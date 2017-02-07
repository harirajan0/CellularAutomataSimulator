package model;

import java.util.ArrayList;
import java.util.List;
import cells.Cell;

public class SquareModel extends Model {

	public SquareModel(int r, int c) {
		super(r, c);
	}

	@Override
	public void initializeNeighbors() {
		for(int r = 0; r < getRows(); r++){
			for(int c = 0; c < getCols(); c++){
				List<Cell> neighbors = new ArrayList<Cell>();
				System.out.println("hi");
				for(int horiz = -1; horiz <= 1; horiz += 2){
					for(int vert = -1; vert <= 1; vert += 2){
						
						//Iterate through neighbors, add if not outside array
						if(contains(r + horiz, c + vert)){
							neighbors.add(get(r + horiz, c + vert));
						}
					}
				}
				get(r, c).setNeighbors(neighbors);
			}
		}
	}
	
	public void updateModel() {
		for(int r = 0; r < getRows(); r++){
			for(int c = 0; c < getCols(); c++){
				get(r, c).update();
			}
		}
		
		for(int r = 0; r < getRows(); r++){
			for(int c = 0; c < getCols(); c++){
				get(r, c).nextGeneration();
				get(r, c).paint();
			}
		}
	}
}
