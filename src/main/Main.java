package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static final String TITLE = "Cell Automata Simulator";
	
	@Override
	public void start(Stage stage) throws Exception {
		GUI myGUI = new GUI("English");
		stage.setTitle(TITLE);
		stage.setScene(myGUI.getScene());
		stage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
