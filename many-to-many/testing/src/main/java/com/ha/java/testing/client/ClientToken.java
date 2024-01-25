package com.ha.java.testing.client;

import lombok.Getter;
import lombok.Setter;


public class ClientToken {

    private static String token;

    public static String getToken() {
        return token;
    }
    public static void setToken(String token) {
        ClientToken.token = token;
    }
}
