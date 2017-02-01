package cellsociety_team24;

import java.util.HashSet;

public class WaTorCell extends Cell {
	
	private int turnsToBreed;
	private int turnsToDie;

	@Override
	public void update() {
		// TODO Auto-generated method stub
		turnsToBreed++;
		if (getNextState() != "") { return; }
		
	}
	
	public void updateShark() {
		if (turnsToDie == 3) {//kill this shark
			setNextState("empty");
			resetFields();
		}
		WaTorCell fishToEat = getRandomNeighbor("fish");
		//if there is a fish to eat, then eat it, if not move somewhere if possible
		if (fishToEat != null) { eatFish(fishToEat); }
		else { move(); }
		if (turnsToBreed == 3) {//breed new shark in random adjacent cell
			WaTorCell newShark = getRandomNeighbor("empty");
			if (newShark != null) {
				newShark.setNextState("shark");
			}
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
			newLocation.setNextState("shark");
			newLocation.setFields(this);
			setNextState("empty");
			resetFields();
		} else {
			setNextState(getCurrentState());
		}
		turnsToDie++;
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
