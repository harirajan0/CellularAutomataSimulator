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
import resources.Resources;

/**
 * View containing the display of all of the cells
 */
public class SimulationView {
	
	private List<PolygonShapeView> cellDisplay;
	private Group cellSimulationGroup;
	private StackPane cellSimStackPane;
	private double currScale;
	
	/**
	 * Create the <code>SimulationView</code>
	 */
	public SimulationView(){
		cellSimulationGroup = new Group();
		cellSimStackPane = makeSimulationStackPane();
		cellDisplay = new ArrayList<>();
		setMinSizeToDefault();
		currScale = 1;
	}
	
	/**
	 * Sets the scaling on the dimensions of the view
	 */
	private void setGroupScale(){
		cellSimulationGroup.setScaleX(currScale);
		cellSimulationGroup.setScaleY(currScale);
	}
	
	/**
	 * Sets the minimum size of the view
	 * @param scaleFactor ScaleFactor of minimum zoom
	 */
	private void setStackPaneMinSize(double scaleFactor){
		cellSimStackPane.setMinWidth(cellSimStackPane.getMinWidth() * scaleFactor);
		cellSimStackPane.setMinHeight(cellSimStackPane.getMinHeight() * scaleFactor);
	}
	

	/**
	 * Doubles the simulation view size
	 */

	protected void zoomIn(){
		currScale = currScale * 2;
		setGroupScale();
		setStackPaneMinSize(2);
	}

	
	/**
	 * Halves the simulation view size
	 */

	protected void zoomOut(){
		currScale = currScale * .5;
		setGroupScale();
		setStackPaneMinSize(.5);
	}

	
	/**
	 * Resets simulation view size
	 */

	protected void zoomReset(){

		currScale = 1;
		setGroupScale();
		setMinSizeToDefault();
	}
	
	/**
	 * Sets the size back to default
	 */
	private void setMinSizeToDefault(){
		cellSimStackPane.setMinWidth(Resources.INIT_WINDOW_SIZE);
		cellSimStackPane.setMinHeight(Resources.INIT_WINDOW_SIZE);
	}
	
	/**
	 * Gets the <code>Group</code> for the simulation view
	 * @return The <code>Group</code> for the simulation view
	 */
	public Group getCellSimulationGroup(){
		return cellSimulationGroup;
	}
	
	/**
	 * Gets the <code>StackPane</code> for the simulation
	 * @return The <code>StackPane</code> for the simulation
	 */
	public StackPane getSimulationStackPane(){
		return cellSimStackPane;
	}
	
	/**
	 * Makes the <code>StackPane</code> for the simulation view
	 * @return The created <code>StackPane</code>
	 */
    private StackPane makeSimulationStackPane(){
    	StackPane zoomPane = new StackPane();
    	zoomPane.getChildren().add(cellSimulationGroup);
    	return zoomPane;
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