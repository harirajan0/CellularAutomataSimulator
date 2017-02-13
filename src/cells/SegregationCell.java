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
	
	/**
	 * 
	 * @param availableList
	 */
	public void setAvailableList(List<Cell> availableList){
		emptyPositions = availableList;
	}
	
	/**
	 * 
	 * @param initState
	 * @param percentage
	 */
	public SegregationCell(SegregationState initState, double percentage) {
		super(initState);
		this.percentage = percentage;
	}
	
	/**
	 * 
	 */
	@Override
	public void update() {
		if (this.getNextState() != null) return;
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
		int option = (int)(Math.random()*emptyPositions.size());
		Cell tmp = emptyPositions.get(option);
		tmp.setNextState(this.getCurrentState());
	}
	
	/**
	 * 
	 */
	@Override
	public void changeStateOnClick() {
		if(getCurrentState().equals(SegregationState.EMPTY)){
			setStateOnClick(SegregationState.X);
		} else if(getCurrentState().equals(SegregationState.X)){
			setStateOnClick(SegregationState.O);

		} else {
			setStateOnClick(SegregationState.EMPTY);

		}
	}
}