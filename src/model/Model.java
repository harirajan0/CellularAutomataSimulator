package model;

import java.util.List;
import cells.Cell;

public abstract class Model {
	
	private Cell[][] myGrid;

	public Model(int r, int c) {
		super();
		myGrid = new Cell[r][c];
	}
	
	/**
	 * Tells the grid to make itself
	 * @param list List of cells to put into the grid
	 */
	public void buildModel(List<Cell> list){
		
	}
	
	/**
	 * Initializes the list of neighbors of each Cell in the grid
	 */
	public abstract void initializeNeighbors();
	
	/**
	 * Calls on every Cell in the grid to update itself
	 */
	public abstract void updateModel();
	
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