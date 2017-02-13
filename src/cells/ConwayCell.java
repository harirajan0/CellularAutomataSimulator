package cells;
import java.util.List;
import states.ConwayState;

/**
<<<<<<< HEAD
 * A subclass of Cell for Conway's Game of Life simulation
=======
 * a subclass of the cell that creates the particular cell type for
 * Conway's game of life
 * 
 * @author Gabriel Chen
 *
>>>>>>> master
 */
public class ConwayCell extends Cell {
	
	/**
	 * Constructor for the <code>ConwayCell</code>
	 * @param initState
	 */
	public ConwayCell(ConwayState initState) {
		super(initState);
	}
	
	/**
	 * updates cells based on rules of Conway's game of life.
	 */
	@Override
	public void update() {
		int livingNeighbors = getLivingNeighbors();
		if(livingNeighbors < 2 && isAlive()){
			setNextState(ConwayState.DEAD);
		} else
		if((livingNeighbors == 2 || livingNeighbors == 3)&& isAlive()){
			setNextState(ConwayState.ALIVE);
		} else
		if(livingNeighbors > 3){
			setNextState(ConwayState.DEAD);
		} else
		if(livingNeighbors == 3 && !isAlive()){
			setNextState(ConwayState.ALIVE);}
		 else {
			setNextState(getCurrentState());
		}
	}
	
	/**
	 * Tells whether or not the current state is <code>ConwayState.ALIVE</code>
	 * @return whether or not the current state is <code>ConwayState.ALIVE</code>
	 */
	private boolean isAlive(){
		return this.getCurrentState().equals(ConwayState.ALIVE);
	}
	
	/**
	 * Iterates through neighbors to find number of living neighbors.
	 * @return the number of living neighbors
	 */
	private int getLivingNeighbors(){
		List<Cell> neighbors = getNeighbors();
		int livingCount = 0;
		for(Cell cell : neighbors){
			if(((ConwayCell) cell).isAlive()){
				livingCount++;
			}
		}
		return livingCount;
	}


	/**
	 * changes the state of the cell on click.
	 * cycles through the cell states available in the model.
	 */
	@Override
	public void changeStateOnClick() {
		if(isAlive()){
			setStateOnClick(ConwayState.DEAD);
		} else {
			setStateOnClick(ConwayState.ALIVE);
		}
	}
}
