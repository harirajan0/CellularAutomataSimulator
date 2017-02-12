package neighborfinder;


public class HexagonNeighborFinder extends NeighborFinder {
	
	private int row, col;

	public HexagonNeighborFinder(int r, int c) {
		super(r, c);
		row = r;
		col = c;
	}

	@Override
	public void findNeighbors() {
		
		for (int i=row-1; i<=row+1; i++){
			for (int j=col-1; j<=col+1; j++){
				// skip itself
				if (i==row && j==col) continue;
				// odd row
				if (row%2==1){
					if ((i==row-1 || i==row+1) && j==col-1) continue;
				}
				// even row
				else{
					if ((i==row-1 || i==row+1) && j==col+1) continue;
				}
				this.getNeighborLocations().add(new int[]{i, j});
			}
		}
	}

	@Override
	public void removeCorners() {}

}
