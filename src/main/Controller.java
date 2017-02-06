package main;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cells.Cell;
import cells.ConwayCell;
import cells.SegregationCell;
import cells.SpreadingFireCell;
import cells.WaTorCell;
import grid.Grid;
import grid.SquareGrid;
import loader.Loader;

public class Controller {
	
	// Dimension of the Grid, obtained from Loader
	private int rows, cols;
	// Grid instance variable
	private Grid myGrid;
	// Controller holds View in order to update it.
	private View myCellView;
	
	private static final String SPREADING_FIRE = "FIRE";
	private static final String WATOR = "WATOR";
	private static final String SEGREGATION = "SEGREGATION";
	private static final String CONWAY = "CONWAY";
	
	/**
	 * Constructor for the Controller
	 * Sets view for the instance View in Controller.
	 * Can be called in GUI multiple times to set up different views.
	 * @param view
	 */
	public Controller(View view){
		myCellView = view;
	}
	
	/**
	 * From the list of states, the probability, and simulation type,
	 * construct a list of cells to be passed in to the Grid later.
	 * @param states
	 * @param param
	 * @param sim
	 * @return
	 */
	private List<Cell> createCells(List<String> states, double param, String sim){
		List<Cell> result = new ArrayList<Cell>();
		switch(sim){
			case SPREADING_FIRE:
				for (int i=0; i<rows*cols; i++){
					result.add(new SpreadingFireCell(states.remove(0), param));
				}
				break;
			case WATOR : 
				for (int i=0; i<rows*cols; i++){
					result.add(new WaTorCell(states.remove(0)));
				}
				break;
			case CONWAY : 
				for (int i=0; i<rows*cols; i++){
					result.add(new ConwayCell(states.remove(0)));
				}
				break;
			case SEGREGATION : 
				for (int i=0; i<rows*cols; i++){
					result.add(new SegregationCell(states.remove(0), param));
				}
				break;
			default : 
					break;
		}
		return result;
	}
	
	
	/* Needs to change once the Grid class is written
	public void updateGrid() {
		
		for(int r = 0; r < rows; r++){
			for(int c = 0; c < cols; c++){
				
				grid[r][c].update();
			}
		}
		
		for(int r = 0; r < rows; r++){
			for(int c = 0; c < cols; c++){
				
				grid[r][c].nextGeneration();
			}
		}
	}*/

	public void start() {
	}

	public void pause() {
	}

	public void step() {
	}

	public void reset() {
	}

	/**
	 * This method will be called in GUI once the user clicks the Load button.
	 * @param filename
	 * @return
	 */
	public void load(String filename) {
		Loader l = new Loader(filename);
		rows = l.getRows();
		cols = l.getCols();
		
		// List of states to shuffle 
		List<String> states = l.getStates();
		Collections.shuffle(states);
		
		// Create a square grid for our purpose here
		myGrid = new SquareGrid(rows, cols);
		// Create a list of cells based on simulation
		List<Cell> cellList = createCells(states, l.getParameter(), l.getSimulationType());
		// Pass the cell list to grid so that grid can make a 2D array
		// We want to do this in order to hide the implementation of 2D array from the world
		myGrid.buildGrid(cellList);
	}

	public void changeSpeed() {
	}

	/** !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * Should change once we have the Grid class set up.
	 * Returns the grid in order to pass it to View in GUI.
	 * @return
	 */
	public Grid getGrid() {
		return myGrid;
	}

	
}