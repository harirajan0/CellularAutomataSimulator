package model;

import java.util.HashMap;
import cells.ConwayCell;
import loader.XMLParser;
import main.ApplicationStartup;
import main.Controller;
import states.ConwayState;

public class ConwayModel extends Model {
	private HashMap<Integer, ConwayState> stateMap = new HashMap<>();
	
	
	public ConwayModel(int r, int c) {
		super(r, c);
		for (ConwayState state : ConwayState.values()) {
			stateMap.put(state.getStateValue(), state);
		}
	}

	@Override
	public void populateCells(XMLParser parser, double param) {
		int sideLength = Controller.INIT_WINDOW_SIZE / Math.max(getRows(), getCols());
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				int xPosition = row * sideLength;
				int yPosition = col * sideLength;
				try {
					ConwayCell newCell = new ConwayCell(stateMap.get(Character.getNumericValue(parser.getTextValue(String.format("row%d", row)).charAt(col))), 
							xPosition, yPosition, sideLength);
					set(row, col, newCell);
				} catch (StringIndexOutOfBoundsException e) {
					throw new StringIndexOutOfBoundsException(String.format("Cannot find cell state for row %d, col %d", row, col));
				}
			}
		}
	}
}
