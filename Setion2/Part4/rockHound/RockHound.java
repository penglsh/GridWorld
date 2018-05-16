package rockHound;
import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
/**
 * A <code>RockHound</code>It removes any rocks in that list from the grid. 
 * A RockHound moves like a Critter.
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class RockHound extends Critter {
	
	/*
	 * a default constructor
	 */
	public RockHound() {
		
	}
	
	/*
	 * constructs with color
	 */
	public RockHound(Color color) {
		setColor(color);
	}
	
	/*
	 * Override the processActors method to remove the Rocks
	 */
	public void processActors(ArrayList<Actor> actors) { 
	    for (Actor actor : actors) { 
	        if (actor instanceof Rock) {
	    	    actor.removeSelfFromGrid(); 
	        }
	    } 
	} 
}
