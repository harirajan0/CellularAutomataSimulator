package alerts;
import java.io.File;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import resources.Resources;
import javafx.scene.control.Alert.AlertType;

/**
 * Pop-up alert that displays when the user interacts incorrectly with the program
 */
public class CellSocietyAlerts {
	
	/**
	 * Displays an alert for an error with an XML file
	 * @param e exception that caused the error
	 * @param file XML file that caused the error
	 * @return true when OK button is pressed
	 */
	public static boolean xmlError(Exception e, File file) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(Resources.getString("ErrorTitle"));
		alert.setHeaderText(Resources.getString("XMLErrorHeader"));
		alert.setContentText(String.format(Resources.getString("XMLErrorMessage"), e.getMessage(), file.getName()));
		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == ButtonType.OK;
	}
	
	/**
	 * Displays alert to notify user that an XML file has been generated and saved
	 * @param filePath path to wear the file is saved
	 */
	public static void xmlGenerated(String filePath) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(Resources.getString("XMLSavedTitle"));
		alert.setHeaderText(Resources.getString("XMLSavedHeader"));
		alert.setContentText(String.format(Resources.getString("XMLSavedMessage"), filePath));
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) alert.close();
	}
}
