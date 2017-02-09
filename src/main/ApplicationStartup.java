package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class ApplicationStartup extends Application {
	
	public static final String TITLE = "Cell Automata Simulator";
	public static final int WINDOW_SIZE = 600;
	private Controller myController;
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";

	
	@Override
	public void start(Stage stage) throws Exception {
		myController = new Controller();
		stage.setTitle(TITLE);
		stage.setScene(myController.getGUI().getScene());
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
