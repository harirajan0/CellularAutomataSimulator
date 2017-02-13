/**
 * 
 */
package states;

import java.util.List;

import javafx.scene.paint.Color;

/**
 * Interface for all state enums
 */
public interface State {
	
	/**
	 * Gets the color corresponding to the state
	 * @return The color corresponding to the state
	 */
	public Color getColor();
	
	/**
	 * Gets the integer value of the state
	 * @return The integer value of the state
	 */
	public int getStateValue();
	
	public String getStateAsString();
	
	public List<String> getPossibleStatesAsString();
	
}
