/**
 * 
 */
package states;


import java.util.ArrayList;
import java.util.List;


import javafx.scene.paint.Color;
import resources.Resources;

/**
 * Enum for the states of the WaTor simulation
 */
public enum WaTorState implements State {
	
	EMPTY(Color.web("#42c5f4"), 0, Resources.EMPTY),
	FISH(Color.web("#00ff00"), 1, Resources.FISH),
    SHARK(Color.web("#59636b"), 2, Resources.SHARK);

    private Color myColor;
    private int stateValue;
    private String stateString;


    /**
     * Creates the state enum
     * @param color Color corresponding to <code>state</code>
     * @param state The state of the cell
     */
    WaTorState(Color color, int state, String stateStr) {
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

	/* (non-Javadoc)
	 * @see states.State#getStateAsString()
	 */
	@Override
	public String getStateAsString() {
		return stateString;
	}

	/* (non-Javadoc)
	 * @see states.State#getPossibleStatesAsString()
	 */
	@Override
	public List<String> getPossibleStatesAsString() {
		List<String> states = new ArrayList<String>();
		for (WaTorState state : values()) states.add(state.getStateAsString());
		return states;
	}

}
