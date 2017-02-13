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
public enum ConwayState implements State {
	

	ALIVE(Color.BLACK, 1, Resources.ALIVE),
	DEAD(Color.ALICEBLUE, 0, Resources.DEAD);

    private Color myColor;
    private int stateValue;
    private String stateString;

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
