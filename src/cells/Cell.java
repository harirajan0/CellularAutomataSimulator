package cells;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Cell superclass. 
 * @author Hari Rajan
 * @author Vishnu Gottiparthy
 * @author Bihan Zhuang
 * @author Gabriel Chen
 */
public abstract class Cell {
	private String currentState;
	private String nextState;
	private List<Cell> neighbors;
	private int xPosition;
	private int yPosition;
	private Rectangle myRect;
	
	/**
	 * Creates a Cell with specified parameters
	 * @param initState initial state of cell
	 * @param xPosition x position of cell on screen
	 * @param yPosition y position of cell on screen
	 */
	public Cell(String initState, int x, int y, int width) {
		setCurrentState(initState);
		setxPosition(x);
		setyPosition(y);
		myRect = new Rectangle(x, y, width, width);
		setFill(Color.BLACK);
	}

	/**
	 * Update the Cell's state based on its current state and its neighbors' states
	 */
	public abstract void update();
	
	/**
	 * Changes the color of the Cell
	 * @param c Color to set the Cell
	 */
	public void setFill(Color c){
		myRect.setFill(c);
	}
	
	/**
	 * Determines which color to call setFill on
	 */
	public abstract void paint();
	
	/**
	 * Gets a List of all of the neighbors of the cell
	 * @return the neighbors of the cell
	 */
	public List<Cell> getNeighbors() {
		return neighbors;
	}
	/**
	 * Sets the neighbors of the cell to those in the specified list
	 * @param neighbors The neighbors to be set
	 */
	public void setNeighbors(List<Cell> neighbors) {
		this.neighbors = neighbors;
	}
	
	/**
	 * Gets the x-coordinate of the cell
	 * @return x-coordinate of the cell
	 */
	public double getxPosition() {
		return xPosition;
	}
	/**
	 * Sets the x-coordinate of the cell
	 * @param xPosition New x-coordinate of the cell
	 */
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	/**
	 * Gets the y-coordinate of the cell
	 * @return y-coordinate of the cell
	 */
	public double getyPosition() {
		return yPosition;
	}
	/**
	 * Sets the y-coordinate of the cell
	 * @param yPosition New y-coordinate of the cell
	 */
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	
	/**
	 * Gets the current state of the cell
	 * @return The current state of the cell
	 */
	public String getCurrentState() {
		return currentState;
	}
	
	/**
	 * Sets the current state of the cell
	 * @param currentState The new current state of the cell
	 */
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
	
	/**
	 * Gets the next state of the cell
	 * @return The next state of the cell
	 */
	public String getNextState() {
		return nextState;
	}
	/**
	 * Sets the next state of the cell
	 * @param nextState The new next state of the cell
	 */
	public void setNextState(String nextState) {
		this.nextState = nextState;
	}
	
	/**
	 * Gets the Rectangle that represents the cell in the GUI
	 * @return	Rectangle that represents the cell in the GUI
	 */
	public Rectangle getRect()
	{
		return myRect;
	}
	
	/**
	 * Sets current state to next state and next state to null
	 */
	public void nextGeneration()
	{
		setCurrentState(getNextState());
		setNextState(null);
	}
}