

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

/**
 * A <code>SparseBoundedGrid2</code> is a rectangular grid with an
 * unbounded number of rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB Exam.
 */
public class SparseBoundedGrid2<E> extends AbstractGrid<E> {
	private int row;
    private int col;
	private Map<Location, E> occupantMap;     // to store the object with its location
    
	/*
	 * Constructs with row and col
	 */
	public SparseBoundedGrid2(int r, int c) {
		occupantMap = new HashMap<Location, E>();
		row = r;
		col = c;
	}
	
	/*
	 * get the number of row
	 */
	public int getNumRows() {
		return row;
	}
	
	/*
	 * get the number of col
	 */
	public int getNumCols() {
		return col;
	}
	
	/*
	 * judge if valid
	 */
	public boolean isValid(Location loc) {
		return 0 <= loc.getRow() && loc.getRow() < getNumRows()
			   && 0 <= loc.getCol() && loc.getCol() < getNumCols(); 
	}
	
	/*
	 * get the object in the given location
	 */
	public E get(Location loc) {
		if (loc == null) {
			throw new NullPointerException("loc can not be null");
		}
		return occupantMap.get(loc);
	}
	
	/*
	 * put an object in the given location
	 */
	public E put(Location loc, E obj) {
		if (loc == null) {
			throw new NullPointerException("loc can not be null");
		}
		if (obj == null) {
			throw new NullPointerException("object can not be null");
		}
		return occupantMap.put(loc, obj);
	}
	
	/*
	 * remove the object in the given location
	 */
	public E remove(Location loc) {
		if (loc == null) {
			throw new NullPointerException("loc can not be null");
		}
		return occupantMap.remove(loc);
	}
	
	/*
	 * get the occupied locations
	 */
	public ArrayList<Location> getOccupiedLocations(){
		ArrayList<Location> occupiedArray = new ArrayList<Location>();
		// traverse the map to add the occupied locations
		for (Location loc : occupantMap.keySet()) {
			occupiedArray.add(loc);
		}
		return occupiedArray;
	}
}
