// Written by Bihan Zhuang

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
	public List<Location> findNeighbors() {
		for (int i=row-1; i<=row+1; i++){
			for (int j=col-1; j<=col+1; j++){
				// skip itself
				if (i==row && j==col) continue;
				this.getNeighborLocations().add(new Location(i, j));
			}
		}
		return this.getNeighborLocations();
	}

	@Override
	public void removeCorners() {
		List<Location> removeList = new ArrayList<>();
		for (Location location : this.getNeighborLocations()){
			if (sameLocation(location, row-1, col) || sameLocation(location, row+1, col) ||
					sameLocation(location, row, col-1) || sameLocation(location, row, col+1)){
				continue;
			}
			removeList.add(location);	
		}
		this.getNeighborLocations().removeAll(removeList);
	}
}
