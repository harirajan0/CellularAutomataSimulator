package model;
import java.util.HashMap;
import main.GraphPanel;
import cells.ConwayCell;
import loader.XMLParser;
import main.Controller;
import states.ConwayState;
import cells.Cell;
import java.util.Iterator;

import resources.Resources;
import states.ConwayState;
public class ConwayModel extends Model {
	private HashMap<Integer, ConwayState> stateMap = new HashMap<>();
	private GraphPanel graph;
	private int iteration;
	
	public ConwayModel(int r, int c, String shapeType) {
		super(r, c, shapeType);
		for (ConwayState state : ConwayState.values()) {
			stateMap.put(state.getStateValue(), state);
		}
		iteration = 0;
	}
	@Override
	public void populateCells(XMLParser parser, double param) {
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				int xPosition = row * sideLength;
				int yPosition = col * sideLength;
				try {					
					ConwayCell newCell = 
							new ConwayCell(stateMap.get(Character.getNumericValue(parser.getTextValue(String.format("row%d", row)).charAt(col))), xPosition, yPosition, sideLength);
					set(row, col, newCell);
				} 
				catch (StringIndexOutOfBoundsException e) {
					throw new StringIndexOutOfBoundsException(
							String.format("Cannot find cell state for row %d, col %d", 
									row, col));
				}
			}
		}
		graph = new GraphPanel(updateGraph());
	}
	
	@Override
	public HashMap<String, Double> updateGraph(){
		iteration++;
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
		HashMap<String, Double> pops = new HashMap<String, Double>();
		pops.put("Alive", liveCount);
		pops.put("Dead", deadCount);
		return pops;
	}
	
	@Override
	public void updateModel(){
		super.updateModel();
		graph.update(updateGraph(), iteration);
	}
}