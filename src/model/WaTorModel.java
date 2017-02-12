package model;

import java.util.HashMap;

import cells.WaTorCell;
import loader.XMLParser;
import main.Controller;
import states.WaTorState;

public class WaTorModel extends Model {
	
	private HashMap<Integer, WaTorState> stateMap = new HashMap<>();
	
	public WaTorModel(int r, int c) {
		super(r, c);
		for (WaTorState state : WaTorState.values()) {
			stateMap.put(state.getStateValue(), state);
		}
	}

	@Override
	public void initializeNeighbors() {
		super.initializeNeighbors();
		removeCorners();
	}
	
	@Override
	public void populateCells(XMLParser parser, double param) {
		int sideLength = Controller.WINDOW_SIZE / Math.max(getRows(), getCols());
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				int xPosition = row * sideLength;
				int yPosition = col * sideLength;
				try {
					WaTorCell newCell = new WaTorCell(stateMap.get(Character.getNumericValue(parser.getTextValue(String.format("row%d", row)).charAt(col))), 
							xPosition, yPosition, sideLength);
					set(row, col, newCell);
				} catch (StringIndexOutOfBoundsException e) {
					throw new StringIndexOutOfBoundsException(String.format("Cannot find cell state for row %d, col %d", row, col));
				}
			}
		}
	}
}
