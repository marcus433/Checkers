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
//import edu.sjsu.cs.cs151.checkers.model.Model;

/**
 * Connects all views together
 * */
public class MainView extends View {
	/**
	 * Instantiates all views
	 * */
	public MainView() {
		this.toolbar = new Toolbar();
		this.board = new Gameboard();
		this.setBackground(new Color(0xAAD6FD));
		this.setLayout(null);
		this.add(toolbar);
		this.add(board);
		board.setBackground(Color.BLUE);
	}
	
	/**
	 * Creates new layout nested structure
	 * @return layout - nested layouts
	 * */
	@Override
	public
	Layout layoutThatFits() {
		ArrayList<Layout> children = new ArrayList<Layout>();
		children.add(toolbar);
		children.add(board);
		return new InsetLayout(new EdgeInsets(0, PADDING, PADDING, PADDING), 
										new StackLayout(StackLayout.Direction.VERTICAL, PADDING, children));
	}
	
	/**
	 * Gets current toolbar
	 * @return toolbar
	 * */
	public Toolbar getToolbar() {
	   return toolbar;
	}
	
	/**
	 * Gets current gameboard
	 * @return gameboard
	 * */
	public Gameboard getGameboard() {
	   return board;
	}
	
	// Private fields
	
	private Toolbar toolbar;
	private Gameboard board;
	
	public static final int PADDING = 25;
}