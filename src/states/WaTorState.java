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
	
	EMPTY(Color.BLUE, 0),
	FISH(Color.GREEN, 1),
    SHARK(Color.GRAY, 2);

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
