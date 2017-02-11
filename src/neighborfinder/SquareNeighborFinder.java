package neighborfinder;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Bihan Zhuang
 *
 */
public class SquareNeighborFinder extends NeighborFinder {
	
	private int row, col;

	public SquareNeighborFinder(int r, int c) {
		super(r, c);
		row = r;
		col = c;
	}

	/**
	 * Find neighbors for one square cell and returns its neighbors
	 * in a list of x,y coordinates.
	 */
	@Override
	public List<int[]> findNeighbors() {
		List<int[]> neighborLocations = new ArrayList<>();
		for (int i=row-1; i<=row+1; i++){
			for (int j=col-2; j<=col+2; j++){
				// skip itself
				if (i==row && j==col) continue;
				int[] tmp = new int[]{i, j};
				neighborLocations.add(tmp);
			}
		}
		return neighborLocations;
	}

}
