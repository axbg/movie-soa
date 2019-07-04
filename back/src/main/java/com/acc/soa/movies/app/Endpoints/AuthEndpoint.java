package com.acc.soa.movies.app.Endpoints;

import com.acc.soa.movies.app.Entities.UserMeta;
import com.acc.soa.movies.app.Repositories.UserMetaRepository;
import com.acc.soa.movies.app.SOAPEntities.AuthenticatedUser;
import com.acc.soa.movies.app.SOAPEntities.LoginRequest;
import com.acc.soa.movies.app.SOAPEntities.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.WebServiceFaultException;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigInteger;
import java.util.Optional;

@Endpoint
public class AuthEndpoint {

    private static final String NAMESPACE_URI = "http://movies-soa.com/app";

    private UserMetaRepository userMetaRepository;

    @Autowired
    public AuthEndpoint(UserMetaRepository userMetaRepository) {
        this.userMetaRepository = userMetaRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "loginRequest")
    @ResponsePayload
    public LoginResponse loginResponse(@RequestPayload LoginRequest request,
                                       MessageContext messageContext) {
        LoginResponse response = new LoginResponse();

        Optional<UserMeta> findUser =
                userMetaRepository.findByUsernameAndPassword(request.getUser().getUsername(),
                        request.getUser().getPassword());
        AuthenticatedUser newUser;

        if (findUser.isPresent()) {
            UserMeta existingUser = findUser.get();
            newUser = new AuthenticatedUser(existingUser.getUsername(),
                    BigInteger.valueOf(existingUser.getId()), existingUser.getToken());
        } else {
            findUser = userMetaRepository.findByUsername(request.getUser().getUsername());

            if (!findUser.isPresent()) {
                UserMeta newUserMeta = new UserMeta(request.getUser().getUsername(),
                        request.getUser().getPassword());
                userMetaRepository.save(newUserMeta);

                newUser = new AuthenticatedUser(newUserMeta.getUsername(),
                        BigInteger.valueOf(newUserMeta.getId()), newUserMeta.getToken());
            } else {
                throw new WebServiceFaultException("Credentials are not valid!");
            }
        }

        response.setAuthenticatedUser(newUser);

        return response;
    }
}
