package edu.sjsu.cs.cs151.checkers.controller;

import java.io.Serializable;

/**
 * Message is an empty abstract class, used by more specialized Message subclasses to identify them as Messages.
 * Any Message object represents a message/event to be sent from View to Model via Controller.
 * Each type of event has its own Message class.
 * @author seanz
 *
 */
public abstract class Message implements Serializable {

}
