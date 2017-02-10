package cells;
import java.util.List;
import states.SegregationState;


/**
 * A subclass of Cell that creates the particular cell type for the 
 * Segregation simulation.
 * @author Bihan Zhuang
 *
 */
public class SegregationCell extends Cell {
	

	private List<Cell> emptyPositions;
	
	private double percentage;
	
	public void setAvailableList(List<Cell> availableList){
		emptyPositions = availableList;
	}
	
	public SegregationCell(SegregationState initState, int x, int y, int width, double percentage) {
		super(initState, x, y, width);
		this.percentage = percentage;
	}
	@Override
	public void update() {
		if (this.getNextState() != null) {return; }
		if (this.getCurrentState().equals(SegregationState.EMPTY)) {
			setNextState(this.getCurrentState());
			return;
		}
		if (!isSatisfied()){
			changeStates();
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
	private boolean isSatisfied(){
		double nonEmpty = 0, sameType = 0;
		for (Cell nb : getNeighbors()){
			if (nb != null){
				if (!nb.getCurrentState().equals(SegregationState.EMPTY)){
					nonEmpty++;
				}
				if (nb.getCurrentState().equals(getCurrentState())){
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
	private void changeStates(){
		if (emptyPositions.size() == 0) {
			this.setNextState(getCurrentState());
			return;
		}
		this.setNextState(SegregationState.EMPTY);
		int option = (int)Math.random()*emptyPositions.size();
		Cell tmp = emptyPositions.get(option);
		tmp.setNextState(this.getCurrentState());
	}
}