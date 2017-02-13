package main;

import javafx.stage.Stage;
import resources.Resources;

/**
 * Owns the controller and stage, keeping the two separated
 */
public class MainView {

	private Controller myController;
	private Stage stage;

	/**
	 * Creates an object to house the controller and stage
	 * @param s <code>Stage</code> to use
	 */
	public MainView(Stage s){
		stage = s;
		myController = new Controller();
		
		stage.setTitle(Resources.getString("Title"));
		stage.setScene(myController.getGUI().getScene());
		stage.show();
	}
}
