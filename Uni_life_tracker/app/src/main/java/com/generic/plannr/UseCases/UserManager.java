package com.generic.plannr.UseCases;

import com.generic.plannr.Gateways.UserGatewayInterface;

/**
 * Represents a user manager for the current logged-in user
 */
public class UserManager {
    private final UserGatewayInterface ug;

    /**
     * Sets up the User manager for the current user
     *
     * @param userGateway the gateway to the user database table
     */
    public UserManager(UserGatewayInterface userGateway) {
        this.ug = userGateway;
    }

    /**
     * This method will return user's name
     *
     * @return a String variable containing user's name
     */
    public String getUsersName() {
        return ug.getLoggedInName();
    }

    /**
     * Returns the logged-in user's email
     *
     * @return logged-in user's email
     */
    public String getUsersEmail() {
        return this.ug.getLoggedInEmail();
    }

    /**
     * Returns the logged-in user's password
     *
     * @return logged-in user's password
     */
    public String getUsersPassword() {
        return this.ug.getLoggedInPassword();
    }

    /**
     * Returns the logged-in user's income
     *
     * @return logged-in user's income
     */
    public String getUsersIncome() {
        return this.ug.getLoggedInIncome();
    }

    /**
     * Changes the logged-in user's name
     *
     * @param name is the logged-in user's new name
     */
    public void setUsersName(String name) {
        this.ug.updateName(name);
    }

    /**
     * Changes the logged-in user's password
     *
     * @param password is the logged-in user's new password
     */
    public void setUsersPassword(String password) {
        this.ug.updatePassword(password);
    }

    /**
     * Changes the logged-in user's income
     *
     * @param income the logged-in user's new income
     */
    public void setUserIncome(double income) {
        this.ug.updateIncome(income);
    }

}
