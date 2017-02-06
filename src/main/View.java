package main;

import java.util.List;

import cells.Cell;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class View {
	//beginning work on GUI 2/2/2017
	// GUI handles the main Scene. View will have a Pane, as well as other methods for handling displaying cell.
	private static final int WINDOW_SIZE = 400;
	private Pane cellGridView;
	
	public View(){
		cellGridView = new Pane();
	}
	
	public void gridViewStep(){
		
	}
	
	public void displayGrid(List<Cell> grid) {
		
	}

	/**!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * This is just a placeholder method. Once we have
	 * the Grid class we should change the parameter.
	 * Takes in the grid and display it in the GUI.
	 * @param grid
	 * @return
	 */
	public Node displayGrid(Cell[][] grid) {
		return null;
	}

}
