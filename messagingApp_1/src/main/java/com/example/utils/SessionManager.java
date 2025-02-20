package com.example.utils;

import com.example.model.User;

public class SessionManager {
	private User usuario;

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
}
