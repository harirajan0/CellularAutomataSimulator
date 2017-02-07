package cells;

import java.util.HashSet;

import javafx.scene.paint.Color;

public class WaTorCell extends Cell {
	
	private static final String EMPTY = "empty";
	private static final String SHARK = "shark";
	private static final String FISH = "fish";
	
	private int turnsToBreed;
	private int turnsToDie;

	/**
	 * Constructor should initialize state and position on screen and set
	 * turnsToBreed and turnsToDie to 0
	 * @param initState initial state of cell
	 * @param xPosition x position of cell on screen
	 * @param yPosition y position of cell on screen
	 */
	public WaTorCell(String initState, int x, int y, int width) {
		super(initState, x, y, width);
		resetFields();
	}

	/**
	 * Updates cell based on current state
	 */
	@Override
	public void update() {
		if (getNextState() != null) { return; }
		if (getCurrentState().equals(EMPTY)) { 
			setNextState(getCurrentState());
		}
		turnsToBreed++;
		if (getCurrentState().equals(SHARK)) { 
			updateShark(); 
		}
		if (getCurrentState().equals(FISH)) { 
			updateFish(); 
		}
		if (getNextState() == null) {
			setNextState(getCurrentState());
		}
	}
	

	/**
	 * If a cell is a shark, cell should check if it should die, eat, move, and/or breed
	 */
	private void updateShark() {
		if (turnsToDie == 3) {//kill this shark
			setNextState(EMPTY);
			resetFields();
		}
		WaTorCell fishToEat = getRandomNeighbor(FISH);
		//if there is a fish to eat, then eat it, if not move somewhere if possible
		if (fishToEat != null) { 
			eatFish(fishToEat); 
		}
		else { 
			//if shark doesnt eat, its closer to dieing
			turnsToDie++;
			move(); 
		}
		if (turnsToBreed == 3) { breed(); }
	}
	
	/**
	 * If cell is a fish, it should try to move and/or breed
	 */
	private void updateFish() {
		move();
		if (turnsToBreed == 3) { breed(); }
	}
	

	/**
	 * Breed by setting a random neighbor's next state to this cell's current state
	 */
	private void breed() {
		WaTorCell newCell = getRandomNeighbor(EMPTY);
		if (newCell != null) {
			newCell.setNextState(getCurrentState());
		}
	}
	
	/**
	 * Eat fish by setting fish cell to empty and resetting the number of turns this cell needs until death
	 * @param fish fish to be eaten
	 */
	private void eatFish(WaTorCell fish) {
		fish.setNextState(EMPTY);
		fish.resetFields();
		turnsToDie = 0;
	}
	
	/**
	 * move by getting random neighbor and setting all of that cell's fields to our current
	 * fields and reset our fields to initial empty state
	 */
	private void move() {
		WaTorCell newLocation = getRandomNeighbor(EMPTY);
		if (newLocation != null) {
			newLocation.setNextState(getCurrentState());
			newLocation.setFields(this);
			setNextState(EMPTY);
			resetFields();
		} else {
			setNextState(getCurrentState());
		}
	}
	
	/**
	 * gets random neighbor of the given state
	 * @param state requested state
	 * @return random WaTorCell neighbor of given state
	 */
	private WaTorCell getRandomNeighbor(String state) {
		HashSet<Cell> possibleNeighbors = new HashSet<>();
		for (Cell cell : getNeighbors()) {
			if (cell.getCurrentState().equals(state)) {possibleNeighbors.add(cell); }
		}
		for (Cell cell : possibleNeighbors) { return (WaTorCell) cell; }
		return null;
	}

	private void resetFields() {
		turnsToDie = 0;
		turnsToBreed = 0;
	}
	
	/**
	 * sets fields of other cell to match this cell
	 * @param cell WaTorCell that needs to have its fields cell
	 */
	private void setFields(WaTorCell cell) {
		turnsToDie = cell.turnsToDie;
		turnsToBreed = cell.turnsToBreed;
	}

	@Override
	public void paint(){
		switch(getCurrentState()){
			case EMPTY:
				setFill(Color.BLUE);
				break;
			case FISH:
				setFill(Color.GREEN);
				break;
			case SHARK:
				setFill(Color.YELLOW);
				break;
			default:
				break;
		}
	}
}