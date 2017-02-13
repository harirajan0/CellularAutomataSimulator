package loader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import alerts.CellSocietyAlerts;
import model.ConwayModel;
import model.Model;
import model.SegregationModel;
import model.SpreadingFireModel;
import model.WaTorModel;
import resources.Resources;

/**
 * Reads an XML file of initial states 
 */
public class Loader {

	private XMLParser myParser;
	private String simulationType;
	private String inputType;
	private String simulationName;
	private int rows;
	private int cols;
	private double param;
	private Model myModel;

	/**
	 * Creates a <code>Loader</code>
	 * @param file The file to read from
	 * @param shapeType The type of shape the cell is
	 */
	public Loader(File file, String shapeType) {
		myParser = new XMLParser(file);
		simulationType = myParser.getTextValue(Resources.SIMULATION_TYPE).trim();
		simulationName = myParser.getTextValue(Resources.SIMULATION_NAME).trim();
		rows = Integer.valueOf(myParser.getTextValue(Resources.NUM_ROWS).trim());
		cols = Integer.valueOf(myParser.getTextValue(Resources.NUM_COLUMNS));
		param = Double.valueOf(myParser.getTextValue(Resources.PARAM).trim());
		inputType = myParser.getTextValue(Resources.INPUT_TYPE).trim();
		initializeGrid(shapeType);

	}

	/**
	 * From the list of states, the probability, and simulation type, construct
	 * a list of cells to be passed in to the Grid later.
	 */
	private void initializeGrid(String shapeType) {
		switch (simulationType) {
		case Resources.SPREADING_FIRE:
			myModel = new SpreadingFireModel(rows, cols, shapeType);
			break;
		case Resources.WATOR:
			myModel = new WaTorModel(rows, cols, shapeType);
			break;
		case Resources.CONWAY:
			myModel = new ConwayModel(rows, cols, shapeType);
			break;
		case Resources.SEGREGATION:
			myModel = new SegregationModel(rows, cols, shapeType);
			break;
		default:
			throw new XMLException(Resources.getString("InvalidSimulationMessage"), simulationType);
		}
		List<Double> distribution = generateDistribution();
		myModel.populateCells(myParser, param, inputType, distribution);
	}
	
	/**
	 * If a random distribution is needed, make it based on listed parameters
	 * @return A list of population percentages specified in the file
	 */
	private List<Double> generateDistribution() {
		List<Double> distribution = new ArrayList<>();
		if (inputType.equals(Resources.PROBABILITY)) {
			try {
				double current = 0.0;
				for (int i = 0; i < myModel.numStates(); i++) {
					current += Double.parseDouble(myParser.getTextValue(String.format("state%d", i)));
					distribution.add(current);
				}
				if (current != 1.0) {
					throw new XMLException("State distribution does not add to 1.0");
				}
			} catch (XMLException e) {
				CellSocietyAlerts.xmlError(e, myParser.getFile());
			}
		}
		return distribution;
		
	}
	/**
	 * Gets the type of simulation
	 * @return The simulation type
	 */
	public String getSimulationType() {
		return simulationType;
	}

	/**
	 * Gets the number of rows in the grid
	 * @return Number of rows in the grid
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Get the number of columns in the grid
	 * @return Number of columns in the grid
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * Gets the additional simulation parameter
	 * @return The additional simulation parameter
	 */
	public double getParameter() {
		return param;
	}
	
	/**
	 * Gets the name of the simulation
	 * @return The name of the simulation
	 */
	public String getSimulationName() {
		return simulationName;
	}

	/**
	 * Gets the grid of initial values
	 * @return The grid of initial values
	 */
	public Model getFirstGrid() {
		return myModel;
	}
}
