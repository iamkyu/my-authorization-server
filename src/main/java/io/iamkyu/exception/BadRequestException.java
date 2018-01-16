package io.iamkyu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Kj Nam
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends OauthException {

    public BadRequestException(String message) {
        super(message);
    }
}
