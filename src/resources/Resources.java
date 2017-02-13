/**
 * 
 */
package resources;

import java.util.ResourceBundle;

/**
 * @author harirajan
 *
 */
public class Resources {
	
	public static final ResourceBundle RESOURCES = ResourceBundle.getBundle("resources/English");
	
	public static final String SIMULATION_TYPE = "simulationType";
	public static final String SIMULATION_NAME = "simulationName";
	public static final String NUM_ROWS = "numRows";
	public static final String NUM_COLUMNS = "numCols";
	public static final String PARAM = "param";
	public static final String INPUT_TYPE = "inputType";

	public static final String SPREADING_FIRE = "FIRE";
	public static final String WATOR = "WATOR";
	public static final String SEGREGATION = "SEGREGATION";
	public static final String CONWAY = "CONWAY";
	
	public static final String SPECIFIC = "SPECIFIC";
	public static final String RANDOM = "RANDOM";
	public static final String PROBABILITY = "PROBABILITY";
	
	public static final String TRIANGLE = "Triangle";
	public static final String SQUARE = "Square";
	public static final String HEXAGON = "Hexagon";
	
	public static String getString(String key) {
		return RESOURCES.getString(key);
	}

}
