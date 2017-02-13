package main;

import javafx.stage.Stage;
import resources.Resources;

public class MainView {

	private Controller myController;
	private Stage stage;

	
	public MainView(Stage s){
		stage = s;
		myController = new Controller();
		
		stage.setTitle(Resources.getString("Title"));
		stage.setScene(myController.getGUI().getScene());
		stage.show();
	}
}
