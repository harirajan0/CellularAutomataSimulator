package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class ApplicationStartup extends Application {
	
	public static final String TITLE = "Cell Automata Simulator";
	public static final int WINDOW_SIZE = 600;
	private SimulationGUI GUI;
	private Controller controller;
	
	@Override
	public void start(Stage stage) throws Exception {
		GUI = new SimulationGUI("English");
		controller = new Controller(GUI);
		
		stage.setTitle(TITLE);
		stage.setScene(GUI.getScene());
		stage.show();
	}
	
	public void setUpComponents(){

	}

	public static void main(String[] args) {
		launch(args);
	}

}
