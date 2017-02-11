package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import cells.Cell;
import cells.SegregationCell;
import loader.XMLParser;
import main.ApplicationStartup;
import states.SegregationState;

public class SegregationModel extends Model {
	
	private List<Cell> availableCells;
	private HashMap<Integer, SegregationState> stateMap = new HashMap<>();

	public SegregationModel(int r, int c) {
		super(r, c);
		for (SegregationState state : SegregationState.values()) {
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
				SegregationCell newCell = new SegregationCell(stateMap.get(Character.getNumericValue(parser.getTextValue(String.format("row%d", row)).charAt(col))), 
						xPosition, yPosition, sideLength, param);
				set(row, col, newCell);
			}
		}
		createAvailableCells();
		placeAvailableList();
	}

	@Override
	public void updateModel() {
		createAvailableCells();
		placeAvailableList();
		
		Iterator<Cell> itr = iterator();
		while(itr.hasNext()){
			itr.next().update();
			createAvailableCells();
			placeAvailableList();
		}
		itr = iterator();
		while(itr.hasNext()) itr.next().nextGeneration();
	}

	// create list of empty cells for Model to hold
	public void createAvailableCells() {
		availableCells = new ArrayList<>();
		Iterator<Cell> itr = iterator();
		while(itr.hasNext()){
			Cell cell = itr.next();
			if (cell.getCurrentState().equals(SegregationState.EMPTY)) {
				if (cell.getNextState() != null) {
					if (!cell.getNextState().equals(SegregationState.EMPTY))
						continue;
				}
				availableCells.add(cell);
			}
		}
	}
	
	// put empty list into every cell
	public void placeAvailableList() {
		Iterator<Cell> itr = iterator();
		while(itr.hasNext()){
			((SegregationCell) itr.next()).setAvailableList(availableCells);
		}
	}
}
