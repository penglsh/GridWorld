package quickCrab;

import java.util.ArrayList;
import info.gridworld.actor.*;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
/**
 * A <code>QuickCrab</code> A QuickCrab moves to one of the two locations, 
 * randomly selected, that are two spaces to its right or left, if that 
 * location and the intervening location are both empty. Otherwise, a 
 * QuickCrab moves like a CrabCritter. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class QuickCrab extends CrabCritter {
	/*
	 * constructor
	 */
	public QuickCrab() {
		
	}
	
	/*
	 * override the getMoveLocations method get the empty locations two cells left and two cells right
	 */
	public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        // get the left two empty location
        addEmptyTwoCellsAway(getDirection() + Location.LEFT, locs);
        // get the right
        addEmptyTwoCellsAway(getDirection() + Location.RIGHT, locs);
        
        // if locs is empty, recall the getMoveLocation method
        if (locs.size() == 0) {
        	super.getMoveLocations();
        }
        return locs;
    }
	
	/*
	 * get the empty location two cells away
	 */
	private void addEmptyTwoCellsAway(int dir, ArrayList<Location> locs) {
		Location currLoc = getLocation();
		Grid<Actor> grid = getGrid();
		Location nextOne = currLoc.getAdjacentLocation(dir);
		if (grid.isValid(nextOne) && grid.get(nextOne) == null) {
			Location twoAway = nextOne.getAdjacentLocation(dir);
			if (grid.isValid(twoAway) && grid.get(twoAway) == null) {
				locs.add(twoAway);
			}
		}
	}
}
