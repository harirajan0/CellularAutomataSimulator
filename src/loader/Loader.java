package loader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Loader {
	
    public static final String SIMULATION_TYPE = "simulationType";
    public static final String SIMULATION_NAME = "simulationName";
    public static final String NUM_ROWS = "numRows";
    public static final String NUM_COLUMNS = "numCols";
    public static final String PERCENT_STATE_ONE = "percentState1";
    public static final String PERCENT_STATE_TWO = "percentState2";
    public static final String PERCENT_STATE_THREE = "percentState3";
    public static final String PARAM = "param";
    
    XMLParser myParser;

	public Loader(String fileName) {
		myParser = new XMLParser(fileName);
	}
    
	public List<String> getStates() {
		return null;
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
