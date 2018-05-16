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
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import info.gridworld.actor.Bug;

/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class DancingBug extends Bug
{
	private int steps;
    private int[] turningTime;
    private int arrayLength;

    public DancingBug() {

    }

    /**
     * Constructs a box bug that traces a square of a given turning time
     * @param turnTime the turning time before moving
     */
    public DancingBug(int[] turnTime)
    {
    	steps = 0;
    	arrayLength = turnTime.length;
        turningTime = new int[arrayLength];
        System.arraycopy(turnTime, 0, turningTime, 0, arrayLength);
    }
    
    /**
     * turn times turning
     */
    private void turnBeforeMove(int times) {
    	int i = 0;
    	while (i < times) {
    		turn();
    		i ++;
    	}
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
    	if (steps == arrayLength) {
    		steps = 0;
    	}
    	turnBeforeMove(turningTime[steps]);
    	move();
    	steps ++;
    }
}
