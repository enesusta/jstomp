package com.github.enesusta.stomp.broker;

import java.util.HashMap;
import java.util.Map;

public class BrokerDetails {

    private static BrokerDetails instance;
    private Map<String, String> details = new HashMap<>(3);


    private BrokerDetails() {

    }

    public static BrokerDetails getInstance() {
        if (instance == null) {
            instance = new BrokerDetails();
        }
        return instance;
    }

    public BrokerDetails setServer(final String server) {
        details.put("server", server);
        return this;
    }

    public BrokerDetails setSession(final String session) {
        details.put("session", session);
        return this;
    }

    public BrokerDetails setVersion(final String version) {
        details.put("version", version);
        return this;
    }

    public void view() {
        details.forEach((e, v) -> {
            System.out.println("key is  :" + e + " value is : " + v);
        });
    }

}
