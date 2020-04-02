package com.github.enesusta.stomp.exception;

public class AuthException extends Exception {
    public AuthException() {
        super("Your credentials are not valid, check your login and passcode information");
    }
}
