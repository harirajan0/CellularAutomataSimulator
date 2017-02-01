package cellsociety_team24;

import java.util.ArrayList;

public abstract class Cell {
	
	private String currentState;
	private String nextState;
	private ArrayList<Cell> neighbors;
	private double xPosition;
	private double yPosition;
	
	public Cell(double x, double y, String state) {
		setxPosition(x);
		setyPosition(y);
		setCurrentState(state);
	}
	
	public abstract void update();


	public ArrayList<Cell> getNeighbors() {
		return neighbors;
	}


	public void setNeighbors(ArrayList<Cell> neighbors) {
		this.neighbors = neighbors;
	}


	public double getxPosition() {
		return xPosition;
	}


	public void setxPosition(double xPosition) {
		this.xPosition = xPosition;
	}


	public double getyPosition() {
		return yPosition;
	}


	public void setyPosition(double yPosition) {
		this.yPosition = yPosition;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public String getNextState() {
		return nextState;
	}

	public void setNextState(String nextState) {
		this.nextState = nextState;
	}

}
