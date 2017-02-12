
package main;

import javafx.geometry.Dimension2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SimulationGUI {
	public static final Dimension2D DEFAULT_SIZE = new Dimension2D(1000, 800);
	public static final Dimension2D SIMULATION_SPACE = new Dimension2D(600, 800);
    private Scene myScene;
    private BorderPane root;
    private SimulationView cellSimulationView;
    private VBox simulationHolder;
    
    public SimulationGUI(String lang){
    	root = new BorderPane();
    	root.setStyle("-fx-background-color : white");
    	
    	simulationHolder = new VBox();
    	
    	cellSimulationView = new SimulationView();
    	
    	StackPane cellSimulationZoomPane = makeSimulation();
    	//test code
//    	cellSimulationDisplay = new SimulationView();
//    	simulationWrapperPane = new VBox();
//    	
//    	simulationWrapperPane.setMinSize(600, 800);
//    	
//    	ScrollPane sp = new ScrollPane();
//    	sp.setMinSize(600, 800);
//    	sp.setContent(cellSimulationDisplay.getCellSimulationGroup());
//    	
//    	simulationWrapperPane.getChildren().add(sp);
    	
//    	root.setRight(simulationWrapperPane);
    	simulationHolder.getChildren().add(cellSimulationZoomPane);

    	root.setRight(simulationHolder);
    	    	
    	myScene = new Scene(root, DEFAULT_SIZE.getWidth(), DEFAULT_SIZE.getHeight());
    }
    private StackPane makeSimulation(){
    	StackPane zoomPane = new StackPane();
    	zoomPane.getChildren().add(cellSimulationView.getCellSimulationGroup());
    	cellSimulationView.getCellSimulationGroup().setScaleX(2);
    	cellSimulationView.getCellSimulationGroup().setScaleY(2);
    	
    	return zoomPane;
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
