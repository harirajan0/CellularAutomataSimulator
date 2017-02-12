package cells;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import states.WaTorState;
import states.SpreadingFireState;
import states.State;
public class WaTorCell extends Cell {
	
	private int turnsToBreed;
	private int turnsToDie;
	/**
	 * Constructor should initialize state and position on screen and set
	 * turnsToBreed and turnsToDie to 0
	 * @param initState initial state of cell
	 * @param xPosition x position of cell on screen
	 * @param yPosition y position of cell on screen
	 */
	public WaTorCell(WaTorState initState, int x, int y, int width) {
		super(initState, x, y, width);
		resetFields();
	}
	/**
	 * Updates cell based on current state
	 */
	@Override
	public void update() {
		if (getNextState() != null) return;
		if (isState(WaTorState.EMPTY)) setNextState(getCurrentState());
		else turnsToBreed++;
		if (isState(WaTorState.SHARK)) updateShark(); 
		if (isState(WaTorState.FISH)) updateFish(); 
		if (getNextState() == null) setNextState(getCurrentState());
	}
	
	/**
	 * If a cell is a shark, cell should check if it should die, eat, move, and/or breed
	 */
	private void updateShark() {
		WaTorCell fishToEat = getRandomNeighbor(WaTorState.FISH);
		if (fishToEat!= null) eatFish(fishToEat); 
		else turnsToDie++; 
		if (turnsToBreed == 3) breed();
		if (turnsToDie == 3) {
			setNextState(WaTorState.EMPTY);
			resetFields();
			return;
		}
		if (fishToEat == null) move();
	}
	
	/**
	 * If cell is a fish, it should try to move and/or breed
	 */
	private void updateFish() {
		if (turnsToBreed == 3) breed();
		move();
	}
	
	/**
	 * Breed by setting a random neighbor's next state to this cell's current state
	 */
	private void breed() {
		turnsToBreed = 0;
		WaTorCell newCell;
		if ((newCell = getRandomNeighbor(WaTorState.EMPTY)) != null) newCell.setNextState(getCurrentState());
	}
	
	/**
	 * Eat fish by setting fish cell to WaTorState.EMPTY and resetting the number of turns this cell needs until death
	 * @param fish fish to be eaten
	 */
	private void eatFish(WaTorCell fish) {
		fish.setNextState(WaTorState.EMPTY);
		fish.resetFields();
		turnsToDie = 0;
	}
	
	/**
	 * move by getting random neighbor and setting all of that cell's fields to our current
	 * fields and reset our fields to initial WaTorState.EMPTY state
	 */
	private void move() {
		WaTorCell newLocation;
		if ((newLocation = getRandomNeighbor(WaTorState.EMPTY)) != null) {
			newLocation.setNextState(getCurrentState());
			newLocation.setFields(this);
			setNextState(WaTorState.EMPTY);
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
	private WaTorCell getRandomNeighbor(State state) {
		List<Cell> possibleNeighbors = new ArrayList<>();
		for (Cell cell : getNeighbors()) {
			if (cell.isState(state)) {
				if (cell.getNextState() == null) possibleNeighbors.add(cell);
				else if (cell.getNextState().equals(state)) possibleNeighbors.add(cell);
			}
		}
		if (!possibleNeighbors.isEmpty()) {
			return (WaTorCell) possibleNeighbors.get(new Random().nextInt(possibleNeighbors.size())); 
		}
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
	
	// check super cell to see general comment
	@Override
	public void changeStateOnClick() {
		if(getCurrentState().equals(WaTorState.EMPTY)){
			setStateOnClick(WaTorState.FISH);
		} else if(getCurrentState().equals(WaTorState.FISH)){
			setStateOnClick(WaTorState.SHARK);

		} else {
			setStateOnClick(WaTorState.EMPTY);

		}		
	}

}