/**
 * 
 */
package states;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import resources.Resources;

/**
 * @author harirajan
 *
 */
public enum SpreadingFireState implements State{
	
	EMPTY(Color.YELLOW, 0, Resources.EMPTY),
	TREE(Color.GREEN, 1, Resources.TREE),
    BURNING(Color.RED, 2, Resources.BURNING);

    private Color myColor;
    private int stateValue;
    private String stateString;

    SpreadingFireState(Color color, int state, String stateStr) {
        myColor = color;
        stateValue = state;
        stateString = stateStr;
    }

    @Override
    public Color getColor() {
        return myColor;
    }

    @Override
    public int getStateValue() {
        return stateValue;
    }
    
    @Override
	public String getStateAsString() {
		return stateString;
	}
    
    @Override
	public List<String> getPossibleStatesAsString() {
		List<String> states = new ArrayList<String>();
		for (SpreadingFireState state : values()) states.add(state.getStateAsString());
		return states;
	}

}
