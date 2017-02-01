package cellsociety_team24;

<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.util.List;
import com.sun.javafx.geom.Shape;
>>>>>>> f2f70ee4eb72f1a66b1f2743ef76056406365eb8

public abstract class Cell {
	
	private String currentState;
	private String nextState;
<<<<<<< HEAD
	private ArrayList<Cell> neighbors;
	private double xPosition;
	private double yPosition;
	
	public Cell() {
		//sets xposition and yposition
=======
	private List<Cell> neighbors;
	private int xPosition;
	private int yPosition;
	private Shape myShape;

	public Cell(String initState, int x, int y) {
		currentState = initState;
		xPosition = x;
		yPosition = y;
>>>>>>> f2f70ee4eb72f1a66b1f2743ef76056406365eb8
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

}
