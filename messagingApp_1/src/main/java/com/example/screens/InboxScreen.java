package com.example.screens;

import javax.swing.*;

import com.example.controller.MessageController;
import com.example.utils.SwitchView;
import com.example.model.Message;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InboxScreen extends JFrame implements MessageView {
    private List<Message> messages; // Lista de mensajes
    private JPanel mainPanel; // Panel principal donde se muestran los mensajes
    private JScrollPane scrollPane; // Panel de desplazamiento
    private JButton logoutButton; // Botón de cerrar sesión
    private JButton sendMessageButton; // Botón de enviar mensaje
    private MessageController controller;

    public InboxScreen() {
        messages = new ArrayList<>();

        // Configuración del JFrame
        setTitle("Bandeja de Entrada");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null); // Usar diseño absoluto para que sea compatible con el editor de diseño

        // Crear el panel principal
        mainPanel = new JPanel();
        mainPanel.setLayout(null); // Diseño absoluto dentro del panel principal
        mainPanel.setBackground(new Color(240, 248, 255));
        mainPanel.setBounds(20, 20, 740, 360); // Dimensiones y posición del panel

        // Configurar el JScrollPane
        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBounds(20, 20, 740, 360); // Ajustar tamaño del JScrollPane
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(scrollPane); // Añadir el scroll al frame principal

        // Botón de cerrar sesión
        logoutButton = new JButton("Cerrar Sesión");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setBackground(new Color(255, 69, 0));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBounds(20, 400, 159, 30); // Posición y tamaño
        logoutButton.addActionListener(e -> {
            messages.clear(); // Limpiar la lista de mensajes
            mainPanel.removeAll(); // Limpiar el panel principal
            SwitchView.switchViews(2, 0);
        });
        getContentPane().add(logoutButton);

        // Botón de enviar mensaje
        sendMessageButton = new JButton("Enviar Mensaje");
        sendMessageButton.setFont(new Font("Arial", Font.BOLD, 14));
        sendMessageButton.setBounds(620, 400, 150, 30); // Posición y tamaño
        sendMessageButton.addActionListener(e -> {
            // TODO: Lógica para enviar mensaje
            SwitchView.switchViews(2, 3);
        });
        getContentPane().add(sendMessageButton);

        setVisible(false);
    }

    @Override
    public void setMessageController(MessageController controller) {
        this.controller = controller;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessages() {
        int yOffset = 10; // Posición inicial en el eje Y
        for (Message message : messages) {
            JPanel messageComponent = createMessageComponent(message);
            messageComponent.setBounds(10, yOffset, 720, 50); // Establecer posición y tamaño
            mainPanel.add(messageComponent); // Añadir al panel principal
            yOffset += 60; // Incrementar posición para el siguiente mensaje
        }

        // Actualizar el JScrollPane
        mainPanel.setPreferredSize(new Dimension(720, yOffset));
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JPanel createMessageComponent(Message message) {
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(null); // Diseño absoluto para compatibilidad con el editor de diseño
        messagePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        messagePanel.setBackground(Color.WHITE);
        messagePanel.setPreferredSize(new Dimension(720, 50));

        JLabel nameLabel = new JLabel(message.getSender());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setBounds(10, 5, 700, 20); // Posición y tamaño del nombre
        messagePanel.add(nameLabel);

        JLabel messageLabel = new JLabel(message.getMsg());
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        messageLabel.setBounds(10, 25, 700, 20); // Posición y tamaño del mensaje
        messagePanel.add(messageLabel);

        return messagePanel;
    }
}
