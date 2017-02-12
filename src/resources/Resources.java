/**
 * 
 */
package resources;

import java.util.ResourceBundle;

/**
 * @author harirajan
 *
 */
public class Resources {
	
	public static final ResourceBundle RESOURCES = ResourceBundle.getBundle("resources/English");
	
	public static String getString(String key) {
		return RESOURCES.getString(key);
	}

}
