package com.ha.java.services;

import com.ha.java.config.KeycloakConfig;
import com.ha.java.dto.UserDto;
import com.ha.java.util.Credentials;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//@AllArgsConstructor
@Service
@RequiredArgsConstructor
public class KeyCloakService {
    private final Keycloak keycloak;

    public void addUser(UserDto userDTO){
        CredentialRepresentation credential = Credentials
                .createPasswordCredentials(userDTO.getPassword());

        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmailId());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);
        user.setEmailVerified(true);


        Response response = this.keycloak.realm("keycloak_realm").users().create(user);

//        =================Assign Role to User=================
        // Get realm
        RealmResource realmResource = keycloak.realm("keycloak_realm");
        UsersResource usersRessource = realmResource.users();

        //Get user id
        String userId = CreatedResponseUtil.getCreatedId(response);

        //Get User
        UserResource userResource = usersRessource.get(userId);

        // Get role "USER" from realm role (requires view-realm role)
        RoleRepresentation userRole = realmResource.roles()
                .get("USER").toRepresentation();

        // Assign realm role "USER" to user
        userResource.roles().realmLevel()
                .add(Arrays.asList(userRole));

        System.out.println("===Access Token =" + keycloak.tokenManager().getAccessToken().getToken());

    }

    public List<UserRepresentation> getUser(String userName){
        UsersResource usersResource = getInstance();
        List<UserRepresentation> user = usersResource.search(userName, true);
        return user;

    }

    public void updateUser(String userId, UserDto userDTO){
        CredentialRepresentation credential = Credentials
                .createPasswordCredentials(userDTO.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmailId());
        user.setCredentials(Collections.singletonList(credential));

        UsersResource usersResource = getInstance();
        usersResource.get(userId).update(user);
    }
    public void deleteUser(String userId){
        UsersResource usersResource = getInstance();
        usersResource.get(userId)
                .remove();
    }


    public void sendVerificationLink(String userId){
        UsersResource usersResource = getInstance();
        usersResource.get(userId)
                .sendVerifyEmail();
    }

    public void sendResetPassword(String userId){
        UsersResource usersResource = getInstance();

        usersResource.get(userId)
                .executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));
    }

    public UsersResource getInstance(){
        return KeycloakConfig.getInstance().realm(KeycloakConfig.REALM).users();
    }


}
