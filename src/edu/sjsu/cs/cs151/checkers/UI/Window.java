package edu.sjsu.cs.cs151.checkers.UI;

import javax.swing.JFrame;

/*
 * Handles main JFrame
 * */
// TODO: refactor & move window to GUI package
public class Window {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Checkers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Game game = new Game();
		game.setBackground(java.awt.Color.blue);
		frame.getContentPane().add(game);
		frame.setSize(800, 900);
		frame.setVisible(true);
	}
}