package edu.sjsu.cs.cs151.checkers.controller;

import javax.swing.JFrame;
import edu.sjsu.cs.cs151.checkers.view.MainView;

public class WindowController {
	public WindowController() {
		this.window = new JFrame("Checkers");
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainView view = new MainView();
		this.window.getContentPane().add(view);
		this.window.setSize(800, 900);
	}

	public void show() {
		this.window.setVisible(true);
	}

	private JFrame window;
}