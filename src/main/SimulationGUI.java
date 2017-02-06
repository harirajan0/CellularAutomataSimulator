package main;

import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class SimulationGUI {
	public static final Dimension2D DEFAULT_SIZE = new Dimension2D(1000, 800);
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    private Scene myScene;
    private BorderPane root;
    private SimulationView cellSimulationDisplay;
    private SimulationView cellSimulationDisplay2;
    
    public SimulationGUI(String lang){
    	root = new BorderPane();
    	root.setStyle("-fx-background-color : black");
    	
    	cellSimulationDisplay = new SimulationView();
    	cellSimulationDisplay2 = new SimulationView();
    	
    	cellSimulationDisplay.getCellSimulationPane().setStyle("-fx-background-color: cyan;");
    	
//    	root.setCenter(cellSimulationDisplay.getCellSimulationPane());
    	
    	cellSimulationDisplay2.getCellSimulationPane().setStyle("-fx-background-color: black;");
//    	
//    	root.getChildren().addAll(cellSimulationDisplay2.getCellSimulationPane(),
//    			cellSimulationDisplay.getCellSimulationPane());
//    	root.getChildren().add(cellSimulationDisplay.getCellSimulationPane());
//    	root.getChildren().add(cellSimulationDisplay2.getCellSimulationPane());
    	
//    	root.setCenter(cellSimulationDisplay.getCellSimulationPane());
    	Pane pane1 = new Pane();
    	pane1.setStyle("-fx-background-color: blue");
        Button btn = new Button();
        btn.setText("yo");
        pane1.setMinWidth(20);
//        pane1.getChildren().add(btn);
        
    	Pane pane2 = new Pane();
    	pane1.setStyle("-fx-background-color: white");

    	Pane pane3 = new Pane();
    	pane1.setStyle("-fx-background-color: red");

    	Pane pane4 = new Pane();
    	pane1.setStyle("-fx-background-color: green");

    	root.setCenter(cellSimulationDisplay.getCellSimulationPane());
    	root.setRight(pane1);
    	root.setBottom(pane2);
    	root.setTop(pane3);
    	root.setLeft(pane4);
    	
//    	root.setRight(cellSimulationDisplay2.getCellSimulationPane());
    	
    	myScene = new Scene(root, DEFAULT_SIZE.getWidth(), DEFAULT_SIZE.getHeight());
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
