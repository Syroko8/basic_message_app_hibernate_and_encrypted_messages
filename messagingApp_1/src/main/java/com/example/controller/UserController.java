package com.example.controller;

import com.example.dao.UserDAO;

/**
 * @author Nicolás Puebla Martín
 * 
 * Controlador a través del cual las vistas accederán a los métodos de UserDAO. 
 */
public class UserController {

    // Atributos
    private UserDAO userDAO;

    // Constructor
    public UserController(UserDAO newUserDAO){
        this.userDAO = newUserDAO;
    }

    public boolean login(String userName, String passwd){
        return userDAO.logIn(userName, passwd);
    }

    public void register(String userName, String passwd){
        userDAO.register(userName, passwd);
    }

    public boolean userNameExists(String userName){
        return userDAO.userNameUsed(userName);
    }    
}
