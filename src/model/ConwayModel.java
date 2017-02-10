package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cells.Cell;
import cells.ConwayCell;
import cells.ConwayCell;
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
	public void initializeNeighbors() {
		List<Cell> neighbors = new ArrayList<Cell>();
		for(int r = 0; r < getRows(); r++){
			for(int c = 0; c < getCols(); c++){
				neighbors = new ArrayList<Cell>();
				for(int horiz = -1; horiz <= 1; horiz ++){
					for(int vert = -1; vert <= 1; vert ++){
						if(horiz ==0 && vert == 0){
							continue;
						}

						//Iterate through neighbors, add if not outside array
						if(contains(r + horiz, c + vert)){
							neighbors.add(get(r + horiz, c + vert));
						}
					}
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
				ConwayCell newCell = new ConwayCell(stateMap.get(Character.getNumericValue(parser.getTextValue(String.format("row%d", row)).charAt(col))), 
						xPosition, yPosition, sideLength);
				set(row, col, newCell);

			}
		}
	}
}
