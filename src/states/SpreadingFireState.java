/**
 * 
 */
package states;
import javafx.scene.paint.Color;

/**
 * Enum for states of the Spreading Fire simulation
 */
public enum SpreadingFireState implements State{
	
	EMPTY(Color.YELLOW, 0),
	TREE(Color.GREEN, 1),
    BURNING(Color.RED, 2);

    private Color myColor;
    private int stateValue;

    /**
     * Creates the state enum
     * @param color Color corresponding to <code>state</code>
     * @param state The state of the cell
     */
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
