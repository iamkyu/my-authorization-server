package io.iamkyu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Kj Nam
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class InvalidClientException extends OauthException {
    public InvalidClientException(String message) {
        super(message);
    }
}
