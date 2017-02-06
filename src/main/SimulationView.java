package main;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import model.Model;

public class SimulationView {
	//beginning work on GUI 2/2/2017
	public static final int SIMULATION_SIZE = 400;
	
	private Model myGrid;
	private Pane cellSimulationPane;
	
	public SimulationView(){
		cellSimulationPane = new Pane();
		cellSimulationPane.setMaxSize(400, 400);
//		cellSimulationPane.setStyle("-fx-background-color: black;");
		
	}
	
	public void setGrid(Model grid){
		myGrid = grid;
	}
	
	public Pane getCellSimulationPane(){
		return cellSimulationPane;
	}
	

	/**!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * This is just a placeholder method. Once we have
	 * the Grid class we should change the parameter.
	 * Takes in the grid and display it in the GUI.
	 * @param grid
	 * @return
	 */
	public Node displayGrid(Model grid) {
		return null;
	}

}
