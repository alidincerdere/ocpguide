package com.ocppreperation.ocpguide.Model;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by adere on 13.01.2019.
 */
public class CompileInput {

    private String script;

    private String language;

    private String clientId;

    private String clientSecret;

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
