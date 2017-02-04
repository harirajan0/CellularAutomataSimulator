package cells;

import java.util.ArrayList;
import java.util.HashSet;

import cellsociety_team24.Cell;

public class WaTorCell extends Cell {
	
	private static final String EMPTY = "empty";
	private static final String SHARK = "shark";
	private static final String FISH = "fish";
	
	private int turnsToBreed;
	private int turnsToDie;
	
	public WaTorCell(String initState, int xPosition, int yPosition) {
		super(initState, xPosition, yPosition);
		resetFields();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (getNextState() != "") { return; }
		if (getCurrentState().equals(EMPTY)) { return; }
		turnsToBreed++;
		if (getCurrentState().equals(SHARK)) { updateShark(); }
		if (getCurrentState().equals(FISH)) { updateShark(); }
	}
	
	public void updateShark() {
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
	
	public void updateFish() {
		move();
		if (turnsToBreed == 3) { breed(); }
	}
	
	private void breed() {
		WaTorCell newCell = getRandomNeighbor(EMPTY);
		if (newCell != null) {
			newCell.setNextState(getCurrentState());
		}
	}
	
	private void eatFish(WaTorCell fish) {
		fish.setNextState(EMPTY);
		fish.resetFields();
		turnsToDie = 0;
	}
	
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
	
	private void setFields(WaTorCell cell) {
		turnsToDie = cell.turnsToDie;
		turnsToBreed = cell.turnsToBreed;
	}

}