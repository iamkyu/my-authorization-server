package io.iamkyu.domain;

/**
 * @author Kj Nam
 */
public class Client {

    private long no;

    private String clientId;

    private String clientSecret;

    public Client(long no, String clientId, String clientSecret) {
        this.no = no;
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
