package dev.knacion.restfulwebservices.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserMismatchException extends RuntimeException {

    public UserMismatchException() {
        super("User ID and the Request Body did not matched. ");
    }
}
