package cells;
import java.util.List;
import states.State;
/**
 * Cell superclass 
 */
public abstract class Cell {
	
	private State currentState;
	private State nextState;
	private List<Cell> neighbors;
	
	/**
	 * Creates a Cell with specified parameters
	 * @param initState initial state of cell
	 */
	public Cell(State initState) {
		setCurrentState(initState);
	}
	
	/**
	 * Updates the cell's state on click
	 */
	public abstract void changeStateOnClick();
	
	/**
	 * Update the Cell's state based on its current state and its neighbors' states
	 */
	public abstract void update();
	
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
	private void setCurrentState(State initState) {
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
	 * Sets the state of the cell to <code>state</code> on click
	 * @param state
	 */

	protected void setStateOnClick(State state) {
		this.currentState = state;
	}
	
	/**
	 * Sets current state to next state and next state to null
	 */
	public void nextGeneration() {
		setCurrentState(getNextState());
		setNextState(null);
	}
}