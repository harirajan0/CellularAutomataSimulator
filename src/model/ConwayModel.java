package model;
import java.util.HashMap;
import cells.ConwayCell;
import loader.XMLParser;

import resources.Resources;
import states.ConwayState;
public class ConwayModel extends Model {
	private HashMap<Integer, ConwayState> stateMap = new HashMap<>();
	
	public ConwayModel(int r, int c, String shapeType) {
		super(r, c, shapeType);
		for (ConwayState state : ConwayState.values()) {
			stateMap.put(state.getStateValue(), state);
		}
	}
	@Override
	public void populateCells(XMLParser parser, double param) {
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				try {
					ConwayCell newCell = new ConwayCell(stateMap.get(Character.getNumericValue(parser.getTextValue(String.format("row%d", row)).charAt(col))));
					set(row, col, newCell);
				} catch (StringIndexOutOfBoundsException e) {
					throw new StringIndexOutOfBoundsException(String.format(Resources.getString("InvalidCellDataMessage"), row, col));
				}
			}
		}
	}
}