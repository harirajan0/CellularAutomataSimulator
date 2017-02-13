package cells;
import java.util.List;
import states.ConwayState;


public class ConwayCell extends Cell {
	
	public ConwayCell(ConwayState initState) {
		super(initState);
	}
	
	/**
	 * 
	 */
	@Override
	public void update() {
		int livingNeighbors = getLivingNeighbors();
		if(livingNeighbors < 2){
			setNextState(ConwayState.DEAD);
		} else
		if(((livingNeighbors == 2)&& isAlive()) || (livingNeighbors == 3 && isAlive())){
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

	/**
	 * 
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
