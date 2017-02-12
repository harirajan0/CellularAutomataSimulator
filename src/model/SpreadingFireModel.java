package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import cells.Cell;
import cells.SpreadingFireCell;
import loader.XMLParser;
import neighborfinder.NeighborFinder;
import resources.Resources;
import states.SpreadingFireState;

public class SpreadingFireModel extends Model {
	
	private HashMap<Integer, SpreadingFireState> stateMap = new HashMap<>();
	
	public SpreadingFireModel(int r, int c, String shapeType) {
		super(r, c, shapeType);
		for (SpreadingFireState state : SpreadingFireState.values()) {
			stateMap.put(state.getStateValue(), state);
		}
		createGraphPanel("Tree", "Burning", "Fish");
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
	public void populateCells(XMLParser parser, double param) {
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				try {
					SpreadingFireCell newCell = new SpreadingFireCell(stateMap.get(Character.getNumericValue(parser.getTextValue(String.format("row%d", row)).charAt(col))), param);
					set(row, col, newCell);
				} catch (StringIndexOutOfBoundsException e) {
					throw new StringIndexOutOfBoundsException(String.format(Resources.getString("InvalidCellDataMessage"), row, col));
				}
			}
		}
	}

	@Override
	public List<Double> updateGraph() {
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
		treeCount /= treeCount + burningCount + emptyCount;
		burningCount /= treeCount + burningCount + emptyCount;
		emptyCount /= treeCount + burningCount + emptyCount;
		ArrayList<Double> pops = new ArrayList<Double>();
		pops.add(treeCount);
		pops.add(burningCount);
		pops.add(emptyCount);
		return pops;
	}
}
