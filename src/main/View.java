package main;

import java.util.List;

import cells.Cell;
import grid.Grid;
import javafx.scene.Node;

public class View {
	//beginning work on GUI 2/2/2017
	public static final int WINDOW_SIZE = 400;
	
	private Grid myGrid;
	
	public View(){
		
	}
	
	public void setGrid(Grid grid){
		myGrid = grid;
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
