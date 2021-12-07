package com.generic.plannr.Gateways;

import com.generic.plannr.Entities.User;

import java.util.List;

public interface UserGatewayInterface {
    /**
     * Open the database for reading or writing
     */
    void openDatabase();

    /**
     * Insert user's info into the database
     *
     * @param user The User to be inserted
     */
    void saveToDatabase(final User user);

    /**
     * Get the User associated with user id userID currently stored
     * in the database
     *
     * @param userID the id of the User we want to return
     * @return the User with id userID
     */
    User getByID(final int userID);

    /**
     * Get the User associated with email userEmail currently stored
     * in the database
     *
     * @param userEmail the email of the User we want to return
     * @return the User with email userEmail
     */
    User getByEmail(final String userEmail);

    /**
     * Get the list of Users currently stored in the database
     *
     * @return a list of all users stored in the database
     */
    List<User> getAllUsers();
}
