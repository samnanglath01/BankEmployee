package com.qrebl.users;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    static Keycloak keycloak = null;
    final static String serverUrl = "http://localhost:8081/";
    public final static String realm = "keycloak_realm";
    final static String clientId = "client_employee";
    final static String clientSecret = "si70rp5V8VQXm5p0PP73LtOc9QxMzZBr";
    final static String userName = "admin";
    final static String password = "admin";
    public KeycloakConfig() {
    }

    @Bean
    public static Keycloak getInstance(){
        if(keycloak == null){

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(userName)
                    .password(password)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .resteasyClient(new ResteasyClientBuilder()
                            .connectionPoolSize(10)
                            .build()
                                   )
                    .build();
        }
        return keycloak;
    }
}
