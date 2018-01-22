package io.iamkyu.domain;

public class UsernamePasswordAuthentication implements Authentication {

    private String id;

    private String password;

    private boolean authenticated;

    public UsernamePasswordAuthentication(String id, String password) {
        this.id = id;
        this.password = password;
        authenticated = false;
    }

    @Override
    public Object getPrincipal() {
        return id;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        this.authenticated = isAuthenticated;
    }
}
