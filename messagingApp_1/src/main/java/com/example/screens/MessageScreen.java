package com.example.screens;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.example.controller.MessageController;
import com.example.utils.SwitchView;

public class MessageScreen extends JFrame implements MessageView{
	private MessageController controller;
	
	public MessageScreen() {
		setTitle("Crear Mensaje");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel titleLabel = new JLabel("Crear Mensaje");
		titleLabel.setBounds(341, 14, 160, 25);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(titleLabel);

		JLabel textLabel = new JLabel("Mensaje:");
		textLabel.setBounds(50, 147, 150, 25);
		getContentPane().add(textLabel);

		JTextField textField = new JTextField();
		textField.setBounds(200, 147, 225, 127);
		getContentPane().add(textField);
		
		JLabel userLabel = new JLabel("Destinatario:");
		userLabel.setBounds(50, 65, 150, 25);
		getContentPane().add(userLabel);

		JTextField userField = new JTextField();
		userField.setBounds(200, 65, 150, 25);
		getContentPane().add(userField);

		JButton sendButton = new JButton("Enviar");
		sendButton.setBounds(469, 408, 130, 30);
		sendButton.addActionListener(e -> {
			if(!userField.getText().isEmpty() || !textField.getText().isEmpty()) {
				controller.sendMsg(userField.getText(), textField.getText());
				userField.setText("");
				textField.setText("");
			}
		});
		getContentPane().add(sendButton);

		JButton goToLoginButton = new JButton("Ver bandeja de entrada");
		goToLoginButton.setBounds(624, 408, 150, 30);
		goToLoginButton.addActionListener(e -> {
			SwitchView.switchViews(3, 2);
		});
		getContentPane().add(goToLoginButton);
		setVisible(false);
	}

	@Override
	public void setMessageController(MessageController controller) {
		this.controller = controller;
	}
}