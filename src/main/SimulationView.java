package main;


import java.util.ArrayList;
import java.util.List;

import cells.Cell;
import cellshapeviews.HexagonShapeView;
import cellshapeviews.PolygonShapeView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.Model;

public class SimulationView {
		
	private Pane cellSimulationPane;
	private List<PolygonShapeView> cellDisplay;

//	private Pane cellSimulationPane;
	private Group cellSimulationGroup;
	private StackPane cellSimStackPane;
	private double currScale;
	
	public SimulationView(){
		cellSimulationGroup = new Group();
		cellSimStackPane = makeSimulationStackPane();
		setMinSizeToDefault();
		currScale = 1;
	}
	
	private void setGroupScale(){
		cellSimulationGroup.setScaleX(currScale);
		cellSimulationGroup.setScaleY(currScale);
	}
	
	private void setStackPaneMinSize(double scaleFactor){
		cellSimStackPane.setMinWidth(cellSimStackPane.getMinWidth() * scaleFactor);
		cellSimStackPane.setMinHeight(cellSimStackPane.getMinHeight() * scaleFactor);
	}
	
	//Doubles Simulation view size
	public void zoomIn(){
		currScale = currScale * 2;
		setGroupScale();
		setStackPaneMinSize(2);
	}
	//halves simulation view size
	public void zoomOut(){
		currScale = currScale * .5;
		setGroupScale();
		setStackPaneMinSize(.5);
	}
	//resets simulation view size
	public void zoomReset(){
		currScale = 1;
		setGroupScale();
		setMinSizeToDefault();
	}
	private void setMinSizeToDefault(){
		cellSimStackPane.setMinWidth(Controller.INIT_WINDOW_SIZE);
		cellSimStackPane.setMinHeight(Controller.INIT_WINDOW_SIZE);
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
		int sideLength = Controller.INIT_WINDOW_SIZE / Math.max(model.getRows(), model.getCols());
		cellSimulationPane.getChildren().clear();
		for(int r = 0; r < model.getRows(); r++){
			for(int c = 0; c < model.getCols(); c++){
				PolygonShapeView psv = new HexagonShapeView(r, c, sideLength);
				Polygon polygon = psv.getPolygon();
				polygon.setFill(model.get(r, c).getCurrentState().getColor());
				polygon.setStroke(Color.BLACK);
				cellSimulationPane.getChildren().add(polygon);
				cellDisplay.add(psv);
			}
		}
	}
	
	/**
	 * Update view for each cell.
	 * @param model
	 */
	public void updateGrid(Model model){
		for (PolygonShapeView psv : cellDisplay){
			Cell tmpCell = model.get(psv.getRow(), psv.getCol());
			psv.getPolygon().setFill(tmpCell.getCurrentState().getColor());
		}
	}
	
}
