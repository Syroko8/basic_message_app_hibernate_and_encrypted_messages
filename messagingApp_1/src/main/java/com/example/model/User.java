package com.example.model;

import javax.persistence.*;

/**
 * @author Nicolás Puebla Martín
 * 
 * Clase a partir de la cual se generará la entidad User. 
 */
@Entity
public class User {

    // Atributos de la clase.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String passwd;
    private String userKey;
     
    // Constructor.
    public User(String username, String passwd, String userKey) {
        this.username = username;
        this.passwd = passwd;
        this.userKey = userKey;
    }
    
    public User() {
    	
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getKey() {
        return userKey;
    }

    public void setKey(String userKey) {
        this.userKey = userKey;
    }
 
}

