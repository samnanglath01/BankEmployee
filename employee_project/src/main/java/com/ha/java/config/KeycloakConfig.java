package com.ha.java.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    private KeycloakConfig(){}

    public static final String REALM = "${keycloak.realm}";
    private static Keycloak keycloak = null;

    private static final String SERVER_URL = "${keycloak.serverUrl}";

    private static final String CLIENT_ID = "${keycloak.clientId}";

    private static final String CLIENT_SECRET = "${keycloak.clientSecret}";

    private static final String USER_NAME = "${keycloak.username}";

    private static final String PASSWORD = "${keycloak.password}";

    @Bean
    public static Keycloak getInstance(){
        if(keycloak == null){

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(SERVER_URL)
                    .realm(REALM)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(USER_NAME)
                    .password(PASSWORD)
                    .clientId(CLIENT_ID)
                    .clientSecret(CLIENT_SECRET)
                    .resteasyClient(new ResteasyClientBuilder()
                            .connectionPoolSize(10)
                            .build()
                    )
                    .build();
        }
        return keycloak;
    }
}
