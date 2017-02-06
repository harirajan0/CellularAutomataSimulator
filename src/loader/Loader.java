package loader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import cells.Cell;
import grid.SquareGrid;

public class Loader {
	
    public static final String SIMULATION_TYPE = "simulationType";
    public static final String SIMULATION_NAME = "simulationName";
    public static final String NUM_ROWS = "numRows";
    public static final String NUM_COLUMNS = "numCols";
    public static final String PERCENT_STATE_ONE = "percentState1";
    public static final String PERCENT_STATE_TWO = "percentState2";
    public static final String PERCENT_STATE_THREE = "percentState3";
    public static final String PARAM = "param";
    
    public static final String EMPTY = "empty";
    public static final String ALIVE = "alive";
    public static final String DEAD = "dead";
    private static final String BURNING = "burning";
	private static final String TREE = "tree";
    public static final String SHARK = "shark";
    public static final String FISH = "fish";
    
    private XMLParser myParser;
    
    private HashMap<String, List<String>> simulationMap;
    
    private String simulationType;
    private int rows;
    private int cols;
    private SquareGrid myGrid;
    private List<Integer> myStates;
    
	public Loader(String fileName) {
		setupSimulationMap();
		myParser = new XMLParser(fileName);
		simulationType = myParser.getTextValue(SIMULATION_TYPE);
		rows = Integer.valueOf(myParser.getTextValue(NUM_ROWS));
		cols = Integer.valueOf(myParser.getTextValue(NUM_COLUMNS));
		initializeGrid();
		
	}
    
	/**
	 * 
	 */
	private void setupSimulationMap() {
		simulationMap = new HashMap<>();
		simulationMap.put("SpreadingFire", Arrays.asList(new String[] {EMPTY, BURNING, TREE}));
		simulationMap.put("Segregation", Arrays.asList(new String[] {EMPTY}));
		simulationMap.put("GameOfLife", Arrays.asList(new String[] {ALIVE, DEAD}));
		simulationMap.put("PreditorPrey", Arrays.asList(new String[] {EMPTY, SHARK, FISH}));
		
	}

	private void initializeGrid() {
		myGrid = new SquareGrid(rows, cols);
		for (Cell cell : myGrid) {
			
			cell = new Con
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
}
