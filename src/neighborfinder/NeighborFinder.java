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
	
	public NeighborFinder(int r, int c) {}
	
	/**
	 * Different cell shapes will result in different ways to 
	 * find neighbors.
	 */
	public abstract List<int[]> findNeighbors();	
}
