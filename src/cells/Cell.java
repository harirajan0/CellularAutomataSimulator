package cells;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import states.State;

/**
 * Cell superclass. 
 * @author Hari Rajan
 * @author Vishnu Gottiparthy
 * @author Bihan Zhuang
 * @author Gabriel Chen
 */
public abstract class Cell {
	private State currentState;
	private State nextState;
	private List<Cell> neighbors;
	private Rectangle myRect;
	
	/**
	 * Creates a Cell with specified parameters
	 * @param initState initial state of cell
	 * @param xPosition x position of cell on screen
	 * @param yPosition y position of cell on screen
	 */
	public Cell(State initState) {
		setCurrentState(initState);
		/*setxPosition(x);
		setyPosition(y);
		myRect = new Rectangle(x, y, width, width);
		myRect.setStroke(Color.BLACK);
		paint();*/
	}

	/**
	 * Update the Cell's state based on its current state and its neighbors' states
	 */
	public abstract void update();
	
	/**
	 * Changes the color of the Cell
	 * @param c Color to set the Cell
	 */
//	public void setFill(Color c){
//		myRect.setFill(c);
//	}
	
	/**
	 * Determines which color to call setFill on
	 */
//	public void paint() {
//		setFill(getCurrentState().getColor());
//	}
	
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
//	public double getxPosition() {
//		return xPosition;
//	}
//	/**
//	 * Sets the x-coordinate of the cell
//	 * @param xPosition New x-coordinate of the cell
//	 */
//	public void setxPosition(int xPosition) {
//		this.xPosition = xPosition;
//	}
//	/**
//	 * Gets the y-coordinate of the cell
//	 * @return y-coordinate of the cell
//	 */
//	public double getyPosition() {
//		return yPosition;
//	}
//	/**
//	 * Sets the y-coordinate of the cell
//	 * @param yPosition New y-coordinate of the cell
//	 */
//	public void setyPosition(int yPosition) {
//		this.yPosition = yPosition;
//	}
//	
	
	/**
	 * Gets the current state of the cell
	 * @return The current state of the cell
	 */
	public State getCurrentState() {
		return currentState;
	}
	
	/**
	 * Sets the current state of the cell
	 * @param initState The new current state of the cell
	 */
	public void setCurrentState(State initState) {
		this.currentState = initState;
	}
	
	/**
	 * Gets the next state of the cell
	 * @return The next state of the cell
	 */
	public State getNextState() {
		return nextState;
	}
	/**
	 * Sets the next state of the cell
	 * @param state The new next state of the cell
	 */
	public void setNextState(State state) {
		this.nextState = state;
	}
	
	/**
	 * Tells whether or not the cell's current state is <code>state</code>.
	 * @param empty State to test
	 * @return Whether or not <code>state</code> is the current state
	 */
	public boolean isState(State state) {
		return getCurrentState().equals(state);
	}
	
	/**
	 * Gets the Rectangle that represents the cell in the GUI
	 * @return	Rectangle that represents the cell in the GUI
	 */
//	public Rectangle getRect()
//	{
//		return myRect;
//	}
	
	/**
	 * Sets current state to next state and next state to null
	 */
	public void nextGeneration()
	{
		setCurrentState(getNextState());
		//paint();
		setNextState(null);
	}
}