package cellsociety_team24;

import java.util.ArrayList;
import java.util.HashSet;

public class WaTorCell extends Cell {
	
	private int turnsToBreed;
	private int turnsToDie;
	
	public WaTorCell(String currentState, double xPosition, double yPosition) {
		setCurrentState(currentState);
		setxPosition(xPosition);
		setyPosition(yPosition);
		resetFields();
	}

	public void addNeighbor(WaTorCell neighbor) {
		ArrayList<Cell> newNeighbors = getNeighbors();
		newNeighbors.add(neighbor);
		setNeighbors(newNeighbors);
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		turnsToBreed++;
		if (getNextState() != "") { return; }
		else if (getCurrentState().equals("empty")) { return; }
		else if (getCurrentState().equals("shark")) { updateShark(); }
		else if (getCurrentState().equals("fish")) { updateShark(); }
	}
	
	public void updateShark() {
		turnsToBreed++;
		if (turnsToDie == 3) {//kill this shark
			setNextState("empty");
			resetFields();
		}
		WaTorCell fishToEat = getRandomNeighbor("fish");
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
		turnsToBreed++;
		move();
		if (turnsToBreed == 3) { breed(); }
	}
	
	private void breed() {
		WaTorCell newCell = getRandomNeighbor("empty");
		if (newCell != null) {
			newCell.setNextState(getCurrentState());
		}
	}
	
	private void eatFish(WaTorCell fish) {
		fish.setNextState("empty");
		fish.resetFields();
		turnsToDie = 0;
	}
	
	private void move() {
		WaTorCell newLocation = getRandomNeighbor("empty");
		if (newLocation != null) {
			newLocation.setNextState(getCurrentState());
			newLocation.setFields(this);
			setNextState("empty");
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