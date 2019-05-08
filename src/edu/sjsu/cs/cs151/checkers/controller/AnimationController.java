package edu.sjsu.cs.cs151.checkers.controller;

import java.awt.Color;
import java.awt.Point;
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
public class AnimationController implements ActionListener {
	
	public interface Callback {
	    void onSuccess();
	    void onError(String err);
	}
	
	/*
	 * Stores information for an animation
	 * in a chain
	 * */
	class AnimationProps {
		int x = 0;
		int y = 0;
		int dX = 0;
		int dY = 0;
		int duration = 0; // in milliseconds
	}
	
	/*
	 * Setup
	 * @param view: view to animate
	 * */
	public AnimationController(JPanel view) {
		this.view = view;
		currentAnimation = null;
		timeIncrement = MS_S / FPS;
		timer = new Timer(timeIncrement, this);
		queue = new ArrayList<>();
	}
	
	/*
	 * Adds animation to queue
	 * @param duration: duration of animation in milliseconds
	 * @param location: target x, y position
	 * @return self: for animateTo chaining
	 * */
	public AnimationController animateTo(int duration, Point location) {
		int x = 0;
		int y = 0;
		if (queue.size() == 0) {
			Rectangle bounds = view.getBounds();
			x = bounds.x;
			y = bounds.y;
		} else {
			AnimationProps lastAnimation = queue.get(queue.size() - 1);
			x = lastAnimation.x + lastAnimation.dX;
			y = lastAnimation.y + lastAnimation.dY;
		}
		AnimationProps props = new AnimationProps();
		props.x = x;
		props.y = y;
		props.dX = (int)location.getX() - x;
		props.dY = (int)location.getY() - y;
		props.duration = duration;
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
			if (onComplete != null)
				onComplete.onSuccess();
		}
	}
	
	public void onComplete(Callback callback) {
		onComplete = callback;
	}
	
	/*
	 * Calculates and paints animation frame
	 * @param e: ActionEvent from timer
	 * */
	public void actionPerformed(ActionEvent e) {
		if (timeElapsed >= currentAnimation.duration) {
			endAnimation();
			return;
		}

		double progress = (double)timeElapsed / Math.max(1.0, (double)currentAnimation.duration);
		double easeProgress = easeInEaseOut(progress);
		int xIntermediate = (int) Math.ceil((double)currentAnimation.dX * easeProgress);
		int yIntermediate = (int) Math.ceil((double)currentAnimation.dY * easeProgress);
		
		view.setBounds(currentAnimation.x + xIntermediate,
										currentAnimation.y + yIntermediate, 
										view.getWidth(), 
										view.getHeight());
		view.repaint();
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
			return (4.0 - 2.0 * p) * p - 1.0;
	}
	
	// Private fields
	
	private static int FPS = 60;
	private static int MS_S = 1000;

	private JPanel view = null;
	private int timeIncrement = 0;
	private int timeElapsed = 0;
	private Timer timer;
	private ArrayList<AnimationProps> queue;
	private AnimationProps currentAnimation;
	private boolean animationRunning = false;
	private Callback onComplete = null;
}