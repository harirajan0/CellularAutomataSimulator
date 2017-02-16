// This entire file is part of my masterpiece.
// HARI RAJAN

package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import cells.Cell;
import cells.SegregationCell;
import loader.XMLException;
import loader.XMLParser;
import resources.Resources;
import states.SegregationState;

/**
 * Data model for the Segregation simulation
 */
public class SegregationModel extends Model {
	
	private List<Cell> availableCells;
	private HashMap<Integer, SegregationState> stateMap = new HashMap<>();
	
	/**
	 * Creates a <code>SegregationModel</code>
	 * @param r Row number
	 * @param c Column number
	 * @param shapeType The type of shape
	 */
	public SegregationModel(int r, int c, String shapeType) {
		super(r, c, shapeType);
		for (SegregationState state : SegregationState.values()) {
			stateMap.put(state.getStateValue(), state);
		}
	}
	
	@Override
	public void populateCells(XMLParser parser, double param, String inputType, List<Double> distribution) {
		super.populateCells(parser, param, inputType, distribution);
		createAvailableCells();
		placeAvailableList();
		createGraphPanel(SegregationState.EMPTY.getPossibleStatesAsString());
	}
	
	@Override
	protected SegregationCell generateCell(int row, int col, String inputType, XMLParser parser, List<Double> distribution, double param) {
		switch (inputType) {
		case Resources.SPECIFIC:
			return new SegregationCell(stateMap.get(Character
					.getNumericValue(parser.getTextValue(String.format("row%d", row)).charAt(col))), param);
		case Resources.RANDOM:
			Random rand = new Random();
			return new SegregationCell(stateMap.get(rand.nextInt(stateMap.size())), param);
		case Resources.PROBABILITY:
			double rand1 = Math.random();
			if (rand1 <= distribution.get(0)) return new SegregationCell(stateMap.get(0), param);
			else if (rand1 <= distribution.get(1)) return new SegregationCell(stateMap.get(1), param);
			else return new SegregationCell(stateMap.get(2), param);
		default:
			throw new XMLException(String.format(Resources.getString("InvalidInputTypeMessage"), inputType));
		}
	}
	
	@Override
	public void updateModel() {
		createAvailableCells();
		placeAvailableList();
		Iterator<Cell> itr = iterator();
		while(itr.hasNext()){
			Cell cell = itr.next();
			cell.update();
			createAvailableCells();
			placeAvailableList();
		}
		itr = iterator();
		while(itr.hasNext()) itr.next().nextGeneration();
		updateGraph();
	}
	
	@Override
	public int numStates() {
		return stateMap.size();
	}
	

	/**
	 *  Create list of empty cells for Model to hold
	 */

	private void createAvailableCells() {
		availableCells = new ArrayList<>();
		Iterator<Cell> itr = iterator();
		while(itr.hasNext()){
			Cell cell = itr.next();
			if (cell.getCurrentState().equals(SegregationState.EMPTY)) {
				if (cell.getNextState() != null) {
					if (!cell.getNextState().equals(SegregationState.EMPTY))
						continue;
				}
				availableCells.add(cell);
			}
		}
	}
	

	/**
	 * Put empty list into every cell
	 */
	private void placeAvailableList() {

		Iterator<Cell> itr = iterator();
		while(itr.hasNext()){
			((SegregationCell) itr.next()).setAvailableList(availableCells);
		}
	}

	@Override
	public List<Double> updatePopulations() {
		double xCount = 0;
		double oCount = 0;
		double emptyCount = 0;
		
		Iterator<Cell> itr = iterator();
		while(itr.hasNext()){
			SegregationState state = 
					(SegregationState)itr.next().getCurrentState();
			switch(state){
				case X : 
					xCount++;
					break;
				case O :
					oCount++;
					break;
				case EMPTY : 
					emptyCount++;
					break;
				default : 
					break;
			}
		}
		double totalStates = xCount + oCount + emptyCount;
		xCount /= totalStates;
		oCount /= totalStates;
		emptyCount /= totalStates;
		ArrayList<Double> pops = new ArrayList<Double>();
		pops.add(xCount);
		pops.add(oCount);
		pops.add(emptyCount);
		return pops;
	}
}