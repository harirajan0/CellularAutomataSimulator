package main;

import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.stage.Stage;

public class ApplicationStartup extends Application {
	
	public static final String TITLE = "Cell Automata Simulator";
	public static final int WINDOW_SIZE = 600;
	private SimulationGUI GUI;
	private Controller controller;
	private ResourceBundle resources;
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";

	
	@Override
	public void start(Stage stage) throws Exception {
		resources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");
		GUI = new SimulationGUI("English");
		controller = new Controller(GUI);
		GUI.createButtons(controller, resources);
		
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
