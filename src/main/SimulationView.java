package main;

import javafx.scene.layout.Pane;
import model.Model;

public class SimulationView {
	
	public static final int SIMULATION_SIZE = 400;
	
	private Pane cellSimulationPane;
	
	public SimulationView(){
		cellSimulationPane = new Pane();
		cellSimulationPane.setMaxSize(400, 400);
//		cellSimulationPane.setStyle("-fx-background-color: black;");
		
	}
	
	public Pane getCellSimulationPane(){
		return cellSimulationPane;
	}
	

	/* Takes in the model and display it in the GUI.
	 * @param model The model to display
	 */
	
	public void displayGrid(Model model) {
		for(int r = 0; r < model.getRows(); r++){
			for(int c = 0; c < model.getCols(); c++){
				cellSimulationPane.getChildren().add(model.get(r, c).getRect());
			}
		}
	}

}
