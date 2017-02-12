/**
 * 
 */
package alerts;

import java.io.File;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import loader.XMLException;
import sun.awt.CausedFocusEvent.Cause;
import javafx.scene.control.Alert.AlertType;

/**
 * @author harirajan
 *
 */
public class CellSocietyAlerts {
	
	public static boolean tagNameError(XMLException e, File file) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Problem with chosen file");
		alert.setContentText(String.format("%s in %s. Please choose a new correctly formatted file", e.getMessage(), file.getName()));
		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == ButtonType.OK;
	}
	
	public static boolean cellDataError(StringIndexOutOfBoundsException e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Problem with chosen file");
		alert.setContentText(String.format("%s. Please choose a new correctly formatted file", e.getMessage()));
		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == ButtonType.OK;
	}
	

}
