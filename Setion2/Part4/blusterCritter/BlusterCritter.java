package blusterCritter;

import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
/**
 * A <code>BlusterCritter</code> It counts the number of critters in those 
 * locations. If there are fewer than c critters, the BlusterCritter’s 
 * color gets brighter (color values increase). If there are c or more critters, the BlusterCritter’s color darkens (color values decrease).<br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter {
	
	private int courage;
	private static int colorddis = 10;
	private static int maxcolor = 255;
    /*
     * constructs with a courage
     */
	public BlusterCritter(int c) {
		courage = c;
	}
	
	/*
	 * Get the neighbor Critters locations in two cells
	 */
	public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        Location currLoc = getLocation();
        Grid<Actor> grid = getGrid();
        for (int row = currLoc.getRow() - 2; row <= currLoc.getRow() + 2; row ++) {
        	for (int col = currLoc.getCol() - 2; col <= currLoc.getCol(); col ++) {
        		Location tmpLoc = new Location(row, col);
        		if (grid.isValid(tmpLoc)) {
        			Actor tmpActor = grid.get(tmpLoc);
        			if (tmpActor instanceof Critter && tmpActor != this) {
        				actors.add(tmpActor);
        			}
        		}
        	}
        }
        return actors;
    }
	
	/*
	 * If courage is more than the number of Critter, get dark
	 */
	private void getDark() {
		int red = getColor().getRed();
		int green = getColor().getGreen();
		int blue = getColor().getBlue();
		if (red > colordis) { 
			red -= colordis; 
		}
		if (green > colordis) { 
			green -= colordis; 
		}
		if (blue > colordis) { 
			blue -= colordis; 
		}
		setColor(new Color(red, green, blue));
	}
	
	/*
	 * If courage is fewer than the number of Critter, get light
	 */
	private void getLight() {
		int Red = getColor().getRed();
		int Green = getColor().getGreen();
		int Blue = getColor().getBlue();
		if (Red < maxcolor - colordis) { 
			Red += colordis; 
		}
		if (Green < maxcolor - colordis) { 
			Green += colordis; 
		}
		if (Blue < maxcolor - colordis) { 
			Blue += colordis; 
		}
		setColor(new Color(Red, Green, Blue));
	}
	
	/*
	 * override the processActors method, to find the number of Critter
	 */
	public void processActors(ArrayList<Actor> actors) {
		int countCritter = actors.size();
		
		if (courage > countCritter) {
			getLight();
		}
		else {
			getDark();
		}
	}
}
