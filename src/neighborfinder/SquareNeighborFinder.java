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
	public void findNeighbors() {
		for (int i=row-1; i<=row+1; i++){
			for (int j=col-1; j<=col+1; j++){
				// skip itself
				if (i==row && j==col) continue;
				this.getNeighborLocations().add(new int[]{i, j});
			}
		}
	}

	@Override
	public void removeCorners() {
		List<int[]> removeList = new ArrayList<>();
		for (int[] arr : this.getNeighborLocations()){
			if (sameLocation(arr, row-1, col) || sameLocation(arr, row+1, col) ||
					sameLocation(arr, row, col-1) || sameLocation(arr, row, col+1)){
				continue;
			}
			removeList.add(arr);	
		}
		this.getNeighborLocations().removeAll(removeList);
	}
}
