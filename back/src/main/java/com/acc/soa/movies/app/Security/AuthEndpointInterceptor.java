package com.acc.soa.movies.app.Security;

import com.acc.soa.movies.app.Endpoints.AuthEndpoint;
import com.acc.soa.movies.app.Entities.UserMeta;
import com.acc.soa.movies.app.Repositories.UserMetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.WebServiceFaultException;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.MethodEndpoint;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.http.HttpServletConnection;
import org.w3c.dom.Node;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import java.util.Optional;

@Component
public class AuthEndpointInterceptor implements EndpointInterceptor {

    @Autowired
    private UserMetaRepository userMetaRepository;

    @Override
    public boolean handleRequest(MessageContext messageContext, Object o) throws Exception {

        MethodEndpoint endpoint = (MethodEndpoint) o;

        TransportContext ctx = TransportContextHolder.getTransportContext();
        HttpServletConnection connection = (HttpServletConnection)ctx.getConnection();

        if (endpoint.getMethod().getDeclaringClass() != AuthEndpoint.class) {

            SoapHeader header =
                    ((SoapMessage)(messageContext.getRequest())).getSoapHeader();

            Source bodySource = header.getSource();
            DOMSource bodyDomSource = (DOMSource) bodySource;
            Node bodyNode = bodyDomSource.getNode();

            String token = bodyNode.getTextContent().trim();

            Optional<UserMeta> user = this.userMetaRepository.findByToken(token);

            if (user.isPresent()) {
                UserMeta realUser = user.get();
                messageContext.setProperty("user", realUser.getUsername());
                messageContext.setProperty("user_id", realUser.getId());
            } else {
                throw new WebServiceFaultException("Token is not valid!");
            }
        }

        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext, Object o) throws Exception {
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext, Object o) throws Exception {
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Object o, Exception e) throws Exception {

    }
}
