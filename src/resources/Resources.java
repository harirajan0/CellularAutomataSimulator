/**
 * 
 */
package resources;

import java.util.ResourceBundle;

import javafx.geometry.Dimension2D;

/**
 * @author harirajan
 *
 */
public class Resources {
		
	public static final ResourceBundle RESOURCES = ResourceBundle.getBundle("resources/English");
	
	public static final String DATA_FILE_EXTENSION = "*.xml";
	public static final double DEFAULT_FPS = 1;
	public static final int INIT_WINDOW_SIZE = 600;
	
	public static final Dimension2D DEFAULT_SIZE = new Dimension2D(1600, 1200);
	public static final Dimension2D SIMULATION_SPACE = new Dimension2D(620, 800);
	
	public static final String WHITE_PANE_STYLE = "-fx-background-color : white";
	public static final String GRAY_PANE_STYLE = "-fx-background-color : gray";
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
	
	public static final String EMPTY = "Empty";
	public static final String DEAD = "Dead";
	public static final String ALIVE = "Alive";
	public static final String SHARK = "Shark";
	public static final String FISH = "Fish";
	public static final String TREE = "Tree";
	public static final String BURNING = "Burning";
	public static final String X = "X";
	public static final String O = "O";
	
	public static final String SPECIFIC = "SPECIFIC";
	public static final String RANDOM = "RANDOM";
	public static final String PROBABILITY = "PROBABILITY";

	public static final String SQUARE = "Square"; 
	public static final String TRIANGLE = "Triangle"; 
	public static final String HEXAGON = "Hexagon"; 
	public static final String[] SHAPES = new String[]{SQUARE, TRIANGLE, HEXAGON};
	
	public static final String XML_FILE_PATH = "src/resources/mySimulation%d.xml";
	
	public static String getString(String key) {
		return RESOURCES.getString(key);
	}

}
