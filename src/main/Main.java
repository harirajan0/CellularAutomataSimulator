package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static final String TITLE = "Cell Automata Simulator";
	
	@Override
	public void start(Stage stage) throws Exception {
		SimulationGUI myGUI = new SimulationGUI("English");
		Controller controller = new Controller(stage, myGUI);
		stage.setTitle(TITLE);
		stage.setScene(myGUI.getScene());
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
