package grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cells.Cell;

public abstract class Grid implements Iterable<Cell> {
	
	private Cell[][] myGrid;

	public Grid(int r, int c) {
		myGrid = new Cell[r][c];
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
	
	public void buildGrid(List<Cell> list){
		
	}
	
	/**
	 * Each simulation will have its own grid type because the need
	 * of finding neighbors in different ways.
	 */
	public abstract List<Cell> findNeighbors();

}
