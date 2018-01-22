package io.iamkyu.domain;

public interface Authentication {

    Object getPrincipal();

    Object getCredentials();

    boolean isAuthenticated();

    void setAuthenticated(boolean isAuthenticated);
}
