package cellsociety_team24;
import java.util.List;

public class SpreadingFireCell extends Cell 
{	
	private static final String EMPTY = "empty";
	private static final String BURNING = "burning";
	private static final String TREE = "tree";
	private double probCatch;
	
	public SpreadingFireCell(String initState, int x, int y, double prob)
	{
		super(initState, x, y);
		probCatch = prob;
	}

	@Override
	public void update() 
	{
		if(isState(EMPTY))
		{
			setNextState(EMPTY);
		}
		else if(isState(TREE))
		{
			setTreeState();
		}
		else if(isState(BURNING))
		{
			setNextState(EMPTY);
		}
	}
	
	private boolean isState(String state)
	{
		return getCurrentState().equals(state);
	}
	
	private void setTreeState()
	{
		List<Cell> neighbors = getNeighbors();
		for(Cell cell : neighbors)
		{
			if(((SpreadingFireCell)cell).isState(BURNING))
			{
				if(Math.random() <= probCatch)
				{
					setNextState(BURNING);
					return;
				}
			}
		}
	}
}
