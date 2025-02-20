package com.example;

import javax.swing.JFrame;

import com.example.controller.MessageController;
import com.example.controller.UserController;
import com.example.dao.MessageDAO;
import com.example.dao.UserDAO;
import com.example.screens.InboxScreen;
import com.example.screens.LoginScreen;
import com.example.screens.MessageScreen;
import com.example.screens.RegisterScreen;
import com.example.utils.SessionManager;
import com.example.utils.SwitchView;

/**
 * @author Nicolás Puebla Martín
 * 
 * Clase principal del programa. Intanciará todos los objetos necesarios el correcto funcionamiento de este.
 */
public class Main {
    public static void main(String[] args) {
        
        // Instanciamos las clases necesarias.
    	JFrame[] views = new JFrame[4];
    	views[0] = new LoginScreen();
        views[1] = new RegisterScreen();
        views[2] = new InboxScreen();
        views[3] = new MessageScreen();
   
        // Instanciamos el sessionManager y switchView.
        SessionManager sessionManager = new SessionManager();
        SwitchView switchView = new SwitchView(views);

        // Instanciamos clases DAO.
        UserDAO userDAO = new UserDAO(sessionManager);
        MessageDAO messageDAO = new MessageDAO(sessionManager);
        messageDAO.setViews(views);

        // Instanciamos los controladores.
        UserController userController = new UserController(userDAO);
        MessageController messageController = new MessageController(messageDAO);

        // Establecemos los controladores y switchView.
        LoginScreen loginScreen = (LoginScreen) views[0];
        RegisterScreen registerScreen = (RegisterScreen) views[1];
        loginScreen.setUserController(userController);
        loginScreen.setMessageController(messageController);
        loginScreen.setViews(views);

        registerScreen.setUserController(userController);
        
        InboxScreen inboxScreen = (InboxScreen) views[2];
        MessageScreen messageScreen = (MessageScreen) views[3];
        inboxScreen.setMessageController(messageController);
        messageScreen.setMessageController(messageController);
        
        views[0].setVisible(true);
    }
}