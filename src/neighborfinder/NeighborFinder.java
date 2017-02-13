package neighborfinder;

import java.util.ArrayList;
import java.util.List;

/**
 * Takes the x, y coordinates of a cell in the grid and
 * returns potential neighbors as a list of coordinates.
 */
public abstract class NeighborFinder {
	
	private List<int[]> neighborLocations;
	
	/**
	 * Creates a <code>NeighborFinder</code>
	 * @param r
	 * @param c
	 */
	public NeighborFinder(int r, int c) {
		neighborLocations = new ArrayList<>();
	}
	
	/**
	 * Different cell shapes will result in different ways to 
	 * find neighbors.
	 */
	public abstract void findNeighbors();
	
	/**
	 * Remove corners if necessary, depending on the simulation.
	 */
	public abstract void removeCorners();
	
	/**
	 * Checks if <code>arr</code> is at index (<code>r, c</code>)
	 * @param arr Array to check
	 * @param r Row to check
	 * @param c Column to check
	 * @return Whether or not <code>arr</code> is at index (<code>r, c</code>) 
	 */
	protected boolean sameLocation(int[] arr, int r, int c){
		return (arr[0]==r && arr[1]==c);
	}
	
	/**
	 * Gets a <code>List</code> of all of the neighbors' locations
	 * @return Gets a <code>List</code> of all of the neighbors' locations
	 */
	public List<int[]> getNeighborLocations(){
		return neighborLocations;
	}
}
