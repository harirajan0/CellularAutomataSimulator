/**
 * 
 */
package states;
import javafx.scene.paint.Color;

/**
 * Enum for the states of the Segregation simulation
 */
public enum SegregationState implements State {

	EMPTY(Color.WHITE, 0),
	X(Color.RED, 1),
    O(Color.BLUE, 2);

    private Color myColor;
    private int stateValue;

    /**
     * Creates the state enum 
     * @param color Color corresponding to <code>state</code>
     * @param state The state of the cell
     */
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
