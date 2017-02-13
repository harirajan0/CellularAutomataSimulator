package main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import resources.Resources;

public class SimulationControlPanel {
	private HBox buttonsPanel;
	private Button zoomInButton, zoomOutButton, zoomResetButton;
	private final int BTN_HEIGHT = 20;

	
	public SimulationControlPanel(){
		buttonsPanel = new HBox();
		buttonsPanel.setStyle(Resources.GRAY_PANE_STYLE);
		buttonsPanel.setPadding(new Insets(15, 15, 15, 15));
		buttonsPanel.setSpacing(10);
		buttonsPanel.setAlignment(Pos.CENTER_RIGHT);
		
		zoomInButton = makeButton("ZoomIn");
		zoomOutButton = makeButton("ZoomOut");
		zoomResetButton = makeButton("ZoomReset");
	}
    private Button makeButton(String property) {
    	Button result = new Button();
    	String label = Resources.getString(property);
    	result.setText(label);
    	result.setPrefHeight(BTN_HEIGHT);
    	return result;
    }
    
    protected void addToHBox(){
    	buttonsPanel.getChildren().addAll(zoomInButton, zoomOutButton, zoomResetButton);
    }
    protected void setZoomIn(EventHandler<ActionEvent> handler){
		zoomInButton.setOnAction(handler);
	}
    protected void setZoomOut(EventHandler<ActionEvent> handler){
		zoomOutButton.setOnAction(handler);
	}
    protected void setZoomReset(EventHandler<ActionEvent> handler){
		zoomResetButton.setOnAction(handler);
	}
    protected HBox getSimulationControlPanel(){
		return buttonsPanel;
	}
	
}