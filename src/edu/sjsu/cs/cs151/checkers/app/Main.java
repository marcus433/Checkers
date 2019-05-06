package edu.sjsu.cs.cs151.checkers.app;

import javax.swing.JFrame;
import edu.sjsu.cs.cs151.checkers.view.Game;
/*
 * Handles main JFrame
 * */

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Checkers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Game game = new Game();
		frame.getContentPane().add(game);
		frame.setSize(800, 900);
		frame.setVisible(true);
	}
}