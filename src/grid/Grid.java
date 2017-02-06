package grid;

import java.util.List;

import cells.Cell;

public abstract class Grid {
	
	private Cell[][] myGrid;
	private int row, col;

	public Grid(int r, int c) {
		myGrid = new Cell[r][c];
		row = r;
		col = c;
	}
	
	public void buildGrid(List<Cell> list){
		
	}
	
	/**
	 * Each simulation will have its own grid type because the need
	 * of finding neighbors in different ways.
	 */
	public abstract List<Cell> findNeighbors();
	
	public int getRow(){
		return row;
	}
	
	public int getCol(){
		return col;
	}

}
