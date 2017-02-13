package main;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Starts the application
 */
public class ApplicationStartup extends Application{

	/**
	 * This class starts the application. It's sole purpose is to start
	 * the application. It should never be touched again. No implementation
	 * should be done in this class.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		@SuppressWarnings("unused")
		MainView mainView = new MainView(stage);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
