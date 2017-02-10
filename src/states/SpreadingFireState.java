/**
 * 
 */
package states;

import javafx.scene.paint.Color;

/**
 * @author harirajan
 *
 */
public enum SpreadingFireState implements State{
	
	EMPTY(Color.YELLOW, 0),
	TREE(Color.GREEN, 1),
    BURNING(Color.RED, 2);

    private Color myColor;
    private int stateValue;

    SpreadingFireState(Color color, int state) {
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
