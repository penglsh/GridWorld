package kingCrab;
import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.CrabCritter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 * A KingCrab causes each actor that it processes to move one location 
 * further away from the KingCrab. 
 * If the actor cannot move away, the KingCrab removes it from the grid. <br />
 */
public class KingCrab extends CrabCritter {
	/*
	 * s default constructor
	 */
	public KingCrab() {
		
	}
	
	/*
	 * constructs with color
	 */
	public KingCrab(Color c) {
		setColor(c);
	}
	
	/*
	 * a method to judge the location if near to the KingCrab
	 */
	private boolean isNearToKingCrab(Location testLoc) {
		ArrayList<Location> locs = getGrid().getEmptyAdjacentLocations(getLocation());
		for (Location loc:locs) {
			if (loc == testLoc) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * judge if can move one more away for the actor, if can, move, otherwise return false
	 */
	public boolean canMoveOneMoreAway(Actor actor) {
		Grid<Actor> currGrid = getGrid();
		ArrayList<Location> locs = currGrid.getEmptyAdjacentLocations(actor.getLocation());
		for (Location loc : locs) {
			if (!isNearToKingCrab(loc)) {
				actor.moveTo(loc);
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * override the processActors method
	 */
	public void processActors(ArrayList<Actor> actors) {
		for (Actor actor: actors) {
			if (!canMoveOneMoreAway(actor)) {
				actor.removeSelfFromGrid();
			}
		}
	}
}
