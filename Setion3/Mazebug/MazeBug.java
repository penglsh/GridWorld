//package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;
	public Location last;
	public boolean isEnd = false;
	public Stack<ArrayList<Location>>crossLocation = new Stack<ArrayList<Location>>();
	public Integer stepCount = 0;
	boolean hasShown = false;//final message has been shown
    
	private int[] selectedDirs = {1, 1, 1, 1};  // to store the directions selected
	
	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
		isEnd = false;
	}

	/**
	 * Moves to the next location of the square.
	 * override the act method
	 */
	public void act() {
		
		if (stepCount == 0) {
			init(getLocation());
		}
		
		boolean willMove = canMove();
		if (isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			
			move();
			//increase step count when move 
			stepCount++;
		} 
		else {
			moveBack();
		}
	}

	private void moveBack() {
		// TODO Auto-generated method stub
		if (crossLocation.size() > 0) {
			crossLocation.pop();
			
			if (crossLocation.size() > 0) {
				Grid<Actor> gr = getGrid();
				if (gr == null) {
					return;
				}
				// get the top list
				ArrayList<Location> topList = crossLocation.peek();
				// the back location is the first one in the topList
				Location backLoc = topList.get(0);
				
				Location loc = getLocation();
				
				// get the direction if it turn back
				int dir = getLocation().getDirectionToward(backLoc);
				if (gr.isValid(backLoc)) {
					// set the direction to turn back
					setDirection(dir);
					moveTo(backLoc);
					stepCount ++;	
				}
				else {
					System.out.println("test1");
					removeSelfFromGrid();
				}
				deleteDirTime(dir, gr, loc);
			}
		}
	}

	/*
	 * this method is to delete the selected time in a direction when turn back
	 */
	private void deleteDirTime(int dir, Grid<Actor> gr, Location loc) {
		// TODO Auto-generated method stub
		int tmp = (int)(dir / Location.RIGHT);
		if (tmp == 0) {
			selectedDirs[2] --; // south
		}
		else if (tmp == 1) {
			selectedDirs[3] --;  // west
		}
		else if (tmp == 2) {
			selectedDirs[0] --;  // north
		}
		else if (tmp == 3) {
			selectedDirs[1] --;  // east
		}
		
		Flower fl = new Flower(getColor());
		fl.putSelfInGrid(gr, loc);
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return null;
		}

		ArrayList<Location> valid = new ArrayList<Location>();
		int[] dirs = {Location.NORTH, Location.EAST, Location.SOUTH, Location.WEST};
		
		// find the 4 directions
		for (int i = 0; i < 4; i ++) {
			Location neigh = loc.getAdjacentLocation(dirs[i]);
			if (gr.isValid(neigh)) {
				// if the destination(red rock) is a neighbor, return
				Actor testAc = gr.get(neigh);
				if ((testAc instanceof Rock) && testAc.getColor().equals(Color.RED)) {
					next = neigh;
					ArrayList<Location> desArr = new ArrayList<Location>();
					desArr.add(neigh);
					return desArr;
				}
				else if (testAc == null ) {
					valid.add(neigh);
				}
			}
			
		}
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		ArrayList<Location> validLocs = new ArrayList<Location>();
		validLocs = getValid(getLocation());
		return (validLocs.size() != 0);
	}
	
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 * override this method
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return;
		}
			
		Location loc = getLocation();
		
		ArrayList<Location> locs = getValid(loc);
		int maxTime = 0;  // to judge the the max times of one direction selected
		int index = 0;
		int total = 0;
		int tmpRecord = 0;
		for (Location l : locs) {
			int dir = loc.getDirectionToward(l);
			// if maxTime is not max, refresh it
			if (selectedDirs[dir / Location.RIGHT] > maxTime) {
				maxTime = selectedDirs[dir / Location.RIGHT];
				index = dir / Location.RIGHT;
				tmpRecord = total;
			}
			total ++;
		}
		
		int size = locs.size();
		// if only one choice, add the selectedDir, and the next must be it
		if (size == 1) {
			next = locs.get(tmpRecord);
			selectedDirs[index] ++;
		}
		// otherwise, randomly choose one direction
		else {
			int  randomNum = (int)(Math.random() * 10);
			if (randomNum >= 7) {
				next = locs.get(randomNum % size);
				index = loc.getDirectionToward(next) / Location.RIGHT;
				selectedDirs[index] ++;
			}
			else {
				next = locs.get(tmpRecord);
				selectedDirs[index] ++;
			}
			
		}
		
		for (Location l : locs) {
			if (getDirection() == getLocation().getDirectionToward(l)) {
				// next is it
				next = l;
				index = loc.getDirectionToward(next) / Location.RIGHT;
				selectedDirs[index] ++;
				break;
			}
		}
		
		//judgeReachDestination();
		if (gr.isValid(next)) {
			Actor tActor = gr.get(next);
			// judge whether reach the destination
			if ((tActor instanceof Rock) && ((tActor.getColor()).equals(Color.RED))) {
				isEnd = true;
			}
			
			moveTo(next);
			
			int heading = loc.getDirectionToward(next);
			this.setDirection(heading);
			
			ArrayList<Location> topList = crossLocation.pop();
			topList.add(next);
			crossLocation.push(topList);
			
			ArrayList<Location> latestList = new ArrayList<Location>();
			latestList.add(next);
			crossLocation.push(latestList);
		} else {
			removeSelfFromGrid();
		}
		
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
	
	// this method is to init if the stepCount == 0 
	private void init(Location loc) {
		// TODO Auto-generated method stub
		ArrayList<Location> initList = new ArrayList<Location>();
		initList.add(loc);
		crossLocation.add(initList);
	}
	
}






