package main;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * A control panel containing zoom functionality
 */
public class SimulationControlPanel {
	private HBox buttonsPanel;
	private Button zoomInButton, zoomOutButton, zoomResetButton;
    private ResourceBundle myResources;
	private final int BTN_HEIGHT = 20;

	/**
	 * Creates the <code>SimulationControlPanel</code>
	 * @param resources Resources file to use
	 */
	public SimulationControlPanel(ResourceBundle resources){
		myResources = resources;
		buttonsPanel = new HBox();
		buttonsPanel.setStyle("-fx-background-color: gray");
		buttonsPanel.setPadding(new Insets(15, 15, 15, 15));
		buttonsPanel.setSpacing(10);

		
		zoomInButton = makeButton("ZoomIn");
		zoomOutButton = makeButton("ZoomOut");
		zoomResetButton = makeButton("ZoomReset");
	}
	
	/**
	 * Creates a button that follows the string <code>property</code>
	 * @param property The property for the button to follow
	 * @return The button
	 */
    private Button makeButton(String property) {
    	Button result = new Button();
    	String label = myResources.getString(property);
    	result.setText(label);
    	result.setPrefHeight(BTN_HEIGHT);
    	return result;
    }
    
    /**
     * Adds the buttons to a <code>HBox</code>
     */
    public void addToHBox(){
    	buttonsPanel.getChildren().addAll(zoomInButton, zoomOutButton, zoomResetButton);
    }
    
    /**
     * Sets the action for the zoom in button
     * @param handler Click event
     */
	public void setZoomIn(EventHandler<ActionEvent> handler){
		zoomInButton.setOnAction(handler);
	}
	
	/**
     * Sets the action for the zoom out button
     * @param handler Click event
     */
	public void setZoomOut(EventHandler<ActionEvent> handler){
		zoomOutButton.setOnAction(handler);
	}
	
	/**
     * Sets the action for the reset zoom button
     * @param handler Click event
     */
	public void setZoomReset(EventHandler<ActionEvent> handler){
		zoomResetButton.setOnAction(handler);
	}
	
	/**
     * Gets the <code>HBox</code> containing all of the buttons
     */
	public HBox getSimulationControlPanel(){
		return buttonsPanel;
	}
	
}
