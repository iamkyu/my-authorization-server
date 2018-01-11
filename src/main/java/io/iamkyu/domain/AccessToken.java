package io.iamkyu.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * @author Kj Nam
 */
public class AccessToken implements Serializable {

    public static String TOKEN_TYPE_BEARER = "Bearer";

    private String value;

    private String tokenType = TOKEN_TYPE_BEARER;

    private RefreshToken refreshToken;

    private Date expiration;

    private Map<String, String> additionalInformation;

    public AccessToken(String value) {
        this.value = value;
    }

    public AccessToken(String value, String tokenType, RefreshToken refreshToken, Date expiration) {
        this(value);
        this.tokenType = tokenType;
        this.refreshToken = refreshToken;
        this.expiration = expiration;
        this.additionalInformation = Collections.emptyMap();
    }

    public AccessToken(String value, String tokenType, RefreshToken refreshToken, Date expiration, Map<String, String> additionalInformation) {
        this(value);
        this.tokenType = tokenType;
        this.refreshToken = refreshToken;
        this.expiration = expiration;
        this.additionalInformation = additionalInformation;
    }

    public static String getTokenTypeBearer() {
        return TOKEN_TYPE_BEARER;
    }

    public String getValue() {
        return value;
    }

    public String getTokenType() {
        return tokenType;
    }

    public RefreshToken getRefreshToken() {
        return refreshToken;
    }

    public Date getExpiration() {
        return expiration;
    }

    public int getExpiresIn() {
        return expiration != null ? Long.valueOf((expiration.getTime() - System.currentTimeMillis()) / 1000L)
                .intValue() : 0;
    }

    public Map<String, String> getAdditionalInformation() {
        return additionalInformation;
    }
}
