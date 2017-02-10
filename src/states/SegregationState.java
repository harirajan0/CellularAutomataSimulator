/**
 * 
 */
package states;

import javafx.scene.paint.Color;

/**
 * @author harirajan
 *
 */
public enum SegregationState implements State {

	EMPTY(Color.WHITE, 0),
	X(Color.RED, 1),
    O(Color.BLUE, 2);

    private Color myColor;
    private int stateValue;

    SegregationState(Color color, int state) {
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
