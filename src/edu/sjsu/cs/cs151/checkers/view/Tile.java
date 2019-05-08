package edu.sjsu.cs.cs151.checkers.view;

import edu.sjsu.cs.cs151.checkers.app.Main;
import edu.sjsu.cs.cs151.checkers.controller.SelectMessage;
import edu.sjsu.cs.cs151.checkers.layout.*;
import edu.sjsu.cs.cs151.checkers.model.Checker;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Tile extends View {
	public Tile(boolean isDark) {
		super();
		if (isDark) {
			this.piece = new Piece(Piece.Type.PAWN, Piece.Color.BLACK);
			this.setBackground(new Color(0x0A2663));
			add(this.piece);
		} else {
			this.setBackground(new Color(0xAAD6FD));
		}
		
		Tile that = this;
		addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				try {
					System.out.println("Select");
					Main.queue.put(new SelectMessage(that.row, that.column));
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	@Override
	public
	Layout layoutThatFits() {
		if (piece == null)
			return null;
		return new InsetLayout(new EdgeInsets(5, 5, 5, 5), piece);
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public int getColumn() {
		return column;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	// Private fields
	
	private Piece piece = null;
	private int row = 0;
	private int column = 0;
}