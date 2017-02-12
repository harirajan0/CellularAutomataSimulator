package main;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.Model;

public class SimulationView {
		
//	private Pane cellSimulationPane;
	private Group cellSimulationGroup;
	private StackPane cellSimStackPane;
	private double currScale;
	
	public SimulationView(){
//		cellSimulationPane = new Pane();
		cellSimulationGroup = new Group();
		cellSimStackPane = makeSimulationStackPane();
		cellSimStackPane.setMinWidth(600);
		cellSimStackPane.setMinHeight(600);

		currScale = 1;
//		cellSimulationPane.setMaxSize(Controller.WINDOW_SIZE, Controller.WINDOW_SIZE);		
	}
	
	public void zoomIn(){
		currScale = currScale * 2;
		cellSimulationGroup.setScaleX(currScale);
		cellSimulationGroup.setScaleY(currScale);
		cellSimStackPane.setMinWidth(cellSimStackPane.getMinWidth() * 2);
		cellSimStackPane.setMinHeight(cellSimStackPane.getMinHeight() * 2);

//		System.out.println(currScale);
	}
	
	public void zoomOut(){
		currScale = currScale * .5;
		cellSimulationGroup.setScaleX(currScale);
		cellSimulationGroup.setScaleY(currScale);
		cellSimStackPane.setMinWidth(cellSimStackPane.getMinWidth() * .5);
		cellSimStackPane.setMinHeight(cellSimStackPane.getMinHeight() * .5);

//		System.out.println(currScale);
	}
	public void zoomReset(){
		currScale = 1;
		cellSimulationGroup.setScaleX(currScale);
		cellSimulationGroup.setScaleY(currScale);
		cellSimStackPane.setMinWidth(600);
		cellSimStackPane.setMinHeight(600);

//		System.out.println(currScale);

	}
	
	public Group getCellSimulationGroup(){
		return cellSimulationGroup;
	}
	
	public StackPane getSimulationStackPane(){
		return cellSimStackPane;
	}
	
    private StackPane makeSimulationStackPane(){
    	StackPane zoomPane = new StackPane();
    	zoomPane.getChildren().add(cellSimulationGroup);
    	return zoomPane;
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
