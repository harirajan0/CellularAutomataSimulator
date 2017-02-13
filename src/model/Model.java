package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import cells.Cell;
import javafx.scene.layout.VBox;
import grid.Grid;
import loader.XMLParser;
import neighborfinder.HexagonNeighborFinder;
import neighborfinder.NeighborFinder;
import neighborfinder.SquareNeighborFinder;
import neighborfinder.TriangleNeighborFinder;
import resources.Resources;
import main.GraphPanel;

/**
 * Abstract class for simulation data models
 */
public abstract class Model implements Iterable<Cell> {
	
	private int iteration;
	private Grid myGrid;
	private String shapeType;
	private NeighborFinder myNF;
	private GraphPanel graph;

	/**
	 * Creates a model
	 * @param r Number of rows
	 * @param c Number of columns
	 * @param shapeType Type of shape
	 */
	public Model(int r, int c, String shapeType) {
		myGrid = new Grid(r, c);
		this.shapeType = shapeType;
		iteration = 0;
	}
	

	/**
	 * Creates <code>NeighborFinder</code> to identify the neighbors of the cell
	 * @param str Indicates which <code>NeighborFinder</code> to use
	 * @param r Row number
	 * @param c Column number
	 * @return A <code>NeighborFinder</code> with the specified parameters
	 */
	protected NeighborFinder initializeNF(String shape, int r, int c){
		switch (shape){
		case Resources.TRIANGLE:
			myNF = new TriangleNeighborFinder(r, c);
			break;
		case Resources.SQUARE:
			myNF = new SquareNeighborFinder(r, c);
			break;
		case Resources.HEXAGON:
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
		itr = iterator();
		while(itr.hasNext()) {
			(itr.next()).nextGeneration();
		}
		updateGraph();
	}
	
	/**
	 * Updates the data in the graph
	 */
	public void updateGraph(){
		graph.update(updatePopulations(), iteration);
		iteration++;
	}
	
	/**
	 * Returns a <code>List</code> containing the population percentages of each state in order
	 * @return A <code>List</code> containing the population percentages of each state in order
	 */
	public abstract List<Double> updatePopulations();
	

	/**
	 * Creates the <code>GraphPanel</code> containing the population graph
	 * @param states All possible states
	 */
	public void createGraphPanel(List<String> states){
		graph = new GraphPanel(states);
		graph.update(updatePopulations(), 0);
	}
	
	/**
	 * Resets the iteration counter to zero
	 */
	public void resetIteration(){
		iteration = 0;
	}
	
	/**
	 * Initializes the cells inside the grid
	 * @param parser XML Parser to read cell information from
	 * @param param Additional parameter for certain models
	 */	
	public abstract void populateCells(XMLParser parser, double param, String inputType, List<Double> distribution);
	
	/**
	 * Returns the total number of states
	 * @return The total number of states
	 */
	public abstract int numStates();

	/**
	 * Gets the <code>Cell</code> contained at index (<code>row, col</code>)
	 * @param row The row number
	 * @param col The column number
	 * @return The <code>Cell</code> contained at index (<code>row, col</code>)
	 */
	public Cell get(int row, int col) {
		return myGrid.get(row, col);
	}


	/**
	 * Sets the <code>Cell</code> at index (<code>row, col</code>)
	 * @param row Set row number
	 * @param col Set column number
	 * @param cell The <code>Cell</code> to set
	 */

	protected void set(int row, int col, Cell cell) {
		myGrid.set(row, col, cell);
	}
	
	/**
	 * Gets the number of rows in the grid
	 * @return The number of rows in the grid
	 */
	public int getRows() {
		return myGrid.getRows();
	}

	/**
	 * Gets the number of columns in the grid
	 * @return The number of columns in the grid
	 */
	public int getCols() {
		return myGrid.getCols();
	}

	/**
	 * Checks if the given index is within the grid
	 * @param row Row index to check
	 * @param col Column index to check
	 * @return Whether or not the index at (row, col) is within the grid
	 */
	public boolean contains(int row, int col) {
		return myGrid.contains(row, col);
	}

	@Override
	public Iterator<Cell> iterator() {
		return myGrid.iterator();
	}
	
	/**
	 * Gets the type of shape
	 * @return The type of shape
	 */
	public String getShapeType(){
		return shapeType;
	}
	
	/**
	 * Gets the <code>VBox</code> containing the graph
	 * @return The <code>VBox</code> containing the graph
	 */
	public VBox getGraph(){
		return graph.getGraph();
	}
}
