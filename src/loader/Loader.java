package loader;

import java.io.File;
import model.ConwayModel;
import model.Model;
import model.SegregationModel;
import model.SpreadingFireModel;
import model.WaTorModel;

public class Loader {

	public static final String XML_EXTENSION = ".xml";

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

	public Loader(File file) {
		myParser = new XMLParser(file);
		if (!file.getName().endsWith(XML_EXTENSION)) {
			throw new XMLException("The chosen file is not an xml file");
			// ASK WINDOWS USERS IF THIS IS AN ISSUE THAT NEEDS TO THROW AN ALERT!!!!!
		}
		simulationType = myParser.getTextValue(SIMULATION_TYPE);
		rows = Integer.valueOf(myParser.getTextValue(NUM_ROWS));
		cols = Integer.valueOf(myParser.getTextValue(NUM_COLUMNS));
		param = Double.valueOf(myParser.getTextValue(PARAM));
		initializeGrid();

	}

	/**
	 * From the list of states, the probability, and simulation type, construct
	 * a list of cells to be passed in to the Grid later.
	 */
	private void initializeGrid() {
		switch (simulationType) {
		case SPREADING_FIRE:
			myModel = new SpreadingFireModel(rows, cols);
			break;
		case WATOR:
			myModel = new WaTorModel(rows, cols);
			break;
		case CONWAY:
			myModel = new ConwayModel(rows, cols);
			break;
		case SEGREGATION:
			myModel = new SegregationModel(rows, cols);
			break;
		default:
			break;
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
