package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import cells.Cell;
import javafx.scene.layout.VBox;
import loader.XMLParser;
import neighborfinder.HexagonNeighborFinder;
import neighborfinder.NeighborFinder;
import neighborfinder.SquareNeighborFinder;
import neighborfinder.TriangleNeighborFinder;
import main.GraphPanel;

public abstract class Model implements Iterable<Cell> {

	private static final String TRIANGLE = "Triangle";
	private static final String SQUARE = "Square";
	private static final String HEXAGON = "Hexagon";
	
	private int iteration;
	private Cell[][] myGrid;
	private String shapeType;
	private NeighborFinder myNF;
	private GraphPanel graph;

	public Model(int r, int c, String shapeType) {
		myGrid = new Cell[r][c];
		this.shapeType = shapeType;
		iteration = 0;
	}
	
	public NeighborFinder initializeNF(String str, int r, int c){
		switch(str){
		case TRIANGLE:
			myNF = new TriangleNeighborFinder(r, c);
			break;
		case SQUARE:
			myNF = new SquareNeighborFinder(r, c);
			break;
		case HEXAGON:
			System.out.println("yes");
			myNF = new HexagonNeighborFinder(r, c);
			break;
		default:
			break;
		}
		return myNF;
	}
	
	/**
	 * Initializes the list of neighbors of each Cell in the grid
	 */
	public void initializeNeighbors() {
		for (int r = 0; r < getRows(); r++) {
			for (int c = 0; c < getCols(); c++) {
				List<Cell> nbs = new ArrayList<>();
				initializeNF(shapeType, r, c);
				myNF.findNeighbors();
				for (int[] arr : myNF.getNeighborLocations()){
					if (contains(arr[0], arr[1])){
						nbs.add(get(arr[0], arr[1]));
					}
				}
				get(r, c).setNeighbors(nbs);
			}
		}
	}

	/**
	 * Calls on every Cell in the grid to update itself
	 */
	public void updateModel() {
		Iterator<Cell> itr = iterator();
		while(itr.hasNext()) itr.next().update();
		System.out.println(get(0,0).getCurrentState());
		itr = iterator();
		while(itr.hasNext()) {
			(itr.next()).nextGeneration();
		}
		iteration++;
		graph.update(updateGraph(), iteration);
	}
	
	/**
	 * Initializes the cells inside the grid
	 * @param parser XML Parser to read cell information from
	 * @param param Additional parameter for certain models
	 */
	public abstract void populateCells(XMLParser parser, double param);
	
	public abstract List<Double> updateGraph();
	
	public void createGraphPanel(String... states){
		graph = new GraphPanel(Arrays.asList(states));
		graph.update(updateGraph(), 0);
	}
	
	public void resetIteration(){
		iteration = 0;
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
	
	public String getShapeType(){
		return shapeType;
	}
	
	public VBox getGraph(){
		return graph.getGraph();
	}
}
