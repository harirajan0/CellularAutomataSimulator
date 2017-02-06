package main;

import grid.Grid;

// purpose of the Model is to hold the cells and have methods for updating the cells (iteration)
// and for (maybe?) some sort of thing that returns data to be passed from the controller to the
// display.
public class Model {
	
	private Grid simulationGrid;

	public Model(){
		
	}
	
	public void updateModel(){
		// an iterator that just calls updateCell on all of the Cells
	}
	
	public Grid getGrid(){
		return simulationGrid;
	}
}
