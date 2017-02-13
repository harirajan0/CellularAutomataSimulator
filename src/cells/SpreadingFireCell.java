package cells;
import java.util.List;
import states.SpreadingFireState;

/**
 * A subclass of <code>Cell</code> for the Spreading Fire simulation.
 */
public class SpreadingFireCell extends Cell 
{	
	private double probCatch;
	
	/**
	 * Constructor for the <code>SpreadingFireCell</code>
	 * @param initState The initial state of the cell
	 * @param prob Probability of fire catching to a neighboring cell
	 */
	public SpreadingFireCell(SpreadingFireState initState, double prob){
		super(initState);
		probCatch = prob;
	}

	@Override
	public void update() {
		if(isState(SpreadingFireState.EMPTY)){
			setNextState(SpreadingFireState.EMPTY);
		}
		else if(isState(SpreadingFireState.TREE)){
			setTreeState();
		}
		else if(isState(SpreadingFireState.BURNING)){
			
			setNextState(SpreadingFireState.EMPTY);
		} else {
			setNextState(getCurrentState());
		}
	}
	
	/**
	 * Sets the next state if the Cell is in the tree state
	 */
	private void setTreeState(){
		
		List<Cell> neighbors = getNeighbors();
		for(Cell cell : neighbors){
			
			if(cell.isState(SpreadingFireState.BURNING)){
				
				if(Math.random() <= probCatch){
					setNextState(SpreadingFireState.BURNING);
					return;
				} else{
					setNextState(getCurrentState());
				}
			}else {
				setNextState(getCurrentState());
			}
		}
	}

	@Override
	public void changeStateOnClick() {
		if(getCurrentState().equals(SpreadingFireState.EMPTY)){
			setStateOnClick(SpreadingFireState.TREE);
		} else if(getCurrentState().equals(SpreadingFireState.TREE)){
			setStateOnClick(SpreadingFireState.BURNING);

		} else {
			setStateOnClick(SpreadingFireState.EMPTY);

		}
	}
}
