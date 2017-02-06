package grid;

import java.util.List;
import cells.Cell;

public abstract class Grid {
	
	private Cell[][] myGrid;
	private int row, col;

	public Grid(int r, int c) {
		super();
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

	public abstract void initializeNeighbors();
	
	protected Cell get(int row, int col){
		
		return myGrid[row][col];
	}
	
	/**
	 * Gets the number of rows in the grid
	 * @return The number of rows in the grid
	 */
	protected int getRows(){
		return myGrid.length;
	}
	
	/**
	 * Gets the number of columns in the grid
	 * @return The number of columns in the grid
	 */
	protected int getCols(){
		return myGrid[0].length;
	}
	
	/**
	 * Checks if the given index is within the grid
	 * @param row Row index to check
	 * @param col Column index to check
	 * @return Whether or not the index at (row, col) is within the grid
	 */
	protected boolean contains(int row, int col){
		return row >=0 && row < getRows() && col >= 0 && col < getCols();
	}
}
