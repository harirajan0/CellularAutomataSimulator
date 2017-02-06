package main;

import grid.Grid;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class SimulationView {
	//beginning work on GUI 2/2/2017
	public static final int WINDOW_SIZE = 400;
	
	private Grid myGrid;
	private Pane cellSimulationPane;
	
	public SimulationView(){
		cellSimulationPane = new Pane();
		
	}
	
	public void setGrid(Grid grid){
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
	public Node displayGrid(Grid grid) {
		return null;
	}

}
