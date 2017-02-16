
// This entire file is part of my masterpiece.
// HARI RAJAN

package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import cells.Cell;
import cells.SpreadingFireCell;
import loader.XMLException;
import loader.XMLParser;
import neighborfinder.NeighborFinder;
import resources.Resources;
import states.SpreadingFireState;

/**
 * Creates a data model for the Spreading Fire simulation
 */
public class SpreadingFireModel extends Model {
	
	private HashMap<Integer, SpreadingFireState> stateMap = new HashMap<>();
	
	/**
	 * Creates a <code>SpreadingFireModel</code>
	 * @param r The row number
	 * @param c The column number
	 * @param shapeType The type of shape
	 */
	public SpreadingFireModel(int r, int c, String shapeType) {
		super(r, c, shapeType);
		for (SpreadingFireState state : SpreadingFireState.values()) {
			stateMap.put(state.getStateValue(), state);
		}
	}
	
	@Override
	public void initializeNeighbors() {
		for (int r = 0; r < getRows(); r++) {
			for (int c = 0; c < getCols(); c++) {
				List<Cell> nbs = new ArrayList<>();
				NeighborFinder myNF = initializeNF(getShapeType(), r, c);
				myNF.findNeighbors();
				myNF.removeCorners();
				for (int[] arr : myNF.getNeighborLocations()){
					if (contains(arr[0], arr[1])){
						nbs.add(get(arr[0], arr[1]));
					}
				}
				get(r, c).setNeighbors(nbs);
			}
		}
	}
	
	@Override
	public void populateCells(XMLParser parser, double param, String inputType, List<Double> distribution) {
		super.populateCells(parser, param, inputType, distribution);
		createGraphPanel(SpreadingFireState.EMPTY.getPossibleStatesAsString());
	}
	
	@Override
	protected SpreadingFireCell generateCell(int row, int col, String inputType, XMLParser parser, List<Double> distribution, double param) {
		switch (inputType) {
		case Resources.SPECIFIC:
			return new SpreadingFireCell(stateMap.get(Character
					.getNumericValue(parser.getTextValue(String.format("row%d", row)).charAt(col))), param);
		case Resources.RANDOM:
			Random rand = new Random();
			return new SpreadingFireCell(stateMap.get(rand.nextInt(stateMap.size())), param);
		case Resources.PROBABILITY:
			double rand1 = Math.random();
			if (rand1 <= distribution.get(0)) return new SpreadingFireCell(stateMap.get(0), param);
			else if (rand1 <= distribution.get(1)) return new SpreadingFireCell(stateMap.get(1), param);
			else return new SpreadingFireCell(stateMap.get(2), param);
		default:
			throw new XMLException(String.format(Resources.getString("InvalidInputTypeMessage"), inputType));
		}
	}
	
	@Override
	public List<Double> updatePopulations() {
		double treeCount = 0;
		double burningCount = 0;
		double emptyCount = 0;
		
		Iterator<Cell> itr = iterator();
		while(itr.hasNext()){
			SpreadingFireState state = 
					(SpreadingFireState)itr.next().getCurrentState();
			switch(state){
				case TREE : 
					treeCount++;
					break;
				case BURNING :
					burningCount++;
					break;
				case EMPTY : 
					emptyCount++;
					break;
				default : 
					break;
			}
		}
		double totalStates = treeCount + burningCount + emptyCount;
		treeCount /= totalStates;
		burningCount /= totalStates;
		emptyCount /= totalStates;
		ArrayList<Double> pops = new ArrayList<Double>();
		pops.add(treeCount);
		pops.add(burningCount);
		pops.add(emptyCount);
		return pops;
	}
	
	@Override
	public int numStates() {
		return stateMap.size();
	}
}
