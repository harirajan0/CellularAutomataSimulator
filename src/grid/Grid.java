package grid;

import java.util.List;

import cells.Cell;

public abstract class Grid {
	
	private Cell[][] myGrid;

	public Grid(int r, int c) {
		myGrid = new Cell[r][c];
	}
	
	public void buildGrid(List<Cell> list){
		
	}
	
	/**
	 * Each simulation will have its own grid type because the need
	 * of finding neighbors in different ways.
	 */
	public abstract List<Cell> findNeighbors();

}
