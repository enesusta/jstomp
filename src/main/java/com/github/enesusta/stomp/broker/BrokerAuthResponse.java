package com.github.enesusta.stomp.broker;


import com.github.enesusta.stomp.exception.AuthException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public enum BrokerAuthResponse {

    INSTANCE;

    public void filter(final InputStream inputStream) {

        final Runnable runnable = () -> {

            try (BufferedReader bufferedReader =
                         new BufferedReader(new InputStreamReader(inputStream))) {

                final Scanner scanner = new Scanner(bufferedReader);
                final String authResponse = scanner.next();

                if (authResponse.equals("ERROR")) {
                    throw new AuthException();
                } else {
                    BrokerDetails brokerDetails = BrokerDetails
                            .getInstance()
                            .setServer(scanner.next())
                            .setSession(scanner.next());
                    scanner.next();
                    brokerDetails.setVersion(scanner.next());
                    brokerDetails.view();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (AuthException e) {
                e.printStackTrace();
            }
        };

        CompletableFuture.runAsync(runnable);
    }

    public static BrokerAuthResponse getInstance() {
        return INSTANCE;
    }

}
