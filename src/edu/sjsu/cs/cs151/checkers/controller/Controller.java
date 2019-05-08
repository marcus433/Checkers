package edu.sjsu.cs.cs151.checkers.controller;

import edu.sjsu.cs.cs151.checkers.model.Model;
import edu.sjsu.cs.cs151.checkers.model.Position;
import edu.sjsu.cs.cs151.checkers.view.MainView;
import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

/**
 * Controller acts an intermediary between View and Model, and alters both when either changes.
 * It operates by receiving Message objects from a BlockingQueue queue, which are generated by View;
 * and then uses these Messages to alter Model accordingly, which in turn may affect View.
 * 
 * It also contains the private Valve interface and several specialized Valve classes
 * that interact with their corresponding specialized Message classes.
 * @author seanz
 *
 */
public class Controller {

   /**
    * Constructor for Controller taken from lecture slides.
    * @param view
    * @param model
    * @param queue
    */
   public Controller(MainView view, Model model, BlockingQueue<Message> queue) {
      this.view = view;
      this.model = model;
      this.messageQueue = queue;
      
      valves.add(new SelectMessageValve());
      valves.add(new ReleaseMessageValve());
      valves.add(new ResetMessageValve());
      valves.add(new SkipTurnMessageValve());
   }
   
   /**
    * mainLoop indefinitely monitors queue, accepts Messages from it, and acts accordingly.
    * Method body taken from lecture slides.
    * @throws Exception
    */
   public void mainLoop() {
	  view.updateState(model);
      Valve.ValveResponse response = Valve.ValveResponse.EXECUTED;
      Message message = null;
      
      while (response != Valve.ValveResponse.FINISH) {
         try {
            message = (Message) messageQueue.take();
         }
         catch (InterruptedException e) {
            e.printStackTrace();
         }
         for (Valve valve : valves) {
             response = valve.execute(message);
             if (response != Valve.ValveResponse.MISS)
                break;
          }
      }
   }
   
   // Private fields
   
   private BlockingQueue<Message> messageQueue;
   private MainView view;
   private Model model;
   private List<Valve> valves = new LinkedList<Valve>();
   
   // Valves
   
   private interface Valve {
      
      enum ValveResponse {
         MISS, EXECUTED, FINISH;
      }
      
      public ValveResponse execute(Message message);

   }
   
   private class SelectMessageValve implements Valve {
      
      public ValveResponse execute(Message message) {
         if (message.getClass() != SelectMessage.class)
            return Valve.ValveResponse.MISS;
         
         SelectMessage selectMsg = (SelectMessage) message;
         Position selectAt = new Position(selectMsg.getRow(), selectMsg.getColumn());
         
         // actions in Model
         if (!model.selectChecker(selectAt))
            return Valve.ValveResponse.EXECUTED;
         
         //Position[] validMoves = model.determineValidMoves();
         
         // actions in View
         // TODO: highlight valid move destinations
         view.updateState(model);
         
         return Valve.ValveResponse.EXECUTED;
      }
   }
   
   private class ReleaseMessageValve implements Valve {
      
      public ValveResponse execute(Message message) {
         if (message.getClass() != ReleaseMessage.class)
            return Valve.ValveResponse.MISS;
         
         ReleaseMessage releaseMsg = (ReleaseMessage) message;
         Position dest = new Position(releaseMsg.getRow(), releaseMsg.getColumn());
         
         // actions in Model
         if (!model.movePiece(dest))
            return Valve.ValveResponse.EXECUTED;
         
         // actions in View
         // TODO: update the game board
         view.updateState(model);
         
         return Valve.ValveResponse.EXECUTED;
      }
   }
   
   private class ResetMessageValve implements Valve {
      
      public ValveResponse execute(Message message) {
         if (message.getClass() != ResetMessage.class)
            return Valve.ValveResponse.MISS;
         
         // actions in Model
         model.reset();
         
         // actions in View
         // TODO: reset the game board
         view.updateState(model);
         
         return Valve.ValveResponse.EXECUTED;
      }
   }
   
   private class SkipTurnMessageValve implements Valve {
      
      public ValveResponse execute(Message message) {
         if (message.getClass() != SkipTurnMessage.class)
            return Valve.ValveResponse.MISS;
         
         // actions in Model
         model.switchTurn();
         
         // actions in View
         view.updateState(model);
         
         return Valve.ValveResponse.EXECUTED;
      }
   }
}


