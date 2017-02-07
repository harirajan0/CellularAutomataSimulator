package loader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import cells.Cell;
import cells.ConwayCell;
import cells.SegregationCell;
import cells.SpreadingFireCell;
import cells.WaTorCell;
import main.ApplicationStartup;
import model.Model;
import model.SquareModel;

public class Loader {
	
    public static final String SIMULATION_TYPE = "simulationType";
    public static final String SIMULATION_NAME = "simulationName";
    public static final String NUM_ROWS = "numRows";
    public static final String NUM_COLUMNS = "numCols";
    public static final String PERCENT_STATE_ONE = "percentState1";
    public static final String PERCENT_STATE_TWO = "percentState2";
    public static final String PERCENT_STATE_THREE = "percentState3";
    public static final String PARAM = "param";
    
	private static final String SPREADING_FIRE = "FIRE";
	private static final String WATOR = "WATOR";
	private static final String SEGREGATION = "SEGREGATION";
	private static final String CONWAY = "CONWAY";
    
    private XMLParser myParser;
    
    private HashMap<String, List<String>> simulationMap;
    
    private String simulationType;
    private int rows;
    private int cols;
    private double param;
    private SquareModel myGrid;
    
	public Loader(String fileName) {
		myParser = new XMLParser(fileName);
		simulationType = myParser.getTextValue(SIMULATION_TYPE);
		rows = Integer.valueOf(myParser.getTextValue(NUM_ROWS));
		cols = Integer.valueOf(myParser.getTextValue(NUM_COLUMNS));
		param = Double.valueOf(myParser.getTextValue(PARAM));
		initializeGrid();
		
	}
    
	/**
	 * From the list of states, the probability, and simulation type,
	 * construct a list of cells to be passed in to the Grid later.
	 * @param states
	 * @param param
	 * @param sim
	 * @return
	 */
	private void initializeGrid(){
		myGrid = new SquareModel(rows, cols);
		int cellNum = 0;
		int sideLength = ApplicationStartup.WINDOW_SIZE / Math.max(rows, cols);
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				int xPosition = row * sideLength;
				int yPosition = col * sideLength;
				switch(simulationType){
				case SPREADING_FIRE: 
					myGrid.set(row, col, new SpreadingFireCell(myParser.getTextValue("state" + Integer.toString(cellNum)), xPosition, yPosition, param));
					break;
				case WATOR : 
					myGrid.set(row, col, new WaTorCell(myParser.getTextValue("state" + Integer.toString(cellNum)), xPosition, yPosition));
					break;
				case CONWAY :
					myGrid.set(row, col, new ConwayCell(myParser.getTextValue("state" + Integer.toString(cellNum)), xPosition, yPosition));
					break;
				case SEGREGATION : 
					myGrid.set(row, col, new SegregationCell(myParser.getTextValue("state" + Integer.toString(cellNum)), xPosition, yPosition, param));
					break;
				default : break;
			}
			}
		}
	}
	
	public String getSimulationType(){
		return myParser.getTextValue(SIMULATION_TYPE);
	}
	
	public int getRows(){
		return Integer.valueOf(myParser.getTextValue(NUM_ROWS));
	}
	
	public int getCols(){
		return Integer.valueOf(myParser.getTextValue(NUM_COLUMNS));
	}
	
	public double getParameter(){
		return Double.valueOf(myParser.getTextValue(PARAM));
	}

	/**
	 * @return
	 */
	public List<String> getStates() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return
	 */
	public Model getFirstGrid() {
		// TODO Auto-generated method stub
		return myGrid;
	}
}
