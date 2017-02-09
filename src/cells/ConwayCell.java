package cells;
import java.util.List;
import states.ConwayState;

public class ConwayCell extends Cell {
	//begin working on ConwayCell 02/02/2017

	/** Conway's game of life rules
	 * 1. Any live cell with fewer than two live neighbors dies, as if caused by under-population.
	 * 2. Any live cell with two or three live neighbors lives on to the next generation.
	 * 3. Any live cell with more than three live neighbors dies, as if by over-population.
	 * 4. Any ConwayState.DEAD cell with exactly three live neighbors becomes a live cell, as if by reproduction.
	 */
	
	public ConwayCell(ConwayState initState, int x, int y, int width) {
		super(initState, x, y, width);
	}
	
	@Override
	public void update() {
		int livingNeighbors = getLivingNeighbors();
		if(livingNeighbors < 2){
			setNextState(ConwayState.DEAD);
		} else
		if(((livingNeighbors == 2)&& isAlive()) || livingNeighbors == 3){
			setNextState(ConwayState.ALIVE);
		} else
		if(livingNeighbors > 3 && isAlive()){
			setNextState(ConwayState.DEAD);
		} else
		if(livingNeighbors > 3 && !isAlive()){
			setNextState(ConwayState.ALIVE);
		} else {
			setNextState(getCurrentState());
		}
	}
	
	/**
	 * Tells whether or not the current state is ConwayState.ALIVE
	 * @return whether or not the current state is ConwayState.ALIVE
	 */
	private boolean isAlive(){
		return getCurrentState().equals(ConwayState.ALIVE);
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
}
