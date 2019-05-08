package edu.sjsu.cs.cs151.checkers.view;

import edu.sjsu.cs.cs151.checkers.model.Checker;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Tile extends JPanel {
	public Tile(Checker checker) {
		super();
		this.setSize(DEFAULT_SIZE, DEFAULT_SIZE);
		if (checker.canHoldPiece())
			this.setBackground(new Color(0x0A2663));
		else
			this.setBackground(new Color(0xAAD6FD));
		//this.setOpaque(false);
	}

	public static final int DEFAULT_SIZE = 100;
}