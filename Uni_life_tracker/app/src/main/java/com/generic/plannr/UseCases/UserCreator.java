package com.generic.plannr.UseCases;

import com.generic.plannr.Entities.User;
import com.generic.plannr.Gateways.UserGatewayInterface;

/**
 * Creates and signs up a new user
 */
public class UserCreator {
    private static UserGatewayInterface ug;

    /**
     * Sets up UserCreator
     * @param userGateway the gateway to the user database table
     */
    public UserCreator (UserGatewayInterface userGateway) {
        ug = userGateway;
    }

    /**
     * Creates and signs up a new user
     * @param name the new user's name
     * @param email the new user's email
     * @param password the new user's password
     */
    public static void signUp (String name, String email, String password) {
        ug.saveToDatabase(new User(name, email, password));
        ug.updateLoggedInUser(email);
    }
}
