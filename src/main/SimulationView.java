//Gabriel Chen

// This is part of my code masterpiece
// I refactored it this way so that the simulation view just handles the displaying and the stuff involving the pane and the group display.
// Meanwile the simulationviewshapeHandler will handle the making of the polygon.
// Gabriel Chen
package main;
import java.util.ArrayList;
import java.util.List;
import cells.Cell;
import cellshapeviews.PolygonShapeView;
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
	private SimulationViewShapeHandler shapeHandler;
	
	/**
	 * Create the <code>SimulationView</code>
	 */
	public SimulationView(){
		cellSimulationGroup = new Group();
		cellSimStackPane = makeSimulationStackPane();
		cellDisplay = new ArrayList<>();
		setMinSizeToDefault();
		currScale = 1;
		shapeHandler = new SimulationViewShapeHandler(cellSimulationGroup, cellDisplay);
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
		cellSimulationGroup.getChildren().clear();
		for(int r = 0; r < model.getRows(); r++){
			for(int c = 0; c < model.getCols(); c++){				
				shapeHandler.chooseAndAddShape(model, shapeType, r, c);
			}
		}
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
	
	
}