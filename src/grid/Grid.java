/**
 * 
 */
package grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cells.Cell;

/**
 * Data structure that holds all of the cells
 */
public class Grid implements Iterable<Cell> {

	private int myRows, myCols;
	private Cell[][] myGrid;
	
	/**
	 * Creates a grid with a specified number of rows and columns
	 * @param rows Number of rows
	 * @param cols Number of columns
	 */
	public Grid(int rows, int cols) {
		myRows = rows;
		myCols = cols;
		myGrid = new Cell[rows][cols];
	}
	
	/**
	 * Gets the cell at index (<code>row, col</code>)
	 * @param row The row to look in
	 * @param col The column to look in
	 * @return The cell at index (<code>row, col</code>)
	 */
	public Cell get(int row, int col) {
		return myGrid[row][col];
	}

	/**
	 * Sets the cell at index (<code>row, col</code>) to <code>Cell</code>
	 * @param row The row to look in
	 * @param col The column to look in
	 * @param cell The cell to set at index (<code>row, col</code>)
	 */
	public void set(int row, int col, Cell cell) {
		myGrid[row][col] = cell;
	}

	/**
	 * Gets the number of rows in the grid
	 * @return The number of rows in the grid
	 */
	public int getRows() {
		return myGrid.length;
	}

	/**
	 * Gets the number of columns in the grid
	 * @return The number of columns in the grid
	 */
	public int getCols() {
		return myGrid[0].length;
	}

	/**
	 * Checks if the given index is within the grid
	 * @param row Row index to check
	 * @param col Column index to check
	 * @return Whether or not the index at (row, col) is within the grid
	 */
	public boolean contains(int row, int col) {
		return row >= 0 && row < myRows && col >= 0 && col < myCols;
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
