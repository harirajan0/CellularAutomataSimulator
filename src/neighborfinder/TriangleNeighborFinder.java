package neighborfinder;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Bihan Zhuang
 *
 */
public class TriangleNeighborFinder extends NeighborFinder {
	
	private int row, col;

	public TriangleNeighborFinder(int r, int c) {
		super(r, c);
		row = r;
		col = c;
	}

	/**
	 * Find neighbors for one triangular cell and returns its neighbors
	 * in a list of x,y coordinates.
	 */
	@Override
	public List<int[]> findNeighbors() {
		List<int[]> neighborLocations = new ArrayList<>();
		
		for (int i=row-1; i<=row+1; i++){
			for (int j=col-2; j<=col+2; j++){
				// skip itself
				if (i==row && j==col) continue;
				// (odd row, odd col) OR (even row, even col)
				if ((row%2==1 && col%2==1) || (row%2==0&&col%2==0)){
					if (i==row+1 && (j==col-2 || j==col+2)) continue;
				}
				// (odd row, even col) OR (even row, odd col)
				else{
					if (i==row-1 && (j==col-2 || j==col+2)) continue;
				}
				int[] tmp = new int[]{i, j};
				neighborLocations.add(tmp);
			}
		}
		return neighborLocations;
	}

}
