package com.generic.plannr.UseCases;

import com.generic.plannr.Entities.User;
import com.generic.plannr.Gateways.UserGatewayInterface;

/**
 * Creates and signs up a new user
 */
public class UserCreator {

    /**
     * Creates and signs up a new user
     *
     * @param ug the gateway to the user database table
     * @param name the new user's name
     * @param email the new user's email
     * @param password the new user's password
     */
    public static void signUp (UserGatewayInterface ug, String name, String email, String password) {
        ug.saveToDatabase(new User(name, email, password));
        ug.updateLoggedInUser(email);
    }
}
