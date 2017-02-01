package cellsociety_team24;

import java.util.List;
import com.sun.javafx.geom.Shape;

public abstract class Cell {
	
	private String currentState;
	private String nextState;
	private List<Cell> neighbors;
	private int xPosition;
	private int yPosition;
	private Shape myShape;

	public Cell(String initState, int x, int y) {
		currentState = initState;
		xPosition = x;
		yPosition = y;
	}
	
	public abstract void update();


	public List<Cell> getNeighbors() {
		return neighbors;
	}


	public void setNeighbors(List<Cell> neighbors) {
		this.neighbors = neighbors;
	}


	public double getxPosition() {
		return xPosition;
	}


	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}


	public double getyPosition() {
		return yPosition;
	}


	public void setyPosition(int yPosition) {
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

	public Shape getMyShape() {
		return myShape;
	}

	public void setMyShape(Shape myShape) {
		this.myShape = myShape;
	}

}
