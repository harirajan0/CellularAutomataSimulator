/**
 * 
 */
package grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cells.Cell;

/**
 * @author harirajan
 *
 */

public class Grid implements Iterable<Cell> {

	private int myRows, myCols;
	private Cell[][] myGrid;
	
	public Grid(int rows, int cols) {
		myRows = rows;
		myCols = cols;
		myGrid = new Cell[rows][cols];
	}
	
	public Cell get(int row, int col) {
		return myGrid[row][col];
	}

	public void set(int row, int col, Cell cell) {
		myGrid[row][col] = cell;
	}

	/**
	 * Gets the number of rows in the grid
	 * 
	 * @return The number of rows in the grid
	 */
	public int getRows() {
		return myGrid.length;
	}

	/**
	 * Gets the number of columns in the grid
	 * 
	 * @return The number of columns in the grid
	 */
	public int getCols() {
		return myGrid[0].length;
	}

	/**
	 * Checks if the given index is within the grid
	 * 
	 * @param row
	 *            Row index to check
	 * @param col
	 *            Column index to check
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
