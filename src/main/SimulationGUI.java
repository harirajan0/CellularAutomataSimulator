package main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import resources.Resources;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The GUI for interacting with and viewing the simulation
 */
public class SimulationGUI {

    private Scene myScene;
    private BorderPane root;
    private SimulationView cellSimulationView;
    private ScrollPane simulationHolder;
    private VBox graphSideLayout;
    
    /**
     * Creates a <code>SimulationGUI</code> with the desired language
     * @param lang Desired language
     */
    public SimulationGUI(){
    	simulationHolder = new ScrollPane();
    	simulationHolder.setPrefSize(Resources.SIMULATION_SPACE.getWidth(), Resources.SIMULATION_SPACE.getHeight());

    	cellSimulationView = new SimulationView();
    	simulationHolder.setContent(cellSimulationView.getSimulationStackPane());
    	
    	root = new BorderPane();
    	root.setStyle(Resources.WHITE_PANE_STYLE);
    	
    	root.setRight(simulationHolder);
    	
    	graphSideLayout = new VBox();
    	graphSideLayout.setAlignment(Pos.CENTER);
    	
    	root.setLeft(graphSideLayout);
    	    	
    	myScene = new Scene(root, Resources.DEFAULT_SIZE.getWidth(), Resources.DEFAULT_SIZE.getHeight());
    }
    
	/**
	 * Zooms in on the simulation view
	 */
    protected void simViewZoomIn(){
    	cellSimulationView.zoomIn();
    }
	/**
	 * Zooms out on the simulation view
	 */
    protected void simViewZoomOut(){
    	cellSimulationView.zoomOut();
    }
    /**
     * Resets the zoom on the simulation view
     */
    protected void simViewZoomReset(){
    	cellSimulationView.zoomReset();
    }
    /**
     * Adds the <code>ControlPanel</code> to the GUI
     * @param hbox <code>HBox</code> containing all buttons and sliders
     */
    protected void createCP(HBox hbox){
    	root.setTop(hbox);
    }
    
    /**
     * Adds the graph to the GUI
     * @param vbox <code>VBox</code> containing the graph
     */
    private void createGraph(VBox vbox){
    	graphSideLayout.getChildren().add(vbox);
    }
    /**
     * Adds the <code>SimulationControlPanel</code> to the GUI
     * @param hbox <code>HBox</code> containing the zoom controls
     */
    protected void createBP(HBox hbox){
    	root.setBottom(hbox);
    }
    
    protected void createGraphSidePanel(String simulationName, VBox graph) {
    	graphSideLayout.getChildren().clear();
    	Text simulationTitle = new Text(simulationName);
    	simulationTitle.setFont(new Font(20));
    	graphSideLayout.getChildren().add(simulationTitle);
    	createGraph(graph);
    }
    
    /**
     * Returns the simulation view
     * @return The simulation view
     */
    public SimulationView getSimulationView(){
    	return cellSimulationView;
    }
    
    /**
     * Returns the scene containing the simulation
     * @return
     */
    public Scene getScene(){
    	return myScene;
    }
}