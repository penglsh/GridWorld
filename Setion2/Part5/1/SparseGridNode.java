public class SparseGridNode {
	private Object occupant;
	private int col;
	private SparseGridNode next;
	
	/*
	 * Constructs with ...
	 */
	public SparseGridNode(Object occup, int c, SparseGridNode n) {
		occupant = occup;
		col = c;
		next = n;
	}
	
	/*
	 * get the occupant
	 */
	public Object getOccupant() {
		return occupant;
	}
	
	/*
	 * get the col
	 */
	public int getCol() {
		return col;
	}
	
	/*
	 * get next node
	 */
	public SparseGridNode getNext() {
		return next;
	}
	
	/*
	 * set next
	 */
	public void setNext(SparseGridNode n) {
		next =n;
	}
	
	/*
	 * set the occupant
	 */
	public void setOccupant(Object occup) {
		occupant = occup;
	}
}