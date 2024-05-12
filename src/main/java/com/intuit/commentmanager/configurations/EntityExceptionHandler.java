package com.intuit.commentmanager.configurations;

import com.intuit.commentmanager.dto.APIError;
import com.intuit.commentmanager.exceptions.ActionNotAllowedException;
import com.intuit.commentmanager.exceptions.CommentNotAllowedException;
import com.intuit.commentmanager.exceptions.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EntityExceptionHandler {

    private ResponseEntity<APIError> errorResponseEntity(HttpStatus status, String message) {
        APIError error = new APIError(status.value(), message);
        return new ResponseEntity<>(error, status);
    }

    private ResponseEntity<APIError> badRequest(String message) {
        return errorResponseEntity(HttpStatus.BAD_REQUEST, message);
    }

    private ResponseEntity<APIError> notFound( String message) {
        return errorResponseEntity(HttpStatus.NOT_FOUND, message);
    }

    private ResponseEntity<APIError> internalError( String message) {
        return errorResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    @ExceptionHandler(InvalidInputException.class)
    private ResponseEntity<APIError> handleException(InvalidInputException ex) {return badRequest(ex.getMessage());
    }

    @ExceptionHandler(CommentNotAllowedException.class)
    private ResponseEntity<APIError> handleException(CommentNotAllowedException ex) {return badRequest(ex.getMessage());
    }

    @ExceptionHandler(ActionNotAllowedException.class)
    private ResponseEntity<APIError> handleException(ActionNotAllowedException ex) {return badRequest(ex.getMessage());
    }

}
