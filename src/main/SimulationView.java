package main;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import model.Model;

public class SimulationView {
		
//	private Pane cellSimulationPane;
	private Group cellSimulationGroup;
	
	public SimulationView(){
//		cellSimulationPane = new Pane();
		cellSimulationGroup = new Group();
//		cellSimulationPane.setMaxSize(Controller.WINDOW_SIZE, Controller.WINDOW_SIZE);		
	}
	
	public Group getCellSimulationGroup(){
		return cellSimulationGroup;
	}
	

	/* Takes in the model and display it in the GUI.
	 * @param model The model to display
	 */
	
	public void displayGrid(Model model) {
		cellSimulationGroup.getChildren().clear();
		for(int r = 0; r < model.getRows(); r++){
			for(int c = 0; c < model.getCols(); c++){
				cellSimulationGroup.getChildren().add(model.get(r, c).getRect());
			}
		}
	}

}
