/**
 * 
 */
package states;


import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import resources.Resources;

/**
 * Enum for the states for Conway's Game of Life
 */
public enum ConwayState implements State {

	ALIVE(Color.BLACK, 1, Resources.ALIVE),
	DEAD(Color.ALICEBLUE, 0, Resources.DEAD);

    private Color myColor;
    private int stateValue;
    private String stateString;


    /**
     * Creates the state enum
     * @param color Color corresponding to <code>state</code>
     * @param state State of the cell
     */
    ConwayState(Color color, int state, String stateStr) {
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
		for (ConwayState state : values()) states.add(state.getStateAsString());
		return states;
	}

}
