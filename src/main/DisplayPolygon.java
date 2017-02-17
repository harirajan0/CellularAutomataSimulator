package main;

import java.util.ArrayList;
import java.util.List;

import cells.Cell;
import cellshapeviews.HexagonShapeView;
import cellshapeviews.PolygonShapeView;
import cellshapeviews.SquareShapeView;
import cellshapeviews.TriangleShapeView;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import model.Model;
import resources.Resources;

public class DisplayPolygon {
	
	private List<PolygonShapeView> cellDisplay;
	private Group cellSimulationGroup;

	public DisplayPolygon() {
		cellSimulationGroup = new Group();
		cellDisplay = new ArrayList<>();
	}
	
	/** 
     * Takes in the model and displays it in the GUI.
	 * @param model The model to display
	 * @param shapeType The type of shape to use
	 */
	
    protected void displayGrid(Model model, String shapeType) {
		int sideLength = Resources.INIT_WINDOW_SIZE / Math.max(model.getRows(), model.getCols());
		cellSimulationGroup.getChildren().clear();
		for(int r = 0; r < model.getRows(); r++){
			for(int c = 0; c < model.getCols(); c++){
				Cell cell = model.get(r, c);
				PolygonShapeView psv;
				if (shapeType == Resources.TRIANGLE){
					psv = new TriangleShapeView(r, c, sideLength);
					setupPolygon(psv, cell);
				} else if (shapeType == Resources.SQUARE){
					psv = new SquareShapeView(r, c, sideLength);
					setupPolygon(psv, cell);
				} else if (shapeType == Resources.HEXAGON){
					psv = new HexagonShapeView(r, c, sideLength);
					setupPolygon(psv, cell);
				}
			}
		}
	}
	
	/**
	 * Sets up the proper shapes for the cells
	 * @param psv <code>PolygonShapeView</code> of the desired shape
	 * @param cell Cell to set for <code>psv</code>
	 */
	private void setupPolygon(PolygonShapeView psv, Cell cell){
		Polygon polygon = psv.getPolygon();
		polygon.setOnMouseClicked(e -> updateIndividualCellState(cell, psv));
		polygon.setFill(cell.getCurrentState().getColor());
		polygon.setStroke(Color.BLACK);
		cellSimulationGroup.getChildren().add(polygon);
		cellDisplay.add(psv);
	}
	
	/**
	 * Update view for each cell.
	 * @param model The data model to use
	 */
	protected void updateGrid(Model model){
		for (PolygonShapeView psv : cellDisplay){
			Cell tmpCell = model.get(psv.getRow(), psv.getCol());
			psv.getPolygon().setFill(tmpCell.getCurrentState().getColor());
		}
	}
	

	/**
	 * Updates an individual cell's state
	 * @param cell Cell to update
	 * @param psv <code>PolygonShapeView</code> of cell to update
	 */
	protected void updateIndividualCellState(Cell cell, PolygonShapeView psv){
		cell.changeStateOnClick();
		psv.getPolygon().setFill(cell.getCurrentState().getColor());
	}
	

}
