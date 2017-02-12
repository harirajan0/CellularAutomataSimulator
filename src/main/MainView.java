package main;

import javafx.stage.Stage;

public class MainView {

	private Controller myController;
	private Stage stage;
	public static final String TITLE = "Cellular Automata Simulator";

	
	public MainView(Stage s){
		stage = s;
		myController = new Controller();
		
		stage.setTitle(TITLE);
		stage.setScene(myController.getGUI().getScene());
		stage.show();
	}
}
