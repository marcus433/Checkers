package edu.sjsu.cs.cs151.checkers.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;
import javax.swing.JButton;

public class Button extends JButton {
	public Button(Icon icon) {
		super(icon);
	}

	public Button(String text) {
		super(text);
	}

	/*public void setText(String text) {
		this.invalidate();
		this.repaint();
	}*/

	@Override
	public Dimension getPreferredSize() {
		// TODO
		return new Dimension(50, 50);
	}

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(50, 50);
	}
	
	@Override
	public Dimension getMaximumSize() {
		return new Dimension(100, 100);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;

		g2d.setColor(new Color(0x0A2663));
		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);

		// TODO: change below code
		FontRenderContext frc = new FontRenderContext(null, false, false);
 		Rectangle2D r = getFont().getStringBounds(getText(), frc);

 		float xMargin = (float)(getWidth()-r.getWidth())/2;
 		float yMargin = (float)(getHeight()-getFont().getSize())/2;

 		g2d.setColor(new Color(0xFFFFFF));
 		g2d.drawString(getText(), xMargin, (float)getFont().getSize() + yMargin);
	}
	
	//0x0A2663
}