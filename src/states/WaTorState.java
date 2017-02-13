/**
 * 
 */
package states;

import javafx.scene.paint.Color;

/**
 * @author harirajan
 *
 */
public enum WaTorState implements State {
	
	EMPTY(Color.web("#42c5f4"), 0),
	FISH(Color.web("#00ff00"), 1),
    SHARK(Color.web("#59636b"), 2);

    private Color myColor;
    private int stateValue;

    WaTorState(Color color, int state) {
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
