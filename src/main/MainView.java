package main;

import javafx.stage.Stage;

/**
 * Owns the controller and stage, keeping the two separated
 */
public class MainView {

	private Controller myController;
	private Stage stage;
	public static final String TITLE = "Cellular Automata Simulator";

	/**
	 * Creates an object to house the controller and stage
	 * @param s <code>Stage</code> to use
	 */
	public MainView(Stage s){
		stage = s;
		myController = new Controller();
		
		stage.setTitle(TITLE);
		stage.setScene(myController.getGUI().getScene());
		stage.show();
	}
}
