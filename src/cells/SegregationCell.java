package cells;
import java.util.List;
import java.util.Random;

import javafx.scene.paint.Color;


/**
 * A subclass of Cell that creates the particular cell type for the 
 * Segregation simulation.
 * @author Bihan Zhuang
 *
 */
public class SegregationCell extends Cell {
	
	private static final String EMPTY = "empty";
	private static final String X = "X";
	private static final String O = "O";
	private List<Cell> emptyPositions;
	
	private double percentage;
	
	public void setAvailableList(List<Cell> availableList){
		emptyPositions = availableList;
	}
	
	public SegregationCell(String initState, int x, int y, int width, double percentage) {
		super(initState, x, y, width);
		this.percentage = percentage;
	}
	@Override
	public void update() {
		if (this.getCurrentState().equals(EMPTY)) {
			setNextState(this.getCurrentState());
			return;
		}
		List<Cell> neighbors = this.getNeighbors();
		if (!isSatisfied(neighbors)){
			changeStates(emptyPositions);
		} else{
			setNextState(this.getCurrentState());
		}
	}
	/**
	 * Updates the state of the Cell if it is dissatisfied and changes 
	 * a random Cell from the specified ArrayList of empty Cells to the 
	 * state of the original Cell (state 1 or state 2).
	 * @param emptyPositions
	 */
	
	/**
	 * Checks if the Cell is satisfied by comparing the percentage of 
	 * neighbors being the same type calculate, to the threshold percentage 
	 * specified by the user.
	 * @param neighbors
	 * @return
	 */
	private boolean isSatisfied(List<Cell> neighbors){
		double nonEmpty = 0, sameType = 0;
		for (Cell nb : neighbors){
			if (nb != null){
				if (!nb.getCurrentState().equals(EMPTY)){
					nonEmpty++;
				}
				if (nb.getCurrentState().equals(this.getCurrentState())){
					sameType++;
				}
			}
		}
		if (nonEmpty == 0) return true;
		return (sameType/nonEmpty) >= percentage;
	}
	
	/**
	 * Changes the NextState of the Cell to empty and randomly selects
	 * an empty Cell and sets its NextState to this Cell's CurrentState.
	 * @param emptyPositions
	 */
	private void changeStates(List<Cell> emptyPositions){
		this.setNextState(EMPTY);
		
		int option = new Random().nextInt(emptyPositions.size());
		Cell tmp = emptyPositions.get(option);
		tmp.setNextState(this.getCurrentState());
	}
	
	@Override
	public void paint(){
		switch(getCurrentState()){
			case EMPTY:
				setFill(Color.BLACK);
				break;
			case X:
				setFill(Color.BLUE);
				break;
			case O:
				setFill(Color.RED);
				break;
			default:
				break;
		}
	}
}