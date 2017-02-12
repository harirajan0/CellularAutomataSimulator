package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import cells.Cell;
import cells.WaTorCell;
import loader.XMLParser;
import neighborfinder.NeighborFinder;
import resources.Resources;
import cells.WaTorCell;
import loader.XMLParser;
import main.Controller;
import states.WaTorState;
public class WaTorModel extends Model {
	
	private HashMap<Integer, WaTorState> stateMap = new HashMap<>();
	
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
				System.out.println(getShapeType());
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
					WaTorCell newCell = new WaTorCell(stateMap.get(Character.getNumericValue(parser.getTextValue(String.format("row%d", row)).charAt(col))));
					set(row, col, newCell);
				} catch (StringIndexOutOfBoundsException e) {
					throw new StringIndexOutOfBoundsException(String.format(Resources.getString("InvalidCellDataMessage"), row, col));
				}
			}
		}
	}
}