package edu.sjsu.cs.cs151.checkers.view;

import edu.sjsu.cs.cs151.checkers.layout.*;
import edu.sjsu.cs.cs151.checkers.layout.AlignLayout.Alignment;
import edu.sjsu.cs.cs151.checkers.layout.AlignLayout.Direction;
import edu.sjsu.cs.cs151.checkers.model.Model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Toolbar for game
 */
public class Toolbar extends View {
	public Toolbar() {
		super();
		this.setLayout(null);
		this.setOpaque(false);
		currentTurn = new CurrentTurn();
		skipTurnButton = new Button("Skip Turn");
		try {
			restartButton = new Button(ImageIO.read(new File("assets/restart.png")));
		} catch (IOException e) {
			e.printStackTrace();
			restartButton = new Button("Restart");
		}
		add(currentTurn);
		add(skipTurnButton);
		add(restartButton);
	}

	public void updateState(Model model) {
		currentTurn.updateState(model);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRoundRect(0, 0, g.getClipBounds().width, g.getClipBounds().height, 50, 50); // x y width height arcWidth arcHeight
		g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height - 26);
	}
	
	@Override
	public Dimension getDisplaySize(Dimension size, Point location) {
		return new Dimension(size.width, DEFAULT_HEIGHT); // temporary hack until layoutManager allows to fill on only 1 dimension
	}
	
	@Override
	public
	Layout layoutThatFits() {
		ArrayList<Layout> children = new ArrayList<Layout>();
		children.add(currentTurn);
		children.add(new AlignLayout(AlignLayout.Direction.HORIZONTAL, AlignLayout.Alignment.CENTER, skipTurnButton));
		children.add(new AlignLayout(AlignLayout.Direction.HORIZONTAL, AlignLayout.Alignment.END, restartButton));
		return new InsetLayout(new EdgeInsets(PADDING, PADDING, PADDING, PADDING), 
				new StackLayout(StackLayout.Direction.HORIZONTAL, 0, children));
	}

	public static final int DEFAULT_HEIGHT = 50;

	private CurrentTurn currentTurn;
	private Button skipTurnButton;
	private Button restartButton;
	private int PADDING = 5;
}