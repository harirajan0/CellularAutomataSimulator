package main;

import java.util.ResourceBundle;

import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class SimulationGUI {
	public static final Dimension2D DEFAULT_SIZE = new Dimension2D(1000, 800);
    private Scene myScene;
    private BorderPane root;
    private SimulationView cellSimulationDisplay;
    private SimulationView cellSimulationDisplay2;
    
    public SimulationGUI(String lang){
    	root = new BorderPane();
    	root.setStyle("-fx-background-color : white");
    	
    	cellSimulationDisplay = new SimulationView();

    	root.setCenter(cellSimulationDisplay.getCellSimulationPane());
    	
//    	root.setRight(cellSimulationDisplay2.getCellSimulationPane());
    	
    	myScene = new Scene(root, DEFAULT_SIZE.getWidth(), DEFAULT_SIZE.getHeight());
    }
    
    public void createButtons(Controller c, ResourceBundle r){
    	ControlPanel cp = new ControlPanel(c, r);
    	root.setTop(cp.getControlPanel());
    }
    
    public SimulationView getSimulationView(){
    	return cellSimulationDisplay;
    }
    
    public Scene getScene(){
    	return myScene;
    }
    
    private void enableButtons(){
    	
    }
    
    private void disableButtons(){
    	
    }
}
