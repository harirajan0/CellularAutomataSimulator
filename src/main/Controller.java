package main;
import java.util.Collections;
import java.util.List;

import cells.Cell;
import cells.ConwayCell;
import cells.SegregationCell;
import cells.SpreadingFireCell;
import cells.WaTorCell;
import loader.Loader;

public class Controller {
	
	private Cell[][] grid;
	private int rows;
	private int cols;
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
	
	
	private void createCells(List<String> states, double param, String sim)
	{
		for(int r = 0; r < rows; r++){
			for(int c = 0; c < cols; c++){
				int xcor = View.WINDOW_SIZE/rows*r;
				int ycor = View.WINDOW_SIZE/cols*c;
				switch(sim){
					case SPREADING_FIRE:
						grid[r][c] = new SpreadingFireCell(states.remove(0), xcor, ycor, param);
						break;
					case WATOR : 
						grid[r][c] = new WaTorCell(states.remove(0),xcor, ycor);
						break;
					case CONWAY : 
						grid[r][c] = new ConwayCell(states.remove(0), xcor, ycor);
						break;
					case SEGREGATION : 
						grid[r][c] = new SegregationCell(states.remove(0), xcor, ycor, param);
						break;
					default : 
							break;
				}
			}
		}
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

	public Object start() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object pause() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object step() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object reset() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object load(String filename) {
		Loader l = new Loader(filename);
		rows = l.getRows();
		cols = l.getCols();
		
		List<String> states = l.getStates ();
		Collections.shuffle(states);
		grid = new Cell[rows][cols];
		createCells(states, l.getParameter(), l.getSimulationType());
		return null;
	}

	public Object changeSpeed() {
		// TODO Auto-generated method stub
		return null;
	}

	/** !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * Should change once we have the Grid class set up.
	 * Returns the grid in order to pass it to View in GUI.
	 * @return
	 */
	public Cell[][] getGrid() {
		return grid;
	}

	
}