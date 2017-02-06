package grid;

import java.util.ArrayList;
import java.util.List;
import cells.Cell;

public class SquareGrid extends Grid {

	public SquareGrid(int r, int c) {
		super(r, c);
	}

	@Override
	public void initializeNeighbors() {
		for(int r = 0; r < getRows(); r++){
			for(int c = 0; c < getCols(); c++){
				List<Cell> neighbors = new ArrayList<Cell>();
				
				for(int horiz = -1; horiz <= 1; horiz += 2){
					for(int vert = -1; vert <= 1; vert += 2){
						
						//Iterate through neighbors, add if not outside array
						if(contains(r + horiz, r + vert)){
							neighbors.add(get(r + horiz, c + vert));
						}
					}
				}
				get(r, c).setNeighbors(neighbors);
			}
		}
	}
	
	public void updateGrid() {
		for(int r = 0; r < getRows(); r++){
			for(int c = 0; c < getCols(); c++){
				get(r, c).update();
			}
		}
		
		for(int r = 0; r < getRows(); r++){
			for(int c = 0; c < getCols(); c++){
				get(r, c).nextGeneration();
			}
		}
	}
}
