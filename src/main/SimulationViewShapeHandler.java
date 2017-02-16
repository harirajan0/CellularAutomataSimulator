// This is part of my code masterpiece
// Gabriel Chen
package main;

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

public class SimulationViewShapeHandler {
	
	private Group cellSimulationGroup;
	private List<PolygonShapeView> cellDisplay;
	public SimulationViewShapeHandler(Group group, List<PolygonShapeView> display){
		cellSimulationGroup = group;
		cellDisplay = display;
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
	 * Updates an individual cell's state
	 * @param cell Cell to update
	 * @param psv <code>PolygonShapeView</code> of cell to update
	 */
	protected void updateIndividualCellState(Cell cell, PolygonShapeView psv){
		cell.changeStateOnClick();
		psv.getPolygon().setFill(cell.getCurrentState().getColor());
	}
	
	protected void chooseAndAddShape(Model model, String shapeType, int r, int c) {
		Cell cell = model.get(r, c);
		int sideLength = Resources.INIT_WINDOW_SIZE / Math.max(model.getRows(), model.getCols());
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
