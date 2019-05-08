package edu.sjsu.cs.cs151.checkers.view;

import edu.sjsu.cs.cs151.checkers.model.Checker;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Tile extends JPanel {
	public Tile(boolean isDark) {
		super();
		if (isDark)
			this.setBackground(new Color(0x0A2663));
		else
			this.setBackground(new Color(0xAAD6FD));
	}
}