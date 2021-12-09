package com.generic.plannr.Gateways;

import com.generic.plannr.Entities.User;

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
     * Get the current logged-in user's name currently stored in the database
     *
     * @return the logged-in user's name in the database
     */
    String getLoggedInName();

    /**
     * Get the current logged-in user's email currently stored in the database
     *
     * @return the logged in user's email in the database
     */
    String getLoggedInEmail();

    /**
     * Get the current logged-in user's income currently stored in the database
     *
     * @return the logged in user's income in the database
     */
    String getLoggedInIncome();

    /**
     * Get the current logged-in user's password currently stored in the database
     *
     * @return the logged-in user's password in the database
     */
    String getLoggedInPassword();

    /**
     * Get the password associated with userEmail currently stored in the database
     *
     * @param userEmail the user's email
     * @return the userEmail's password from the database
     */
    String getPassword(String userEmail);

    /**
     * Update the User's name in the database
     *
     * @param name The user's name to be inserted
     */
    void updateName(String name);

    /**
     * Update the User's password in the database
     *
     * @param password The user's password to be inserted
     */
    void updatePassword(String password);

    /**
     * Update the User's income in the database
     *
     * @param income The user's income to be inserted
     */
    void updateIncome(Double income);

    /**
     * Set which account is currently logged in the database
     */
    void updateLoggedInUser(String userEmail);
}
