package edu.sjsu.cs.cs151.checkers.app;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.swing.JFrame;

import edu.sjsu.cs.cs151.checkers.controller.Controller;
import edu.sjsu.cs.cs151.checkers.controller.Message;
import edu.sjsu.cs.cs151.checkers.model.Model;
import edu.sjsu.cs.cs151.checkers.view.Button;
import edu.sjsu.cs.cs151.checkers.view.Gameboard;
import edu.sjsu.cs.cs151.checkers.view.MainView;
import edu.sjsu.cs.cs151.checkers.view.Window;

/**
 * Main is the central class that is called on any execution of the Checkers program.
 * It manages the game loop and GUI of any execution of Checkers.
 * @author seanz
 *
 */
public class Main {
	/**
	 * Queue for valve tasks
	 * */
	public static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
	
	/**
	 * main() is responsible for Checkers' core game loop, and also cleanup when exiting the game.
	 * It instantiates a Model and MainView, and links them with a Window and a Controller.
	 * It also runs the Controller's main loop, which manages the game's state, graphics, and user input.
	 * @param args - unused
	 */
	public static void main(String[] args) {
		Model model = Model.getInstance(); // get model instance
		MainView view = new MainView(); // get main view UI
		Window window = new Window("Checkers", view); // add view to window
		Controller controller = new Controller(view, model, queue); // instantiate game controller
		window.setVisible(true); // make window visible
		
		controller.mainLoop(); // start main loop

		window.dispose(); // dispose window
		queue.clear(); // clear queue
	}
}