package main;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControlPanel {
	private HBox buttonsPanel;
    private ResourceBundle myResources;

	public ControlPanel(Controller c, ResourceBundle resources){
		myResources = resources;

		buttonsPanel = new HBox();
		buttonsPanel.setStyle("-fx-background-color: gray");
		Button startButton = makeButton("StartCommand", e -> c.start());
		Button pauseButton = makeButton("PauseCommand", e -> c.pause());
		Button stepButton = makeButton("StepCommand", e-> c.step());
		Button resetButton = makeButton("ResetCommand", e -> c.reset());
		Button loadButton = makeButton("LoadCommand", e -> c.load());

		Slider speedSlider = makeSpeedSlider(c);
		
		buttonsPanel.setMaxHeight(speedSlider.getHeight());

		
		buttonsPanel.getChildren().addAll(startButton, pauseButton, stepButton, resetButton, loadButton, speedSlider);
		
	}
	
	public HBox getControlPanel(){
		return buttonsPanel;
	}
	
	private Slider makeSpeedSlider(Controller c) {
		Slider slider = new Slider(0, 1000, 50);
		slider.setOrientation(Orientation.HORIZONTAL);
		slider.setOnDragDropped(e -> c.changeSpeed());
		slider.setMajorTickUnit(100);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setSnapToTicks(true);
		slider.setPrefHeight(250);
		return slider;
	}
    private Button makeButton(String property, EventHandler<ActionEvent> handler) {
    	Button result = new Button();
    	System.out.println(myResources.getString(property));
    	String label = myResources.getString(property);
    	
    	result.setText(label);
    	result.setOnAction(handler);
    	result.setPrefSize(70, 20);
    	return result;
    }
	
}
