package com.example.controller;

import java.util.List;

import com.example.dao.MessageDAO;
import com.example.model.Message;

/**
 * @author Nicolás Puebla Martín
 * 
 * Controlador a través del cual las vistas accederán a los métodos de MessageDAO. 
 */
public class MessageController {
    
    // Atributos de la clase.
    MessageDAO myDao;

    public MessageController(MessageDAO newDAO){
        this.myDao = newDAO;
    }

    public String sendMsg(String destinatary, String msg){
        return myDao.sendMsg(destinatary, msg); 
    }
    
    public void getMessages(){
        myDao.getMessages();
    }

}
