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
import javafx.scene.text.TextAlignment;

public class SimulationGUI {

    private Scene myScene;
    private BorderPane root;
    private SimulationView cellSimulationView;
    private ScrollPane simulationHolder;
    private VBox graphSideLayout;
    
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
    
    protected void simViewZoomIn(){
    	cellSimulationView.zoomIn();
    }
    protected void simViewZoomOut(){
    	cellSimulationView.zoomOut();
    }
    protected void simViewZoomReset(){
    	cellSimulationView.zoomReset();
    }
    
    protected void createCP(HBox hbox){
    	root.setTop(hbox);
    }
    
    private void createGraph(VBox vbox){
    	graphSideLayout.getChildren().add(vbox);
    }
    
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
    
    public SimulationView getSimulationView(){
    	return cellSimulationView;
    }
    
    public Scene getScene(){
    	return myScene;
    }
    
    
}