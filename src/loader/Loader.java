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
    
    public static final String EMPTY = "empty";
    public static final String ALIVE = "alive";
    public static final String DEAD = "dead";
    private static final String BURNING = "burning";
	private static final String TREE = "tree";
    public static final String SHARK = "shark";
    public static final String FISH = "fish";
    
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
		setupSimulationMap();
		myParser = new XMLParser(fileName);
		simulationType = myParser.getTextValue(SIMULATION_TYPE);
		rows = Integer.valueOf(myParser.getTextValue(NUM_ROWS));
		cols = Integer.valueOf(myParser.getTextValue(NUM_COLUMNS));
		param = Double.valueOf(myParser.getTextValue(PARAM));
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
		int i = 0;
		switch(simulationType){
			case SPREADING_FIRE:
				for (Cell cell : myGrid) {
					cell = new SpreadingFireCell(myParser.getTextValue("state" + Integer.toString(i)), param);
					i++;
				}
				break;
			case WATOR : 
				for (Cell cell : myGrid) {
					cell = new WaTorCell(myParser.getTextValue("state" + Integer.toString(i)));
					i++;
				}
				break;
			case CONWAY : 
				for (Cell cell : myGrid) {
					cell = new ConwayCell(myParser.getTextValue("state" + Integer.toString(i)));
					i++;
				}
				break;
			case SEGREGATION : 
				for (Cell cell : myGrid) {
					cell = new SegregationCell(myParser.getTextValue("state" + Integer.toString(i)), i, i, param);
					i++;
				}
				break;
			default : 
					break;
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
}
