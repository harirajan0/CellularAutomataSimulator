package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class ApplicationStartup extends Application{
	/**
	 * This class starts the application. It's sole purpose is to start
	 * the application. It should never be touched again. No implementation
	 * should be done in this class.
	 */
	@SuppressWarnings("unused")
	@Override
	public void start(Stage stage) throws Exception {
		MainView mainView = new MainView(stage);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
