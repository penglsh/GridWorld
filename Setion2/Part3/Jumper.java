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

import java.awt.Color;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 * A <code>Jumper</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */

public class Jumper extends Actor {
	// Constructs
	public Jumper() {
		
	}
	
	// Constructs a jumper for a given color
	public Jumper(Color color) {
		setColor(color);
	}
	
	/*
	 * To know whether the jumper can jump by to cells
	 * If the cell in front of the jumper by two cells is null or
	 * a flower and its neighbor is null or a Rock or a flower, it can jump, 
	 * that is return true  
	 */
	public boolean canJump() {
		Grid<Actor> curr_grid = getGrid();
		// return false if the grid is null
		if (curr_grid == null){
			return false;
		}
		Location currLocation = getLocation();
		Location neighbor = currLocation.getAdjacentLocation(getDirection());
		// if the neighbor is out of the current grid, return false
		if (!curr_grid.isValid(neighbor)){
			return false;
		}
		Actor actor = curr_grid.get(neighbor);
		if (actor == null || actor instanceof Flower || actor instanceof Rock){
			Location nexLoc = neighbor.getAdjacentLocation(getDirection());
			if (!curr_grid.isValid(nexLoc)) { 
				return false; 
			}
			Actor nexActor = curr_grid.get(nexLoc);
			return (nexActor == null) || (nexActor instanceof Flower);
		}
		else {
			return false;
		}
	}
	
	/*
	 * If can jump, jump two cells. Of course, the location two cells away must be valid, otherwise, remove itself
	 */
	public void jump() {
		Grid<Actor> curr_grid = getGrid();
		if (curr_grid == null) {
		    return; 
		}
		Location currLoc = getLocation();
		Location adjacent = currLoc.getAdjacentLocation(getDirection());
		Location two_cells_away = adjacent.getAdjacentLocation(getDirection());
		if (curr_grid.isValid(two_cells_away)) {
			moveTo(two_cells_away);
		}
		else { r
			emoveSelfFromGrid(); 
		}
	}
	
	/*
	 *  turn mothod is the same as other classes, turn 45 degrees one time
	 */
	public void turn() {
		setDirection(getDirection() + Location.HALF_RIGHT);
	}
	/*
	 * act method is similar to other classes
	 */
	public void act() {
		if (canJump()){
			jump();
		}
		else {
			turn();
		}
	}
}