/**
 * 
 */
package states;

import javafx.scene.paint.Color;

/**
 * @author harirajan
 *
 */
public enum ConwayState implements State {
	

	ALIVE(Color.ALICEBLUE, 0),
	DEAD(Color.BLACK, 1);

    private Color myColor;
    private int stateValue;

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
