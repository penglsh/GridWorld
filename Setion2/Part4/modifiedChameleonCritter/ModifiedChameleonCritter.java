package modifiedChameleonCritter;

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
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>ModifiedChameleonCritter</code>if the list of actors to process 
 * is empty, the color of the ChameleonCritter will darken (like a flower).
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ModifiedChameleonCritter extends Critter
{
	private static double Darker_color = 0.9;
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
    	int darkerRed = (int)(currColor.getRed() * Darker_color);
    	int darkerGreen = (int)(currColor.getGreen() * Darker_color);
    	int darkerBlue = (int)(currColor.getBlue() * Darker_color);
        setColor(new Color(darkerRed, darkerGreen, darkerBlue));
    }
}
