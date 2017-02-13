package neighborfinder;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Takes the x, y coordinates of a cell in the grid and
 * returns potential neighbors as a list of coordinates.
 * @author Bihan Zhuang
 *
 */
public abstract class NeighborFinder {
	
	private List<int[]> neighborLocations;
	
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
	
	
	protected boolean sameLocation(int[] arr, int r, int c){
		return (arr[0]==r && arr[1]==c);
	}
	
	public List<int[]> getNeighborLocations(){
		return neighborLocations;
	}
}
