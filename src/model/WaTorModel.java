package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cells.Cell;
import cells.SpreadingFireCell;
import cells.WaTorCell;
import loader.XMLParser;
import main.ApplicationStartup;
import states.State;
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
		List<Cell> neighbors = new ArrayList<Cell>();
		for(int r = 0; r < getRows(); r++){
			for(int c = 0; c < getCols(); c++){
				neighbors = new ArrayList<Cell>();
				for(int shift = -1; shift <= 1; shift += 2){
					if(contains(r + shift, c)) neighbors.add(get(r + shift, c));
					if(contains(r, c + shift)) neighbors.add(get(r, c + shift));
				}
				get(r, c).setNeighbors(neighbors);
			}
		}
	}
	
	@Override
	public void populateCells(XMLParser parser, double param) {
		int sideLength = ApplicationStartup.WINDOW_SIZE / Math.max(getRows(), getCols());
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				int xPosition = row * sideLength;
				int yPosition = col * sideLength;
				WaTorCell newCell = new WaTorCell(stateMap.get(Character.getNumericValue(parser.getTextValue(String.format("row%d", row)).charAt(col))), 
						xPosition, yPosition, sideLength);
				set(row, col, newCell);
			}
		}
	}
}
