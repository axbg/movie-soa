package com.acc.soa.movies.app.Security;

import com.acc.soa.movies.app.Entities.UserMeta;
import com.acc.soa.movies.app.Repositories.UserMetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.WebServiceFaultException;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
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

        SoapHeader header =
                ((SoapMessage) ((WebServiceMessage) messageContext.getRequest())).getSoapHeader();

        Source bodySource = header.getSource();
        DOMSource bodyDomSource = (DOMSource) bodySource;
        Node bodyNode = bodyDomSource.getNode();

        String token = bodyNode.getTextContent().trim();

        Optional<UserMeta> user = this.userMetaRepository.findByToken(token);

        if (user.isPresent()) {
            UserMeta realUser = user.get();
            messageContext.setProperty("user", realUser.getUsername());
        } else {
            throw new WebServiceFaultException("Token is not valid!");
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
