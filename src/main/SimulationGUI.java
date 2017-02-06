package main;

import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class SimulationGUI {
	public static final Dimension2D DEFAULT_SIZE = new Dimension2D(800, 600);
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    private Scene myScene;
    private BorderPane root;
    private SimulationView cellSimulationDiplay;
    
    public SimulationGUI(String lang){
    	root = new BorderPane();
    	
    	cellSimulationDiplay = new SimulationView();
    	
    	root.setCenter(cellSimulationDiplay.getCellSimulationPane());
    	
    	myScene = new Scene(root, DEFAULT_SIZE.getWidth(), DEFAULT_SIZE.getHeight());
    }
    
    public Scene getScene(){
    	return myScene;
    }
}
