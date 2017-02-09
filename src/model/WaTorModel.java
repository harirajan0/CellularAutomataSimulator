package model;

import java.util.ArrayList;
import java.util.List;

import cells.Cell;
import cells.WaTorCell;
import loader.XMLParser;
import main.ApplicationStartup;

public class WaTorModel extends Model {
	public WaTorModel(int r, int c) {
		super(r, c);
	}

	@Override
	public void initializeNeighbors() {
		List<Cell> neighbors = new ArrayList<Cell>();
		for(int r = 0; r < getRows(); r++){
			for(int c = 0; c < getCols(); c++){
				neighbors = new ArrayList<Cell>();
				for(int shift = -1; shift <= 1; shift += 2){
					if(contains(r + shift, c)){
						neighbors.add(get(r + shift, c));
					}
					if(contains(r, c + shift)){
						neighbors.add(get(r, c + shift));
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
				set(row, col, new WaTorCell(parser.getTextValue("state" + Integer.toString(cellNum)),
						xPosition, yPosition, sideLength));
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
