
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

/**
 * A <code>BoundedGrid</code> is a rectangular grid with a finite
 * number of rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */ 
public class SparseBoundedGrid<E> extends AbstractGrid<E> {
    private int row;
    private int col;
    // a array to save the grid elements 
    private SparseGridNode[] occupantArray;
    
    /*
     * constructs with row and col, row for the row of the grid world,
     * col for the col of the grid world 
     */
    public SparseBoundedGrid(int t_row, int t_col) {
    	if (t_row <= 0 || t_col <= 0) {
    		throw new IllegalArgumentException("Row or column is below 0!");
    	}
    	
    	row = t_row;
    	col = t_col;
    	occupantArray = new SparseGridNode[row];	
    }
    
 
    public boolean isValid(Location testLoc) {
    	int testRow = testLoc.getRow();
    	int testCol = testLoc.getCol();
    	if ((testRow < 0 || testRow >= row) || (testCol < 0 || testCol >= col)) {
    		return false;
    	}
        
    	return true;
    }
    
    /*
     * override the the put method to put the obj into the loc
     */
    public E put(Location loc, E obj) {
    	if (!isValid(loc) || obj == null) {
    		throw new IllegalArgumentException("Location is out of grid or object is null!");
    	}
    	// remove the preObj in the loc
    	E preObj = remove(loc);
    	
    	//add the new obj into the array with its row for the index
    	SparseGridNode node = occupantArray[loc.getRow()];
    	occupantArray[loc.getRow()] = new SparseGridNode(obj, loc.getCol(), node);
    	
    	return preObj;
    }
    
    /*
     * (non-Javadoc)
     * @see info.gridworld.grid.Grid#remove(info.gridworld.grid.Location)
     * override the remove method to remove the obj in the location and then return the obj
     */
    public E remove(Location loc) {
    	if (!isValid(loc)) {
    		throw new IllegalArgumentException("The location is beyond to the grid!");
    	}
    	
    	E removeObj = get(loc);
    	if (removeObj == null) {
    		return null;
    	}
    	
    	// get the list in the row index
    	SparseGridNode node = occupantArray[loc.getRow()];
    	
    	if (node != null) {
    		// first check the first node
    		if (node.getCol() == loc.getCol()) {
    			occupantArray[loc.getRow()] = node.getNext();
    		}
    		else {
    			SparseGridNode nextNode = node.getNext();
    			while (nextNode != null && nextNode.getCol() != loc.getCol()) {
    				// refresh node and nextNode
    				node = node.getNext();
    				nextNode = nextNode.getNext();
    			}
    			
    			// if exist, the nextNode can no be null
    			if (nextNode != null) {
    				node.setNext(nextNode.getNext());
    			}
    		}
    	}
    	
    	return removeObj;
    }
    
    /*
     * (non-Javadoc)
     * @see info.gridworld.grid.Grid#get(info.gridworld.grid.Location)
     * override the get mothod to get the obj in loc
     */
    public E get(Location loc) {
    	if (!isValid(loc)) {
    		throw new IllegalArgumentException("The location is beyond to the grid!");
    	}
    	
    	// get the list in the row index
    	SparseGridNode node = occupantArray[loc.getRow()];
    	while (node != null) {
    		if (node.getCol() == loc.getCol()) {
    			E occupant = (E)node.getOccupant();
    			return occupant;
    		}
    		node = node.getNext();
    	}
    	
    	return null;
    }
    
    /*
     * (non-Javadoc)
     * @see info.gridworld.grid.Grid#getOccupiedLocations()
     * override it to get the occupied locations in the OccupantArray
     */
    public ArrayList<Location> getOccupiedLocations() {
    	ArrayList<Location> locArray = new ArrayList<Location>();
    	for (int i = 0; i < row; i ++) {
    		// get the list by the row index
    		SparseGridNode node = occupantArray[i];
    		while (node != null) {
    			Location loc = new Location(i, node.getCol());
    			locArray.add(loc);
    			node = node.getNext();
    		}
    	}
    	return locArray;
    }

	@Override
	public int getNumCols() {
		// TODO Auto-generated method stub
		return col;
	}

	@Override
	public int getNumRows() {
		// TODO Auto-generated method stub
		return row;
	}
}
