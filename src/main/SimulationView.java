package main;
import java.util.ArrayList;
import java.util.List;
import cells.Cell;
import cellshapeviews.HexagonShapeView;
import cellshapeviews.PolygonShapeView;
import cellshapeviews.SquareShapeView;
import cellshapeviews.TriangleShapeView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import model.Model;
public class SimulationView {
	
	private static final String TRIANGLE = "Triangle";
	private static final String SQUARE = "Square";
	private static final String HEXAGON = "Hexagon";
	
	private List<PolygonShapeView> cellDisplay;
	private Group cellSimulationGroup;
	private StackPane cellSimStackPane;
	private double currScale;
	
	public SimulationView(){
		cellSimulationGroup = new Group();
		cellSimStackPane = makeSimulationStackPane();
		cellDisplay = new ArrayList<>();
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
	
	public void displayGrid(Model model, String shapeType) {
		int sideLength = Controller.INIT_WINDOW_SIZE / Math.max(model.getRows(), model.getCols());
		cellSimulationGroup.getChildren().clear();
		for(int r = 0; r < model.getRows(); r++){
			for(int c = 0; c < model.getCols(); c++){
				Cell cell = model.get(r, c);
				PolygonShapeView psv;
				if (shapeType == TRIANGLE){
					psv = new TriangleShapeView(r, c, sideLength);
					setupPolygon(psv, cell);
				} else if (shapeType == SQUARE){
					psv = new SquareShapeView(r, c, sideLength);
					setupPolygon(psv, cell);
				} else if (shapeType == HEXAGON){
					psv = new HexagonShapeView(r, c, sideLength);
					setupPolygon(psv, cell);
				}
			}
		}
	}
	
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
	 * @param model
	 */
	public void updateGrid(Model model){
		for (PolygonShapeView psv : cellDisplay){
			Cell tmpCell = model.get(psv.getRow(), psv.getCol());
			psv.getPolygon().setFill(tmpCell.getCurrentState().getColor());
		}
	}
	
	public void updateIndividualCellState(Cell cell, PolygonShapeView psv){
		cell.changeStateOnClick();
		psv.getPolygon().setFill(cell.getCurrentState().getColor());
	}
	
}