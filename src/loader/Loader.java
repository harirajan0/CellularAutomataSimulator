package loader;

import java.io.File;
import java.util.List;

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
    public static final String PARAM = "param";
    
	private static final String SPREADING_FIRE = "FIRE";
	private static final String WATOR = "WATOR";
	private static final String SEGREGATION = "SEGREGATION";
	private static final String CONWAY = "CONWAY";
    
    private XMLParser myParser;
        
    private String simulationType;
    private int rows;
    private int cols;
    private double param;
    private SquareModel myGrid;
    
	public Loader(File file) {
		myParser = new XMLParser(file);
		simulationType = myParser.getTextValue(SIMULATION_TYPE);
		rows = Integer.valueOf(myParser.getTextValue(NUM_ROWS));
		cols = Integer.valueOf(myParser.getTextValue(NUM_COLUMNS));
		param = Double.valueOf(myParser.getTextValue(PARAM));
		initializeGrid();
		
	}
    
	/**
	 * From the list of states, the probability, and simulation type,
	 * construct a list of cells to be passed in to the Grid later.
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
					myGrid.set(row, col, new SpreadingFireCell(myParser.getTextValue("state" + Integer.toString(cellNum)), xPosition, yPosition, sideLength, param));
					break;
				case WATOR : 
					myGrid.set(row, col, new WaTorCell(myParser.getTextValue("state" + Integer.toString(cellNum)), xPosition, yPosition, sideLength));
					break;
				case CONWAY :
					myGrid.set(row, col, new ConwayCell(myParser.getTextValue("state" + Integer.toString(cellNum)), xPosition, yPosition, sideLength));
					break;
				case SEGREGATION : 
					myGrid.set(row, col, new SegregationCell(myParser.getTextValue("state" + Integer.toString(cellNum)), xPosition, yPosition, sideLength, param));
					break;
				default : break;
			}
			}
		}
	}
	
	/**
	 * Gets the type of simulation
	 * @return The simulation type
	 */
	public String getSimulationType(){
		return myParser.getTextValue(SIMULATION_TYPE);
	}
	
	/**
	 * Gets the number of rows in the grid
	 * @return Number of rows in the grid
	 */
	public int getRows(){
		return Integer.valueOf(myParser.getTextValue(NUM_ROWS));
	}
	
	/**
	 * Get the number of columns in the grid
	 * @return Number of columns in the grid
	 */
	public int getCols(){
		return Integer.valueOf(myParser.getTextValue(NUM_COLUMNS));
	}
	
	/**
	 * Gets the additional simulation parameter
	 * @return The additional simulation parameter
	 */
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
