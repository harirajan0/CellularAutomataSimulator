package main;

import grid.Grid;

// purpose of the Model is to hold the cells and have methods for updating the cells (iteration)
// and for (maybe?) some sort of thing that returns data to be passed from the controller to the
// display.
public class Model {
	
	private Grid simulationGrid;

	public Model(Grid grid){
		simulationGrid = grid;
	}
	
	
	public void updateModel(){
		for (int i=0; i<simulationGrid.getRow(); i++){
			for (int j=0; j<simulationGrid.getCol(); j++){
			}
		}
	}
	
	public Grid getGrid(){
		return simulationGrid;
	}
}
