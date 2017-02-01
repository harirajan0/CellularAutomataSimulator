package cellsociety_team24;
import java.util.ArrayList;

<<<<<<< HEAD
public class SpreadingFireCell extends Cell 
{	
	private static final String EMPTY = "empty";
	private static final String BURNING = "burning";
	private static final String TREE = "tree";
	private double probCatch;
	
	public SpreadingFireCell(double x, double y, String state, double prob)
	{
		super();
		setxPosition(x);
		setyPosition(y);
		setCurrentState(state);
		probCatch = prob;
		//setNeighbors(???);
	}
	
=======
public class SpreadingFireCell extends Cell {

	public SpreadingFireCell(String initState, int x, int y) {
		super(initState, x, y);
		// TODO Auto-generated constructor stub
	}

>>>>>>> f2f70ee4eb72f1a66b1f2743ef76056406365eb8
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
		ArrayList<Cell> neighbors = getNeighbors();
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
