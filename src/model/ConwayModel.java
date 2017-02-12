package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import cells.ConwayCell;
import loader.XMLParser;
import resources.Resources;
import states.ConwayState;
import cells.Cell;

public class ConwayModel extends Model {
	private HashMap<Integer, ConwayState> stateMap = new HashMap<>();
	
	public ConwayModel(int r, int c, String shapeType) {
		super(r, c, shapeType);
		for (ConwayState state : ConwayState.values()) {
			stateMap.put(state.getStateValue(), state);
		}
		createGraphPanel("Alive", "Dead");
	}
	
	@Override
	public void populateCells(XMLParser parser, double param) {
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				try {
					ConwayCell newCell = new ConwayCell(stateMap.get(Character.getNumericValue(parser.getTextValue(String.format("row%d", row)).charAt(col))));
					set(row, col, newCell);
				} catch (StringIndexOutOfBoundsException e) {
					throw new StringIndexOutOfBoundsException(String.format(Resources.getString("InvalidCellDataMessage"), row, col));
				}
			}
		}
	}
	
	@Override
	public List<Double> updateGraph(){
		double liveCount = 0;
		double deadCount = 0;
		Iterator<Cell> itr = iterator();
		while(itr.hasNext()){
			ConwayState state = (ConwayState)itr.next().getCurrentState();
			switch(state){
				case ALIVE : 
					liveCount++;
					break;
				case DEAD : 
					deadCount++;
					break;
				default : 
					break;
			}
		}
		liveCount /= liveCount + deadCount;
		deadCount /= liveCount + deadCount;
		ArrayList<Double> pops = new ArrayList<Double>();
		pops.add(liveCount);
		pops.add(deadCount);
		return pops;
	}
}