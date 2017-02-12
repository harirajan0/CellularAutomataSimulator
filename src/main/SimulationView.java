package main;

import java.util.ArrayList;
import java.util.List;

import cells.Cell;
import cellshapeviews.HexagonShapeView;
import cellshapeviews.PolygonShapeView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import model.Model;

public class SimulationView {
		
	private Pane cellSimulationPane;
	private List<PolygonShapeView> cellDisplay;
	
	public SimulationView(){
		cellSimulationPane = new Pane();
		cellSimulationPane.setMaxSize(ApplicationStartup.WINDOW_SIZE, ApplicationStartup.WINDOW_SIZE);	
		cellDisplay = new ArrayList<>();
	}
	
	public Pane getCellSimulationPane(){
		return cellSimulationPane;
	}
	

	/* Takes in the model and display it in the GUI.
	 * @param model The model to display
	 */
	
	public void displayGrid(Model model) {
		int sideLength = ApplicationStartup.WINDOW_SIZE / Math.max(model.getRows(), model.getCols());
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
