/**
 * 
 */
package alerts;

import java.io.File;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import loader.XMLException;
import resources.Resources;
import sun.awt.CausedFocusEvent.Cause;
import javafx.scene.control.Alert.AlertType;

/**
 * @author harirajan
 *
 */
public class CellSocietyAlerts {
	
	public static boolean xmlError(Exception e, File file) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(Resources.getString("ErrorTitle"));
		alert.setHeaderText(Resources.getString("XMLErrorHeader"));
		alert.setContentText(String.format(Resources.getString("XMLErrorMessage"), e.getMessage(), file.getName()));
		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == ButtonType.OK;
	}
}
