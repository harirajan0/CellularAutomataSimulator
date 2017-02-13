package cells;
import java.util.List;
import states.SegregationState;


/**
 * A subclass of <code>Cell</code> for the Segregation simulation.
 */
public class SegregationCell extends Cell {

	private List<Cell> emptyPositions;
	private double percentage;
	
	/**
	 * Constructor for the <code>SegregationCell</code>
	 * @param initState Initial state of the cell
	 * @param percentage Percentage of neighbors needed to be alike for cell to be satisfied
	 */
	public SegregationCell(SegregationState initState, double percentage) {
		super(initState);
		this.percentage = percentage;
	}
	
	/**
	 * Sets the global list of empty positions
	 * @param availableList the list of empty positions
	 */
	public void setAvailableList(List<Cell> availableList){
		emptyPositions = availableList;
	}
	
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