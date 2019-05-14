package edu.sjsu.cs.cs151.checkers.view;

import edu.sjsu.cs.cs151.checkers.layout.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * JFrame wrapper that 
 * takes into accoutn aspect ratios
 * and resizing
 * */
public class Window extends JFrame implements ComponentListener {

	/**
	 * Creates new window
	 * @param title - window title
	 * @param view - view to show in window
	 * */
	public Window(String title, View view) {
		super(title);
		this.view = view;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double relativeDimension = (double)screenSize.height * HEIGHT_RELATIVE;
		double height = relativeDimension + (double)Toolbar.DEFAULT_HEIGHT;
		double width = relativeDimension;
		setSize((int)width, (int)height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addComponentListener(this);
		setLayout(null);
		getContentPane().add(this.view);
		layoutViews();
		try {
		    setIconImage(ImageIO.read(new File("assets/icon.png")));
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

	/**
	 * Creates new window
	 * @param title - window title
	 * */
	private Window(String title) {
		super(title);
	}
	
	/**
	 * Creates new window
	 * */
	private Window() {
		super();
	}
	
	/**
	 * Called on JFrame resize
	 * @param event - resize event
	 * */
	@Override
	public void componentResized(ComponentEvent event) {
		double aspectRatio = aspectRatio();
		Rectangle bounds = event.getComponent().getBounds();
		int clampedWidth = clampDimension(bounds.width + Toolbar.DEFAULT_HEIGHT) - Toolbar.DEFAULT_HEIGHT;
		event.getComponent().setBounds(bounds.x, bounds.y, clampedWidth, (int)((double)clampedWidth * aspectRatio) + Toolbar.DEFAULT_HEIGHT);
		layoutViews();
	}

	/**
	 * Renders view layouts
	 * based on frame size
	 * */
	private void layoutViews() {
		Dimension size = getSize();
		this.view.renderWithSize(size, new Point(0, 0));
	}
	
	/**
	 * Gets aspect ratio for screen
	 * @return ratio
	 * */
	private double aspectRatio() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double relativeDimension = (double)screenSize.height * HEIGHT_RELATIVE;
		double height = relativeDimension + (double)Toolbar.DEFAULT_HEIGHT;
		double width = relativeDimension;
		return height / width;
	}
	
	/**
	 * Clamps dimensions to a min and max
	 * @return clamped dimension
	 * */
	private int clampDimension(int dimension) {
		return Math.max(Math.min(dimension, MAX_DIMENSION), MIN_DIMENSION);
	}
	
	@Override
	public void componentMoved(ComponentEvent e) {}

	@Override
	public void componentShown(ComponentEvent e) {}

	@Override
	public void componentHidden(ComponentEvent e) {}

	// Private Fields

	private View view;
	private static final double HEIGHT_RELATIVE = 0.8;
	private static final int MAX_DIMENSION = 900;
	private static final int MIN_DIMENSION = 700;
}