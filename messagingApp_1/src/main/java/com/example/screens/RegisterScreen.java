package com.example.screens;

import javax.swing.*;

import com.example.controller.UserController;
import com.example.utils.SwitchView;

public class RegisterScreen extends JFrame implements UserView{
	private UserController controller;
	
	public RegisterScreen() {
		setTitle("Registro de Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel titleLabel = new JLabel("Registro de Usuario");
		titleLabel.setBounds(341, 14, 160, 25);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(titleLabel);

		JLabel userLabel = new JLabel("Nombre de Usuario:");
		userLabel.setBounds(50, 65, 150, 25);
		getContentPane().add(userLabel);

		JTextField userField = new JTextField();
		userField.setBounds(200, 65, 150, 25);
		getContentPane().add(userField);

		JLabel passLabel = new JLabel("Contraseña:");
		passLabel.setBounds(50, 117, 150, 25);
		getContentPane().add(passLabel);

		JPasswordField passField = new JPasswordField();
		passField.setBounds(200, 117, 150, 25);
		getContentPane().add(passField);

		JButton registerButton = new JButton("Registrar");
		registerButton.setBounds(489, 408, 130, 30);
		registerButton.addActionListener(e -> {
			if(!userField.getText().isEmpty() || !passField.getText().isEmpty()) {
				if(!controller.userNameExists(userField.getText())) {
					controller.register(userField.getText(), passField.getText());
					SwitchView.switchViews(1, 0);
				}
			}
		});
		getContentPane().add(registerButton);

		JButton goToLoginButton = new JButton("Ir a Inicio de Sesión");
		goToLoginButton.setBounds(644, 408, 130, 30);
		goToLoginButton.addActionListener(e -> {
			SwitchView.switchViews(1, 0);
		});
		getContentPane().add(goToLoginButton);
		setVisible(false);
	}

	@Override
	public void setUserController(UserController controller) {
		this.controller = controller;
	}
}
