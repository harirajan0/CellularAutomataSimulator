package main;

import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The GUI for interacting with and viewing the simulation
 */
public class SimulationGUI {
	public static final Dimension2D DEFAULT_SIZE = new Dimension2D(1000, 800);
	public static final Dimension2D SIMULATION_SPACE = new Dimension2D(620, 800);

    private Scene myScene;
    private BorderPane root;
    private SimulationView cellSimulationView;
    private ScrollPane simulationHolder;
    private VBox simulationLayout;
    
    /**
     * Creates a <code>SimulationGUI</code> with the desired language
     * @param lang Desired language
     */
    public SimulationGUI(String lang){
    	simulationHolder = new ScrollPane();
    	simulationHolder.setPrefSize(SIMULATION_SPACE.getWidth(), SIMULATION_SPACE.getHeight());

    	cellSimulationView = new SimulationView();
    	simulationHolder.setContent(cellSimulationView.getSimulationStackPane());
    	
    	simulationLayout = new VBox();
    	simulationLayout.getChildren().add(simulationHolder);
    	root = new BorderPane();
    	root.setStyle("-fx-background-color : white");
    	
    	root.setRight(simulationLayout);
    	    	
    	myScene = new Scene(root, DEFAULT_SIZE.getWidth(), DEFAULT_SIZE.getHeight());
    }
    
    /**
     * Zooms in on the simulation view
     */
    public void simViewZoomIn(){
    	cellSimulationView.zoomIn();
    }
    
    /**
     * Zooms out on the simulation view
     */
    public void simViewZoomOut(){
    	cellSimulationView.zoomOut();
    }
    
    /**
     * Resets the zoom on the simulation view
     */
    public void simViewZoomReset(){
    	cellSimulationView.zoomReset();
    }
    
    /**
     * Adds the <code>ControlPanel</code> to the GUI
     * @param hbox <code>HBox</code> containing all buttons and sliders
     */
    public void createCP(HBox hbox){
    	root.setTop(hbox);
    }
    
    /**
     * Adds the graph to the GUI
     * @param vbox <code>VBox</code> containing the graph
     */
    public void createGraph(VBox vbox){
    	root.setLeft(vbox);
    }
    
    /**
     * Adds the <code>SimulationControlPanel</code> to the GUI
     * @param hbox <code>HBox</code> containing the zoom controls
     */
    public void createBP(HBox hbox){
    	simulationLayout.getChildren().add(hbox);
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