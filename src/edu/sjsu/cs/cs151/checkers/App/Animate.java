package edu.sjsu.cs.cs151.checkers.App;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JPanel;

public class Animate implements ActionListener {
	private static int FPS = 60;
	private static int MS_S = 1000;

	private int duration = 0; // in milliseconds
	private int timeIncrement = 0;
	private int timeElapsed = 0;
	private int dX = 0;
	private int dY = 0;
	private JPanel view = null;
	private Timer timer;

	Animate(int duration) {
		this.duration = duration;
		timeIncrement = MS_S / FPS;
		timer = new Timer(timeIncrement, this);
	}

	public void animateTo(JPanel view, Location location) {
		timer.stop();
		timeElapsed = 0;
		this.view = view;
		Rectangle bounds = view.getBounds();
		int x = bounds.x;
		int y = bounds.y;
		dX = location.getX() - x;
		dY = location.getY() - y;
		timer.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (timeElapsed >= duration) {
			timer.stop();
			return;
		}
		// TODO: need to smooth animation
		double progress = (double)timeElapsed / Math.max(1.0, (double)duration);
		double easeProgress = easeInEaseOut(progress);
		int xIntermediate = (int) Math.ceil((double)dX * easeProgress);
		int yIntermediate = (int) Math.ceil((double)dY * easeProgress);
		view.setBounds(xIntermediate, yIntermediate, view.getWidth(), view.getHeight());
		view.repaint();
		timeElapsed += timeIncrement;
	}
	
	private double easeInEaseOut(double p) {
		if (p < 0.5)
			return 2.0 * Math.pow(p, 2.0);
		else
			return (4 - 2 * p) * p - 1;
	}
}