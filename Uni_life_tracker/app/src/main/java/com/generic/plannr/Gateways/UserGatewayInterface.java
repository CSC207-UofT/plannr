package com.generic.plannr.Gateways;

import com.generic.plannr.Entities.User;

import java.util.List;

public interface UserGatewayInterface {
    void openDatabase();
    void saveToDatabase(final User user);
    User getByID(final int userID);
    User getByEmail(final String userEmail);
    List<User> getAllUsers();
}
