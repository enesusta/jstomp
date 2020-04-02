package com.github.enesusta.stomp.exception;

public class AuthException extends Exception {
    public AuthException() {
        super("Your credentials not valid");
    }
}
