package com.example.screens;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.swing.*;

import com.example.controller.MessageController;
import com.example.controller.UserController;
import com.example.utils.SwitchView;

public class LoginScreen extends JFrame implements UserView, MessageView{
	private UserController userController;
	private MessageController messageController;
	private JFrame[] views;

	public LoginScreen() {
		setTitle("Inicio de Sesión");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel titleLabel = new JLabel("Inicio de Sesión");
		titleLabel.setBounds(329, 11, 120, 25);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(titleLabel);

		JLabel userLabel = new JLabel("Nombre de Usuario:");
		userLabel.setBounds(50, 61, 150, 25);
		getContentPane().add(userLabel);

		JTextField userField = new JTextField();
		userField.setBounds(200, 61, 150, 25);
		getContentPane().add(userField);

		JLabel passLabel = new JLabel("Contraseña:");
		passLabel.setBounds(50, 109, 150, 25);
		getContentPane().add(passLabel);

		JPasswordField passField = new JPasswordField();
		passField.setBounds(200, 109, 150, 25);
		getContentPane().add(passField);

		JButton loginButton = new JButton("Iniciar Sesión");
		loginButton.setBounds(496, 402, 130, 30);
		loginButton.addActionListener(e -> {
			if(!userField.getText().isEmpty() || !passField.getText().isEmpty()) {
				if(userController.login(userField.getText(), passField.getText())) {
					messageController.getMessages();
					InboxScreen inboxScreen = (InboxScreen) views[2];
					inboxScreen.addMessages();
					SwitchView.switchViews(0, 2);
				}
			}
		});
		getContentPane().add(loginButton);

		JButton goToRegisterButton = new JButton("Ir a Registro");
		goToRegisterButton.setBounds(636, 402, 130, 30);
		goToRegisterButton.addActionListener(e -> {
			SwitchView.switchViews(0, 1);
		});
		getContentPane().add(goToRegisterButton);
		setVisible(true);
	}

	@Override
	public void setUserController(UserController controller) {
		this.userController = controller;
	}

	@Override
	public void setMessageController(MessageController controller){
		this.messageController = controller;
	}

	public void setViews(JFrame[] views) {
		this.views = views;
	}
}
