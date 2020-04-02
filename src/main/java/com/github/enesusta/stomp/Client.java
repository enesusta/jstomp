package com.github.enesusta.stomp;

import com.github.enesusta.stomp.broker.BrokerAuthResponse;
import com.github.enesusta.stomp.exception.AuthException;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@SuppressWarnings("Duplicates")
public class Client {

    private OutputStream outputStream;
    private InputStream inputStream;
    private Socket socket;

    public Client(String server, int port, String login, String pass, String vhost) throws IOException, AuthException {
        socket = new Socket(server, port);
        outputStream = socket.getOutputStream();
        inputStream = socket.getInputStream();
        System.out.println(socket.isClosed());
        System.out.println(socket.isConnected());

        HashMap h = new HashMap();
        h.put("login", login);
        h.put("passcode", pass);
        h.put("host", vhost);
        h.put("heart-beat", "60000,0");

        StringBuffer message = new StringBuffer("CONNECT");
        message.append("\n");

        if (h != null) {
            for (Iterator keys = h.keySet().iterator(); keys.hasNext(); ) {
                String key = (String) keys.next();
                String value = (String) h.get(key);
                message.append(key);
                message.append(":");
                message.append(value);
                message.append("\n");
            }
        }
        message.append("\n");
        message.append("\000");
        outputStream.write(message.toString().getBytes(StandardCharsets.UTF_8));
        outputStream.flush();

        System.out.println(Thread.currentThread().getName());
        isAuth();

    }

    void send(String dest, String msg) throws IOException {

        StringBuffer message = new StringBuffer("SEND");
        message.append("\n");
        Map h = new HashMap();
        h.put("destination", dest);

        if (h != null) {
            for (Iterator keys = h.keySet().iterator(); keys.hasNext(); ) {
                String key = (String) keys.next();
                String value = (String) h.get(key);
                message.append(key);
                message.append(":");
                message.append(value);
                message.append("\n");
            }
        }
        message.append("\n");
        message.append(msg + " at :" + new Date());
        message.append("\000");
        outputStream.write(message.toString().getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }

    private void isAuth() {
        BrokerAuthResponse.getInstance().filter(inputStream);
    }

}
