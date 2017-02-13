package main;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
    
    public void addToHBox(){
    	buttonsPanel.getChildren().addAll(zoomInButton, zoomOutButton, zoomResetButton);
    }
	public void setZoomIn(EventHandler<ActionEvent> handler){
		zoomInButton.setOnAction(handler);
	}
	public void setZoomOut(EventHandler<ActionEvent> handler){
		zoomOutButton.setOnAction(handler);
	}
	public void setZoomReset(EventHandler<ActionEvent> handler){
		zoomResetButton.setOnAction(handler);
	}
	public HBox getSimulationControlPanel(){
		return buttonsPanel;
	}
	
}
