package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import cells.Cell;

public abstract class Model implements Iterable<Cell> {
	
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
	public abstract void initializeNeighbors(boolean edgesOnly);
	
	/**
	 * Calls on every Cell in the grid to update itself
	 */
	public abstract void updateModel();
	
	public Cell get(int row, int col){
		
		return myGrid[row][col];
	}
	
	public void set(int row, int col, Cell cell) {
		myGrid[row][col] = cell;
	}
	
	/**
	 * Gets the number of rows in the grid
	 * @return The number of rows in the grid
	 */
	public int getRows(){
		return myGrid.length;
	}
	
	/**
	 * Gets the number of columns in the grid
	 * @return The number of columns in the grid
	 */
	public int getCols(){
		return myGrid[0].length;
	}
	
	/**
	 * Checks if the given index is within the grid
	 * @param row Row index to check
	 * @param col Column index to check
	 * @return Whether or not the index at (row, col) is within the grid
	 */
	public boolean contains(int row, int col){
		return row >= 0 && row < getRows() && col >= 0 && col < getCols();
	}
	
	@Override
	public Iterator<Cell> iterator() {
		List<Cell> cellList = new ArrayList<>();
		for (Cell[] row : myGrid) {
			for (Cell cell : row) {
				cellList.add(cell);
			}
		}
		return cellList.iterator();
	}
}
