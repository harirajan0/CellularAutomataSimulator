package main;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import resources.Resources;

public class SimulationGUI {

    private Scene myScene;
    private BorderPane root;
    private SimulationView cellSimulationView;
    private ScrollPane simulationHolder;
    
    public SimulationGUI(String lang){
    	simulationHolder = new ScrollPane();
    	simulationHolder.setPrefSize(Resources.SIMULATION_SPACE.getWidth(), Resources.SIMULATION_SPACE.getHeight());

    	cellSimulationView = new SimulationView();
    	simulationHolder.setContent(cellSimulationView.getSimulationStackPane());
    	
    	root = new BorderPane();
    	root.setStyle(Resources.PANE_STYLE);
    	
    	root.setRight(simulationHolder);
    	    	
    	myScene = new Scene(root, Resources.DEFAULT_SIZE.getWidth(), Resources.DEFAULT_SIZE.getHeight());
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