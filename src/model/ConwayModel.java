
// This entire file is part of my masterpiece.
// HARI RAJAN

package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import cells.ConwayCell;
import loader.XMLException;
import loader.XMLParser;
import resources.Resources;
import states.ConwayState;
import cells.Cell;

/**
 * Model for Conway's Game of Life simulation
 */
public class ConwayModel extends Model {
	
	private HashMap<Integer, ConwayState> stateMap = new HashMap<>();
	
	/**
	 * Creates the model
	 * @param r Number of rows
	 * @param c Number of columns
	 * @param shapeType Type of shape
	 */
	public ConwayModel(int r, int c, String shapeType) {
		super(r, c, shapeType);
		for (ConwayState state : ConwayState.values()) {
			stateMap.put(state.getStateValue(), state);
		}
	}
	
	@Override
	public void populateCells(XMLParser parser, double param, String inputType, List<Double> distribution) {
		super.populateCells(parser, param, inputType, distribution);
		createGraphPanel(ConwayState.DEAD.getPossibleStatesAsString());
	}
	
	@Override
	protected ConwayCell generateCell(int row, int col, String inputType, XMLParser parser, List<Double> distribution, double param) {
		switch (inputType) {
		case Resources.SPECIFIC:
			return new ConwayCell(stateMap.get(Character
					.getNumericValue(parser.getTextValue(String.format("row%d", row)).charAt(col))));
		case Resources.RANDOM:
			Random rand = new Random();
			return new ConwayCell(stateMap.get(rand.nextInt(stateMap.size())));
		case Resources.PROBABILITY:
			double rand1 = Math.random();
			if (rand1 <= distribution.get(0)) return new ConwayCell(stateMap.get(0));
			else return new ConwayCell(stateMap.get(1));
		default:
			throw new XMLException(String.format(Resources.getString("InvalidInputTypeMessage"), inputType));
		}
	}
	
	@Override
	public List<Double> updatePopulations(){
		double liveCount = 0;
		double deadCount = 0;
		Iterator<Cell> itr = iterator();
		while(itr.hasNext()){
			ConwayState state = (ConwayState)itr.next().getCurrentState();
			switch(state){
				case ALIVE : 
					liveCount++;
					break;
				case DEAD : 
					deadCount++;
					break;
				default : 
					break;
			}
		}
		double totalStates = liveCount + deadCount;
		liveCount /= totalStates;
		deadCount /= totalStates;
		ArrayList<Double> pops = new ArrayList<Double>();
		pops.add(liveCount);
		pops.add(deadCount);
		return pops;
	}

	/* (non-Javadoc)
	 * @see model.Model#numStates()
	 */
	@Override
	public int numStates() {
		return stateMap.size();
	}
}