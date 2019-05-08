package edu.sjsu.cs.cs151.checkers.controller;

import edu.sjsu.cs.cs151.checkers.model.Model;
import edu.sjsu.cs.cs151.checkers.view.MainView;
import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

public class Controller {

   public Controller(MainView view, Model model, BlockingQueue<Message> queue) {
      
   }
   
   public void mainLoop() throws Exception {
      Valve.ValveResponse response = Valve.ValveResponse.EXECUTED;
      Message message = null;
      
      while (response != Valve.ValveResponse.FINISH) {
         try {
            message = (Message) messageQueue.take();
         }
         catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      
      for (Valve valve : valves) {
         response = valve.execute(message);
         if (response != Valve.ValveResponse.MISS)
            break;
      }
   }
   
   // Private fields
   
   private BlockingQueue<Message> messageQueue;
   private MainView view;
   private Model model;
   private List<Valve> valves = new LinkedList<Valve>();
   
   private interface Valve {
      
      enum ValveResponse {
         MISS, EXECUTED, FINISH;
      }
      
      public ValveResponse execute(Message message);

   }
}


