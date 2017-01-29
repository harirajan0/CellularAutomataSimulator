package cellsociety_team24;

import java.util.ArrayList;

public abstract class Cell {
	
	private String state;
	private ArrayList<Cell> neighbors;
	private double xPosition;
	private double yPosition;
	
	public Cell() {
		//sets xposition and yposition
	}
	
	
	public abstract void update();


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


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

}
