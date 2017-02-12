package main;

import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SimulationGUI {
	public static final Dimension2D DEFAULT_SIZE = new Dimension2D(1000, 800);
	public static final Dimension2D SIMULATION_SPACE = new Dimension2D(620, 800);

    private Scene myScene;
    private BorderPane root;
    private BorderPane simulationCenterer;
    private SimulationView cellSimulationView;
    private ScrollPane simulationHolder;
    
    public SimulationGUI(String lang){
    	simulationHolder = new ScrollPane();
    	simulationHolder.setPrefSize(SIMULATION_SPACE.getWidth(), SIMULATION_SPACE.getHeight());

    	cellSimulationView = new SimulationView();
    	simulationHolder.setContent(cellSimulationView.getSimulationStackPane());
    	
    	root = new BorderPane();
    	root.setStyle("-fx-background-color : white");
    	
    	root.setRight(simulationHolder);
    	    	
    	myScene = new Scene(root, DEFAULT_SIZE.getWidth(), DEFAULT_SIZE.getHeight());
    }
    
    public void simViewZoomIn(){
    	cellSimulationView.zoomIn();
    }
    public void simViewZoomOut(){
    	cellSimulationView.zoomOut();


    }
    public void simViewZoomReset(){
    	cellSimulationView.zoomReset();


    }
    
    
    public void createCP(HBox hbox){
    	root.setTop(hbox);
    }
    
    public SimulationView getSimulationView(){
    	return cellSimulationView;
    }
    
    public Scene getScene(){
    	return myScene;
    }
    
    
}