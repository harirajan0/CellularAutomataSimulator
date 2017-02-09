package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cells.Cell;
import cells.SpreadingFireCell;
import loader.XMLParser;
import main.ApplicationStartup;
import states.SpreadingFireState;
import states.WaTorState;

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
		List<Cell> neighbors = new ArrayList<Cell>();
		for(int r = 0; r < getRows(); r++){
			for(int c = 0; c < getCols(); c++){
				neighbors = new ArrayList<Cell>();
				for(int horiz = -1; horiz <= 1; horiz += 2){
					for(int vert = -1; vert <= 1; vert += 2){
						
						//Iterate through neighbors, add if not outside array
						if(contains(r + horiz, c + vert)){
							neighbors.add(get(r + horiz, c + vert));
						}
					}
				}
				get(r, c).setNeighbors(neighbors);
				System.out.println(neighbors.size());
			}
		}
	}
	
	@Override
	public void populateCells(XMLParser parser, double param) {
		int cellNum = 0;
		int sideLength = ApplicationStartup.WINDOW_SIZE / Math.max(getRows(), getCols());
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				int xPosition = row * sideLength;
				int yPosition = col * sideLength;
				set(row, col, new SpreadingFireCell(parser.getTextValue("state" + Integer.toString(cellNum)),
						xPosition, yPosition, sideLength, param));
				cellNum++;
			}
		}
	}
	
	public void updateModel() {
		for(int r = 0; r < getRows(); r++){
			for(int c = 0; c < getCols(); c++){
				get(r, c).update();
			}
		}
		
		for(int r = 0; r < getRows(); r++){
			for(int c = 0; c < getCols(); c++){
				get(r, c).nextGeneration();
			}
		}
	}
}
