package edu.sjsu.cs.cs151.checkers.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Color;

public class MainView extends JPanel {
	public MainView() {
		this.setBackground(new Color(0xAAD6FD));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Toolbar toolbar = new Toolbar();
		Gameboard board = new Gameboard();
		this.add(toolbar);
		//this.add(board);
	}
}