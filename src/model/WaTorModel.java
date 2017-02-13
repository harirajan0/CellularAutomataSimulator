package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cells.Cell;
import cells.WaTorCell;
import loader.XMLException;
import loader.XMLParser;
import neighborfinder.NeighborFinder;
import resources.Resources;
import states.WaTorState;

public class WaTorModel extends Model {

	private HashMap<Integer, WaTorState> stateMap = new HashMap<>();
	
	/**
	 * Creates a <code>WaTorModel</code>
	 * @param r The row number
	 * @param c The column number
	 * @param shapeType The type of shape
	 */
	public WaTorModel(int r, int c, String shapeType) {
		super(r, c, shapeType);
		for (WaTorState state : WaTorState.values()) {
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
				for (int[] arr : myNF.getNeighborLocations()) {
					if (contains(arr[0], arr[1])) {
						nbs.add(get(arr[0], arr[1]));
					}
				}
				get(r, c).setNeighbors(nbs);
			}
		}
	}

	@Override
	public void populateCells(XMLParser parser, double param, String inputType, List<Double> distribution) {
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				try {
					WaTorCell newCell = null;
					switch (inputType) {
					case Resources.SPECIFIC:
						newCell = new WaTorCell(stateMap.get(Character
								.getNumericValue(parser.getTextValue(String.format("row%d", row)).charAt(col))));
						break;
					case Resources.RANDOM:
						Random rand = new Random();
						newCell = new WaTorCell(stateMap.get(rand.nextInt(stateMap.size())));
						break;
					case Resources.PROBABILITY:
						double rand1 = Math.random();
						if (rand1 <= distribution.get(0))
							newCell = new WaTorCell(stateMap.get(0));
						else if (rand1 <= distribution.get(1))
							newCell = new WaTorCell(stateMap.get(1));
						else
							newCell = new WaTorCell(stateMap.get(2));
						break;
					default:
						throw new XMLException(String.format(Resources.getString("InvalidInputTypeMessage"), inputType));
					}
					set(row, col, newCell);
				} catch (StringIndexOutOfBoundsException e) {
					throw new StringIndexOutOfBoundsException(
							String.format(Resources.getString("InvalidCellDataMessage"), row, col));
				}
			}
		}
		createGraphPanel(WaTorState.EMPTY.getPossibleStatesAsString());
	}

	@Override
	public List<Double> updatePopulations() {
		double fishCount = 0;
		double sharkCount = 0;
		double emptyCount = 0;
		Iterator<Cell> itr = iterator();
		while(itr.hasNext()){
			WaTorState state = (WaTorState)itr.next().getCurrentState();
			switch(state){
				case FISH : 
					fishCount++;
					break;
				case SHARK : 
					sharkCount++;
					break;
				case EMPTY:
					emptyCount++;
					break;
				default : 
					break;
			}
		}
		double totalStates = fishCount + sharkCount + emptyCount;
		fishCount /= totalStates;
		sharkCount /= totalStates;
		emptyCount /= totalStates;
		ArrayList<Double> pops = new ArrayList<Double>();
		pops.add(fishCount);
		pops.add(sharkCount);
		pops.add(emptyCount);
		return pops;
	}

	@Override
	public int numStates() {
		return stateMap.size();
	}
}