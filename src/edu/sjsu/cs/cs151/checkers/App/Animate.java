package edu.sjsu.cs.cs151.checkers.App;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;
import javax.swing.JPanel;

/**
 * Handles animation interpolation
 * easing, and chaining.
 */
public class Animate implements ActionListener {
	
	/*
	 * Stores information for an animation
	 * in a chain
	 * */
	class AnimationProps {
		int x = 0;
		int y = 0;
		int dX = 0;
		int dY = 0;
		int tX = 0;
		int tY = 0;
		JPanel view = null;
	}
	
	/*
	 * Setup
	 * @param duration: duration of animation in milliseconds
	 * */
	Animate(int duration) {
		this.duration = duration;
		currentAnimation = null;
		timeIncrement = MS_S / FPS;
		timer = new Timer(timeIncrement, this);
		queue = new ArrayList<>();
	}
	
	/*
	 * Adds animation to queue
	 * @param view: target JPanel
	 * @param location: target x, y position
	 * @return self: for animateTo chaining
	 * */
	public Animate animateTo(JPanel view, Location location) {
		int x = 0;
		int y = 0;
		if (queue.size() == 0) {
			Rectangle bounds = view.getBounds();
			x = bounds.x;
			y = bounds.y;
		} else {
			x = queue.get(queue.size() - 1).tX;
			y = queue.get(queue.size() - 1).tY;
		}
		AnimationProps props = new AnimationProps();
		props.view = view;
		props.x = x;
		props.y = y;
		props.dX = location.getX() - x;
		props.dY = location.getY() - y;
		props.tX = x + props.dX;
		props.tY = y + props.dY;
		queue.add(props);
		startAnimation();
		return this;
	}
	
	/*
	 * Starts next animation
	 * */
	private void startAnimation() {
		if (!animationRunning) {
			timeElapsed = 0;
			if (queue.size() > 0) {
				animationRunning = true;
				currentAnimation = queue.get(0);
				timer.start();
			}
		}
	}
	
	/*
	 * Ends animation and proceeds down animation chain
	 * */
	private void endAnimation() {
		timer.stop();
		timeElapsed = 0;
		queue.remove(0);
		if (queue.size() > 0) {
			currentAnimation = queue.get(0);
			timer.start();
		} else {
			animationRunning = false;
		}
	}
	
	/*
	 * Calculates and paints animation frame
	 * @param e: ActionEvent from timer
	 * */
	public void actionPerformed(ActionEvent e) {
		if (timeElapsed >= duration) {
			endAnimation();
			return;
		}
		// TODO: need to smooth animation
		double progress = (double)timeElapsed / Math.max(1.0, (double)duration);
		double easeProgress = easeInEaseOut(progress);
		int xIntermediate = (int) Math.ceil((double)currentAnimation.dX * easeProgress);
		int yIntermediate = (int) Math.ceil((double)currentAnimation.dY * easeProgress);
		
		currentAnimation.view.setBounds(currentAnimation.x + xIntermediate,
										currentAnimation.y + yIntermediate, 
										currentAnimation.view.getWidth(), 
										currentAnimation.view.getHeight());
		currentAnimation.view.repaint();
		timeElapsed += timeIncrement;
	}
	
	/*
	 * Easing Function
	 * @param p: double between 0 and 1 representing interpolation progress
	 * @return over/under shot progress
	 * */
	private double easeInEaseOut(double p) {
		if (p < 0.5)
			return 2.0 * Math.pow(p, 2.0);
		else
			return (4 - 2 * p) * p - 1;
	}
	
	// Private fields
	
	private static int FPS = 120; // drop back to 60 later
	private static int MS_S = 1000;

	private int duration = 0; // in milliseconds
	private int timeIncrement = 0;
	private int timeElapsed = 0;
	private Timer timer;
	private ArrayList<AnimationProps> queue;
	private AnimationProps currentAnimation;
	private boolean animationRunning = false;
}