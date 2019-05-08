package edu.sjsu.cs.cs151.checkers.view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class MainView extends JPanel implements ComponentListener {
	public MainView() {
		this.toolbar = new Toolbar();
		this.board = new Gameboard();
		this.setBackground(new Color(0xAAD6FD));
		this.setLayout(null);
		this.add(toolbar);
		this.add(board);
		addComponentListener(this);
	}
	
	@Override
	public void componentResized(ComponentEvent event) {
		Rectangle bounds = event.getComponent().getBounds();
		// TODO: cleaup how this is done
		this.toolbar.setBounds(PADDING, 0, (int) bounds.getWidth() - (2 * PADDING), (int)this.toolbar.getHeight());
		this.board.setBounds(PADDING, (int)this.toolbar.getHeight() + PADDING, (int)bounds.getWidth() - (2 * PADDING), (int)bounds.getWidth() - (2 * PADDING));
		System.out.println((int)bounds.getWidth() - (2 * PADDING));
	}

	@Override
	public void componentMoved(ComponentEvent e) {}

	@Override
	public void componentShown(ComponentEvent e) {}

	@Override
	public void componentHidden(ComponentEvent e) {}
	
	// Private fields
	
	private Toolbar toolbar;
	private Gameboard board;
	
	public static final int PADDING = 25;
}