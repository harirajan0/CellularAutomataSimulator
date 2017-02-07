package cells;
import java.util.List;
import javafx.scene.paint.Color;

public class SpreadingFireCell extends Cell 
{	
	private static final String EMPTY = "empty";
	private static final String BURNING = "burning";
	private static final String TREE = "tree";
	private double probCatch;
	
	public SpreadingFireCell(String initState, int x, int y, int width, double prob){
		
		super(initState, x, y, width);
		probCatch = prob;
	}

	@Override
	public void update() {
		
		if(isState(EMPTY)){
			
			setNextState(EMPTY);
		}
		else if(isState(TREE)){
			
			setTreeState();
		}
		else if(isState(BURNING)){
			
			setNextState(EMPTY);
		}
		paint();
	}
	
	/**
	 * Tells whether or not the cell's current state is <code>state</code>.
	 * @param state State to test
	 * @return Whether or not <code>state</code> is the current state
	 */
	private boolean isState(String state){
		
		return getCurrentState().equals(state);
	}
	
	/**
	 * Sets the next state if the Cell is in the tree state
	 */
	private void setTreeState(){
		
		List<Cell> neighbors = getNeighbors();
		for(Cell cell : neighbors){
			
			if(((SpreadingFireCell)cell).isState(BURNING)){
				
				if(Math.random() <= probCatch){
					
					setNextState(BURNING);
					return;
				}
			}
		}
	}
	
	@Override
	public void paint(){
		switch(getCurrentState()){
			case EMPTY:
				setFill(Color.BLACK);
			case BURNING:
				setFill(Color.RED);
			case TREE:
				setFill(Color.GREEN);
			default:
				break;
		}
	}
}
