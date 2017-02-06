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
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import loader.Loader;

// This controller class is the central nexus control of the entire program.
// It will handle things like when to update the model, when to update the view,
// this class holds the cell simulation togethers

public class Controller {
	
	// Dimension of the Grid, obtained from Loader
	private int rows, cols;
	// Grid instance variable
	private Grid myGrid;
	// Controller holds View in order to update it.
	private SimulationView cellSimulationDisplay;
	
	private static final int default_fps = 60;
	
	private static final String SPREADING_FIRE = "FIRE";
	private static final String WATOR = "WATOR";
	private static final String SEGREGATION = "SEGREGATION";
	private static final String CONWAY = "CONWAY";
	private int fps;
	private int mil_delay;
	private double sec_delay;
	private Timeline animation;
	
	
	/**
	 * Constructor for the Controller
	 * Sets view for the instance View in Controller.
	 * Can be called in GUI multiple times to set up different views.
	 * @param view
	 */
	public Controller(SimulationGUI gui){
//		myCellView = view;
		cellSimulationDisplay = gui.getSimulationView();
//		stage = s;
		fps = default_fps;
		mil_delay = 1000/fps;
		sec_delay = 1.0/fps;
		
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

	// this should be for starting a new simulation maybe? still need to look into it
	public void start() {
		KeyFrame frame = new KeyFrame(Duration.millis(mil_delay),
				e -> step());
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
		
	}
	
	// this should be for playing a paused simulation? maybe... rip idk if this actually works.
	// just a guess.
	public void resume(){
		animation.play();
	}

	public void pause() {
		// not sure if this is how you would stop the animation, but maybe this would work.
		animation.pause();
	}
	
	// oh this step is currently gonna be used for the loop. not sure if we need to make a seperate
	// step for the step button or if we can use the same one.  
	public void step() {
		// update model
		// update view
		// this method should e less than 5 lines. probably just 2 method calls will already be enough,
		// but we'll see.
	}

	public void reset() {
		// not sure if this is actually how you would stop the animation, but maybe this would work.
		animation.stop();
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