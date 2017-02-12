package loader;

import java.io.File;

import alerts.CellSocietyAlerts;
import model.ConwayModel;
import model.Model;
import model.SegregationModel;
import model.SpreadingFireModel;
import model.WaTorModel;
import resources.Resources;

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
	private Model myModel;

	public Loader(File file, String shapeType) {
		myParser = new XMLParser(file);
		simulationType = myParser.getTextValue(SIMULATION_TYPE);
		rows = Integer.valueOf(myParser.getTextValue(NUM_ROWS));
		cols = Integer.valueOf(myParser.getTextValue(NUM_COLUMNS));
		param = Double.valueOf(myParser.getTextValue(PARAM));
		initializeGrid(shapeType);

	}

	/**
	 * From the list of states, the probability, and simulation type, construct
	 * a list of cells to be passed in to the Grid later.
	 */
	private void initializeGrid(String shapeType) {
		switch (simulationType) {
		case SPREADING_FIRE:
			myModel = new SpreadingFireModel(rows, cols, shapeType);
			break;
		case WATOR:
			myModel = new WaTorModel(rows, cols, shapeType);
			break;
		case CONWAY:
			myModel = new ConwayModel(rows, cols, shapeType);
			break;
		case SEGREGATION:
			myModel = new SegregationModel(rows, cols, shapeType);
			break;
		default:
			throw new XMLException(Resources.getString("InvalidSimulationMessage"), simulationType);
		}
		myModel.populateCells(myParser, param);
	}

	/**
	 * Gets the type of simulation
	 * 
	 * @return The simulation type
	 */
	public String getSimulationType() {
		return myParser.getTextValue(SIMULATION_TYPE);
	}

	/**
	 * Gets the number of rows in the grid
	 * 
	 * @return Number of rows in the grid
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Get the number of columns in the grid
	 * 
	 * @return Number of columns in the grid
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * Gets the additional simulation parameter
	 * 
	 * @return The additional simulation parameter
	 */
	public double getParameter() {
		return param;
	}

	/**
	 * @return
	 */
	public Model getFirstGrid() {
		return myModel;
	}
}
