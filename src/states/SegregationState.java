/**
 * 
 */
package states;


import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import resources.Resources;

/**
 * Enum for the states of the Segregation simulation
 */
public enum SegregationState implements State {

	EMPTY(Color.WHITE, 0, Resources.EMPTY),
	X(Color.RED, 1, Resources.X),
    O(Color.BLUE, 2, Resources.O);

    private Color myColor;
    private int stateValue;
    private String stateString;


    /**
     * Creates the state enum 
     * @param color Color corresponding to <code>state</code>
     * @param state The state of the cell
     */
    SegregationState(Color color, int state, String stateStr) {
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
		for (SegregationState state : values()) states.add(state.getStateAsString());
		return states;
	}

}
