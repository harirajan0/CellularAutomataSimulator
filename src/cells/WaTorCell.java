package cells;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import states.WaTorState;
import states.State;

/**
 * A subclass of <code>Cell</code> for the WaTor simulation.
 */
public class WaTorCell extends Cell {
	
	private int turnsToBreed;
	private int turnsToDie;

	/**
	 * Constructor for the <code>WaTorCell</code>
	 * @param initState Initial state of the cell
	 */
	public WaTorCell(WaTorState initState) {
		super(initState);
		resetFields();
	}

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
	 * Eat fish by setting fish cell to <code>WaTorState.EMPTY</code> 
	 * and resetting the number of turns this cell needs until death
	 * @param fish fish to be eaten
	 */
	private void eatFish(WaTorCell fish) {
		fish.setNextState(WaTorState.EMPTY);
		fish.resetFields();
		turnsToDie = 0;
	}
	
	/**
	 * move by getting random neighbor and setting all of that cell's fields to our current
	 * fields and reset our fields to initial <code>WaTorState.EMPTY</code> state
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
	 * @return random <code>WaTorCell</code> neighbor of given state
	 */
	private WaTorCell getRandomNeighbor(State state) {
		List<Cell> possibleNeighbors = new ArrayList<>();
		int num = 0;
		for (Cell cell : getNeighbors()) {
			num++;
			if (cell.isState(state)) {
				if (cell.getNextState() == null) possibleNeighbors.add(cell);
				else if (cell.getNextState().equals(state)) possibleNeighbors.add(cell);
			}
		}
		System.out.println(num);
		if (!possibleNeighbors.isEmpty()) {
			return (WaTorCell) possibleNeighbors.get(new Random().nextInt(possibleNeighbors.size())); 
		}
		return null;
	}
	
	/**
	 * Resets the number of turns for the cell to die and breed.
	 */
	private void resetFields() {
		turnsToDie = 0;
		turnsToBreed = 0;
	}
	
	/**
	 * sets fields of other cell to match this cell
	 * @param cell <code>WaTorCell</code> that needs to have its fields cell
	 */
	private void setFields(WaTorCell cell) {
		turnsToDie = cell.turnsToDie;
		turnsToBreed = cell.turnsToBreed;
	}
	
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