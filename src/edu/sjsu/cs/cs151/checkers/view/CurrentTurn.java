package edu.sjsu.cs.cs151.checkers.view;

import edu.sjsu.cs.cs151.checkers.layout.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CurrentTurn extends View {
	public CurrentTurn() {
		super();
		this.setOpaque(false);
		piece = new Piece(Piece.Type.PAWN, Piece.Color.RED);
		currentTurnLabel = new Label("Current Turn");
		currentTurnLabel.setDisplaySize(currentTurnLabel.getSize());
		piece.setDisplaySize(new Dimension(30, 30));
		add(piece);
		add(currentTurnLabel);
	}

	@Override
	public Layout layoutThatFits() {
		ArrayList<Layout> children = new ArrayList<>();
		children.add(piece);
		children.add(currentTurnLabel);
		return new StackLayout(StackLayout.Direction.HORIZONTAL, 10, children);
	}

	public static final int DEFAULT_HEIGHT = 40;

	private Piece piece;
	private Label currentTurnLabel;
}