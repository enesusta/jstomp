package com.github.enesusta.stomp.broker;


import com.github.enesusta.stomp.exception.AuthException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public enum BrokerResponse {

    INSTANCE;

    public boolean authFilter(final InputStream inputStream) {
        boolean isValid = false;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            isValid = bufferedReader.readLine().equals("ERROR");
            if (!isValid)
                throw new AuthException();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AuthException e) {
            e.printStackTrace();
        }
        return isValid;
    }

    public static BrokerResponse getInstance() {
        return INSTANCE;
    }

}
