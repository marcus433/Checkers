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
		//renderWithSize(new Dimension(800, 800));
		//addComponentListener(this);
	}
	
	@Override
	public
	Layout layoutThatFits() {
		ArrayList<Layout> al2 = new ArrayList<Layout>();
		al2.add(toolbar);
		al2.add(board);
		// top left bottom right
		// new StackLayout(StackLayout.Direction.HORIZONTAL, 0, al)
		return new InsetLayout(new EdgeInsets(0, PADDING, PADDING, PADDING), 
													 new StackLayout(StackLayout.Direction.VERTICAL, PADDING, al2));
		//return new InsetLayout(new EdgeInsets(10, 10, 10, 10), board);
	}
	
	// Private fields
	
	private Toolbar toolbar;
	private Gameboard board;
	
	public static final int PADDING = 25;
}