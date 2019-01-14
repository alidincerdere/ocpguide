package com.ocppreperation.ocpguide.Service;

import com.ocppreperation.ocpguide.Model.CompileInput;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Created by adere on 3.01.2019.
 */
@Component
public class CompilerRestClient {

    @Value("${clientId}")
    private String clientId;

    @Value("${clientSecret}")
    private String clientSecret;

    @Value("${language}")
    private String language;


    @Value("${doodleUrl}")
    private String doodleUrl;


    public Response sendCompileRequest(CompileInput compileInput) {

        Client client = ClientBuilder.newClient();

        WebTarget webTarget
                = client.target(doodleUrl);

        Invocation.Builder invocationBuilder
                = webTarget.request(MediaType.APPLICATION_JSON);

        compileInput.setClientId(clientId);
        compileInput.setClientSecret(clientSecret);
        compileInput.setLanguage(language);

        Response response
                = invocationBuilder
                .post(Entity.entity(compileInput, MediaType.APPLICATION_JSON));

        return response;

    }

}
