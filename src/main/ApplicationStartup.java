package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class ApplicationStartup extends Application{
	
	@SuppressWarnings("unused")
	@Override
	public void start(Stage stage) throws Exception {
		MainView mainView = new MainView(stage);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
