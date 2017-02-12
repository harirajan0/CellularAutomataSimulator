package main;

import javafx.scene.layout.Pane;
import model.Model;

public class SimulationView {
		
	private Pane cellSimulationPane;
	
	public SimulationView(){
		cellSimulationPane = new Pane();
		cellSimulationPane.setMaxSize(Controller.WINDOW_SIZE, Controller.WINDOW_SIZE);		
	}
	
	public Pane getCellSimulationPane(){
		return cellSimulationPane;
	}
	

	/* Takes in the model and display it in the GUI.
	 * @param model The model to display
	 */
	
	public void displayGrid(Model model) {
		cellSimulationPane.getChildren().clear();
		for(int r = 0; r < model.getRows(); r++){
			for(int c = 0; c < model.getCols(); c++){
				cellSimulationPane.getChildren().add(model.get(r, c).getRect());
			}
		}
	}

}
