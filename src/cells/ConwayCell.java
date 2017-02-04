package cells;

import cellsociety_team24.Cell;

import java.util.List;

public class ConwayCell extends Cell {

	//begin working on ConwayCell 02/02/2017
	
	private static final String ALIVE = "alive";
	private static final String DEAD = "dead";
	
	/** Conway's game of life rules
	 * 1. Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
	 * 2. Any live cell with two or three live neighbours lives on to the next generation.
	 * 3. Any live cell with more than three live neighbours dies, as if by overpopulation.
	 * 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
	 */
	
	public ConwayCell(String initState, int x, int y) {
		super(initState, x, y);
	}

	@Override
	public void update() {
		int livingNeighbors = getLivingNeighbors();
		if(livingNeighbors < 2){
			setNextState(DEAD);
		}
		if(livingNeighbors == 2 || livingNeighbors == 3){
			setNextState(ALIVE);
		}
		if(livingNeighbors > 3){
			setNextState(DEAD);
		}
		if(livingNeighbors > 3 && !isAlive()){
			setNextState(ALIVE);
		}
	}
	
	private boolean isAlive(){
		return getCurrentState().equals(ALIVE);
	}
	
	/**
	 * Iterates through neighbors to find number of living neighbors.
	 * @return
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

