package model;

import java.util.HashMap;
import cells.SpreadingFireCell;
import loader.XMLException;
import loader.XMLParser;
import main.ApplicationStartup;
import states.SpreadingFireState;

public class SpreadingFireModel extends Model {
	
	private HashMap<Integer, SpreadingFireState> stateMap = new HashMap<>();
	
	public SpreadingFireModel(int r, int c) {
		super(r, c);
		for (SpreadingFireState state : SpreadingFireState.values()) {
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
		int sideLength = ApplicationStartup.WINDOW_SIZE / Math.max(getRows(), getCols());
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				int xPosition = row * sideLength;
				int yPosition = col * sideLength;
				try {
					SpreadingFireCell newCell = new SpreadingFireCell(stateMap.get(Character.getNumericValue(parser.getTextValue(String.format("row%d", row)).charAt(col))), 
							xPosition, yPosition, sideLength, param);
					set(row, col, newCell);
				} catch (StringIndexOutOfBoundsException e) {
					throw new StringIndexOutOfBoundsException(String.format("Cannot find cell state for row %d, col %d", row, col));
				}
			}
		}
	}
}
