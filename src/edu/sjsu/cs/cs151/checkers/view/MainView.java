package edu.sjsu.cs.cs151.checkers.view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Arrays;

import edu.sjsu.cs.cs151.checkers.layout.*;

public class MainView extends View {
	public MainView() {
		this.toolbar = new Toolbar();
		this.board = new Gameboard();
		this.setBackground(new Color(0xAAD6FD));
		this.setLayout(null);
		this.add(toolbar);
		this.add(board);
		board.setBackground(Color.BLUE);
	}
	
	@Override
	public
	Layout layoutThatFits() {
		ArrayList<Layout> children = new ArrayList<Layout>();
		children.add(toolbar);
		children.add(board);
		return new InsetLayout(new EdgeInsets(0, PADDING, PADDING, PADDING), 
										new StackLayout(StackLayout.Direction.VERTICAL, PADDING, children));
	}
	
	// Private fields
	
	private Toolbar toolbar;
	private Gameboard board;
	
	public static final int PADDING = 25;
}