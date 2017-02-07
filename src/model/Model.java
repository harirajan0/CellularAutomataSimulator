package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import cells.Cell;

public abstract class Model implements Iterable<Cell> {
	
	private Cell[][] myGrid;
	private int row, col;

	public Model(int r, int c) {
		super();
		myGrid = new Cell[r][c];
		row = r;
		col = c;
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
	public abstract List<Cell> findNeighbors();
	
	public int getRow(){
		return row;
	}
	
	public int getCol(){
		return col;
	}

	public abstract void initializeNeighbors();
	
	/**
	 * Calls on every Cell in the grid to update itself
	 */
	public abstract void updateModel();
	
	protected Cell get(int row, int col){
		
		return myGrid[row][col];
	}
	
	public void set(int row, int col, Cell cell) {
		myGrid[row][col] = cell;
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
