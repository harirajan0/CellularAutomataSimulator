package main;

import javafx.scene.layout.Pane;
import model.Model;

public class SimulationView {
	//beginning work on GUI 2/2/2017
	public static final int SIMULATION_SIZE = 400;
	
	private Pane cellSimulationPane;
	
	public SimulationView(){
		cellSimulationPane = new Pane();
		cellSimulationPane.setMaxSize(400, 400);
//		cellSimulationPane.setStyle("-fx-background-color: black;");
		
	}
	
	public Pane getCellSimulationPane(){
		return cellSimulationPane;
	}
	

	/**!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * This is just a placeholder method. Once we have
	 * the Grid class we should change the parameter.
	 * Takes in the grid and display it in the GUI.
	 * @param grid
	 */
	
	public void displayGrid(Model grid) {
		for(int r = 0; r < grid.getRows(); r++){
			for(int c = 0; c < grid.getCols(); c++){
				cellSimulationPane.getChildren().add(grid.get(r, c).getRect());
			}
		}
	}

}
