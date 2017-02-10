package model;

import java.util.HashMap;
import cells.ConwayCell;
import loader.XMLParser;
import main.ApplicationStartup;
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
		int sideLength = ApplicationStartup.WINDOW_SIZE / Math.max(getRows(), getCols());
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				int xPosition = row * sideLength;
				int yPosition = col * sideLength;
				ConwayCell newCell = new ConwayCell(stateMap.get(Character.getNumericValue(parser.getTextValue(String.format("row%d", row)).charAt(col))), 
						xPosition, yPosition, sideLength);
				set(row, col, newCell);
			}
		}
	}
}
