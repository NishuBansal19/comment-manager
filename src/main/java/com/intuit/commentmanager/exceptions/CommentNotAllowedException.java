package com.intuit.commentmanager.exceptions;

public class CommentNotAllowedException extends RuntimeException {
    public CommentNotAllowedException(String message) {
        super(message);
    }
}
