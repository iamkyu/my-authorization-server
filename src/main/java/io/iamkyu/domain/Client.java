package io.iamkyu.domain;

/**
 * @author Kj Nam
 */
public class Client {

    private String clientId;

    private String clientSecret;

    public Client(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
