package com.github.enesusta.stomp;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        Client client = new Client("localhost", 61613, "guest", "guest", "/");
        client.send("/queue/test", "hello queue");
    }
}
