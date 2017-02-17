// This entire file is part of my masterpiece.
// Bihan Zhuang

package neighborfinder;

import java.util.ArrayList;
import java.util.List;

/**
 * Takes the x, y coordinates of a cell in the grid and
 * returns potential neighbors as a list of coordinates.
 */
public abstract class NeighborFinder {
	
	// This private instance is needed because the removeCorners 
	// method will need to access the list after the list is generated 
    // in certain types of simulations, such as Spreading Fire.
	private List<Location> neighborLocations;
	
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
	public abstract List<Location> findNeighbors();
	
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
	protected boolean sameLocation(Location location, int r, int c){
		return (location.getX()==r && location.getY()==c);
	}
	
	/**
	 * Gets a <code>List</code> of all of the neighbors' locations
	 * @return Gets a <code>List</code> of all of the neighbors' locations
	 */
	public List<Location> getNeighborLocations(){
		return neighborLocations;
	}
}
