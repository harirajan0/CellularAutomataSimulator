/**
 * 
 */
package states;

import java.util.List;

import javafx.scene.paint.Color;

/**
 * @author harirajan
 *
 */
public interface State {
	
	public Color getColor();
	
	public int getStateValue();
	
	public String getStateAsString();
	
	public List<String> getPossibleStatesAsString();
	
}
