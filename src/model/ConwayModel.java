package model;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import cells.ConwayCell;
import cells.WaTorCell;
import loader.XMLException;
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
	public void populateCells(XMLParser parser, double param, String inputType, List<Double> distribution) {
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				try {
					ConwayCell newCell = null;
					switch (inputType) {
					case Resources.SPECIFIC:
						newCell = new ConwayCell(stateMap.get(Character
								.getNumericValue(parser.getTextValue(String.format("row%d", row)).charAt(col))));
						break;
					case Resources.RANDOM:
						Random rand = new Random();
						newCell = new ConwayCell(stateMap.get(rand.nextInt(stateMap.size())));
						break;
					case Resources.PROBABILITY:
						double rand1 = Math.random();
						if (rand1 <= distribution.get(0))
							newCell = new ConwayCell(stateMap.get(0));
						else
							newCell = new ConwayCell(stateMap.get(1));
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
	}
	/* (non-Javadoc)
	 * @see model.Model#numStates()
	 */
	@Override
	public int numStates() {
		return stateMap.size();
	}
	
}