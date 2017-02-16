// This entire file is part of my masterpiece.
// Bihan Zhuang

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
	 * @return 
	 */
	@Override
	public List<Location> findNeighbors() {
		
		for (int i=row-1; i<=row+1; i++){
			for (int j=col-2; j<=col+2; j++){
				// skip itself
				if (i==row && j==col) continue;
				if (sameParity()){
					if (i==row+1 && (j==col-2 || j==col+2)) continue;
				} else{
					if (i==row-1 && (j==col-2 || j==col+2)) continue;
				}
				this.getNeighborLocations().add(new Location(i,j));
			}
		}
		return this.getNeighborLocations();
	}

	@Override
	public void removeCorners() {
		List<Location> removeList = new ArrayList<>();
		for (Location location : this.getNeighborLocations()){
			if (sameParity()){
				if (sameLocation(location, row-1, col) 
						|| sameLocation(location, row, col-1) 
						|| sameLocation(location, row, col+1)){
					continue;
				}
			} else{
				if (sameLocation(location, row+1, col) 
						|| sameLocation(location, row, col-1) 
						|| sameLocation(location, row, col+1)){
					continue;
				}
			}
			removeList.add(location);	
		}
		this.getNeighborLocations().removeAll(removeList);
	}
	
	 /**
	  * (odd row, odd col) OR (even row, even col)
	  * @return
	  */
	private boolean sameParity(){
		return (row%2==1 && col%2==1) || (row%2==0&&col%2==0);
	}

}
