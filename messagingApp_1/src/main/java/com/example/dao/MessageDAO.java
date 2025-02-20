package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JFrame;

import com.example.model.Message;
import com.example.screens.InboxScreen;
import com.example.utils.Crypto;
import com.example.utils.SessionManager;

/**
 * @author Nicolás Puebla Martín
 * 
 * Clase encargada de interactuar con la entidad Message en base de datos. 
 */
public class MessageDAO {

        // Atributos de la clase.
    private EntityManagerFactory emf;
    private EntityManager em;
    private Crypto myCrypto;
    private SessionManager mySessionManager;
    private JFrame[] views;
    
    public MessageDAO(SessionManager newSessionManager){
        this.emf = Persistence.createEntityManagerFactory("example-unit");
        this.myCrypto = new Crypto();
        this.mySessionManager = newSessionManager;
    }
    
    public void setViews(JFrame[] views){
        this.views = views;
    }

    // Método que inicia la transacción.
    private void openEm() {
    	this.em = emf.createEntityManager();
    	this.em.getTransaction().begin();
    }
    
    // Método que cierra la transacción.
    private void closeEm() {
    	this.em.close();
    }

     // Obtiene la clave del usuario receptor.
    public String getKey(String userName){

        String userKey = null;

        try {
            openEm();
            TypedQuery<String> sql = em.createQuery("select userKey from User where username = :username", String.class);
            sql.setParameter("username", userName);
            
            // Ejecutamos la sentencia.
            userKey = sql.getSingleResult();
        } catch (NoResultException e) {
            // El nombre de usuario no existe en la base de datos.
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        // Devolvemos el valor.
        return userKey;
    }

    // Método para enviar un mensaje.
    public String sendMsg(String destinatary, String msg){
        try {

            // Obtenemos la clave del usuario al que va dirigido el mensaje.
            String userKey = getKey(destinatary);

            // Si la clave es nula, el nombre de usuario es incorrecto.
            if(userKey == null){
                System.err.println("El nombre de usuario introducido no pertenece a ningún usuario.");
                return "El nombre de usuario introducido no pertenece a ningún usuario."; 
            }

            // Ciframos el mensaje con la clave obtenida.
            String encrypted = myCrypto.messageCrypted(msg, userKey);

            // Creamos el mensaje y lo almacenamos.
            Message newMessage = new Message(mySessionManager.getUsuario().getUsername(), destinatary, encrypted);

            openEm();
            em.persist(newMessage);
            em.getTransaction().commit();
            closeEm();
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.err.println("El mensaje ha sido enviado correctamente.");
        return "El mensaje ha sido enviado correctamente.";
    }

    public void getMessages(){

        List<Message> messages = new ArrayList<Message>();

        try {
            openEm();

            System.err.println(mySessionManager.getUsuario().getUsername());
            TypedQuery<Message> sql = em.createQuery("from Message where receiver = :username", Message.class);
            sql.setParameter("username", mySessionManager.getUsuario().getUsername());

            messages = sql.getResultList();
		    closeEm();

            // Desencriptamos los mensajes obtenidos.
            for(Message message : messages){
                String decrypted = myCrypto.messageDecrypted(message.getMsg(), mySessionManager.getUsuario().getKey());
                message.setMsg(decrypted);
            }

            // Insertamos los mensajes en la vista.
            InboxScreen screen = (InboxScreen) views[2];
            screen.setMessages(messages);

        } catch (NoResultException e) {
            // Si la lista es nula, no ocurre nada.
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
