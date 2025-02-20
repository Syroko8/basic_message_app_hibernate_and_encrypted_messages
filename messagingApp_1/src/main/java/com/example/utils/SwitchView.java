package com.example.utils;

import javax.swing.JFrame;

/**
 * @author Nicolás Puebla Martín
 * 
 * Clase cuyo método se empleará para alternar entre vistas. 
 */
public class SwitchView {

	private static JFrame[] views;
	
	public SwitchView(JFrame[] newViews) {
		this.views = newViews;
	}
	
  // Crearemos aquí el método para alternar entre vistas.
	public static void switchViews(int origin, int destination) {
		views[origin].setVisible(false);
		views[destination].setVisible(true);
	}
	
}
