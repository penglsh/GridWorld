package chameleonKid1;


/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.ChameleonCritter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;


/**
 * A <code>ChameleonKid</code> A ChameleonKid changes its color to the color of 
 * one of the actors immediately in front or behind. otherwise, work like ChameleonCritter.
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ChameleonKid extends ChameleonCritter
{
	private static double darkerColor = 0.9;
	
	/*
	 * the method is similar to we can find in the CrabCritter
	 * here we get the actors in the AHEAD and HALF_CIRCLE
	 */
	public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            { Location.AHEAD, Location.HALF_CIRCLE};
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null) {
                actors.add(a);
            }
                
        }

        return actors;
    }
	
	/*
	 * 
	 */
	public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid<Actor> gr = getGrid();
        Location loc = getLocation();
    
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc)) {
                locs.add(neighborLoc);
            }     
        }
        return locs;
    }    
	
    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, no action is taken.
     */
    public void processActors(ArrayList<Actor> actors)
    {
    	int n = actors.size(); 
  	  if (n == 0) 
  	  { 
  	    getDarker(); 
  	    return; 
  	  } 
  	  int r = (int) (Math.random() * n); 
  	  Actor other = actors.get(r); 
  	  setColor(other.getColor());
    }

    /**
     * Turns towards the new location as it moves.
     */
    public void makeMove(Location loc)
    {
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }
    
    /*
     * Turn darker color by a constant color
     */
    private void getDarker() {
    	Color currColor = getColor();
    	int darkerRed = (int)(currColor.getRed() * darkerColor);
    	int darkerGreen = (int)(currColor.getGreen() * darkerColor);
    	int darkerBlue = (int)(currColor.getBlue() * darkerColor);
        setColor(new Color(darkerRed, darkerGreen, darkerBlue));
    }
}

