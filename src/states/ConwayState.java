/**
 * 
 */
package states;

import javafx.scene.paint.Color;

/**
 * Enum for the states for Conway's Game of Life
 */
public enum ConwayState implements State {

	ALIVE(Color.BLACK, 1),
	DEAD(Color.ALICEBLUE, 0);

    private Color myColor;
    private int stateValue;

    /**
     * Creates the state enum
     * @param color Color corresponding to <code>state</code>
     * @param state State of the cell
     */
    ConwayState(Color color, int state) {
        myColor = color;
        stateValue = state;
    }

    @Override
    public Color getColor() {
        return myColor;
    }

    @Override
    public int getStateValue() {
        return stateValue;
    }

}
