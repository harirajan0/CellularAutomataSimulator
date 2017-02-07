package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cells.Cell;
import cells.SegregationCell;
import loader.XMLParser;
import main.ApplicationStartup;

public class SegregationModel extends Model {
	private List<Cell> availableCells;
	
	private static String EMPTY = "empty";
	
	public SegregationModel(int r, int c) {
		super(r, c);
		availableCells = new ArrayList<>();
	}
	
	// put empty list into every cell
	public void initiateAvailableCells(){
		for (Iterator<Cell> i = iterator(); i.hasNext();){
			((SegregationCell)i.next()).setAvailableList(availableCells);
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
				set(row, col,
						new SegregationCell(parser.getTextValue("state" + Integer.toString(cellNum)), xPosition,
								yPosition, sideLength, param));
				cellNum++;
			}
		}
		createAvailableCells();
		initiateAvailableCells();
	}
	
	public void updateModel() {
		availableCells = new ArrayList<Cell>();
		for(int r = 0; r < getRows(); r++){
			for(int c = 0; c < getCols(); c++){
				get(r, c).update();
			}
		}
		createAvailableCells();
		for(int r = 0; r < getRows(); r++){
			for(int c = 0; c < getCols(); c++){
				get(r, c).nextGeneration();
				((SegregationCell) this.get(r, c)).setAvailableList(availableCells);
			}
		}
	}
	
	// create list of empty cells for Model to hold
	public void createAvailableCells(){
		for (Iterator<Cell> i = iterator(); i.hasNext();){
			Cell cell = i.next();
			if (cell.getCurrentState().equals(EMPTY)){
				availableCells.add(cell);
			}
		}
	}
}
