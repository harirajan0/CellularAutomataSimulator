package main;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class ControlPanel {
	
	private final int BTN_WIDTH  = 70, BTN_HEIGHT = 20;
	
	private HBox buttonsPanel;
	private Button startButton, pauseButton, stepButton, resetButton, loadButton, resumeButton, saveButton, zoomInButton, zoomOutButton, zoomResetButton;
	private Slider speedSlider;
    private ResourceBundle myResources;

	public ControlPanel(ResourceBundle resources){
		myResources = resources;

		buttonsPanel = new HBox();
		buttonsPanel.setStyle("-fx-background-color: gray");
		
		// make buttons' visual 
		startButton = makeButton("StartCommand");
		pauseButton = makeButton("PauseCommand");
		stepButton = makeButton("StepCommand");
		resetButton = makeButton("ResetCommand");
		loadButton = makeButton("LoadCommand");
		resumeButton = makeButton("ResumeCommand");
		saveButton = makeButton("SaveCommand");
		zoomInButton = makeButton("ZoomIn");
		zoomOutButton = makeButton("ZoomOut");
		zoomResetButton = makeButton("ZoomReset");

		speedSlider = makeSpeedSlider();
		
		buttonsPanel.setMaxHeight(speedSlider.getHeight());
	}
	
	public HBox getControlPanel(){
		return buttonsPanel;
	}
	
	public void addToHBox(){
		buttonsPanel.getChildren().addAll(startButton, resumeButton, pauseButton, stepButton, resetButton, 
				loadButton, saveButton, speedSlider, zoomInButton, zoomOutButton, zoomResetButton);
	}
	
	// setter methods to set actions for buttons; used in the controller
	public void setStart(EventHandler<ActionEvent> handler){
		startButton.setOnAction(handler);
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
	
	public void setPause(EventHandler<ActionEvent> handler){
		pauseButton.setOnAction(handler);
	}
	
	public void setStep(EventHandler<ActionEvent> handler){
		stepButton.setOnAction(handler);
	}
	
	public void setReset(EventHandler<ActionEvent> handler){
		resetButton.setOnAction(handler);
	}
	
	public void setLoad(EventHandler<ActionEvent> handler){
		loadButton.setOnAction(handler);
	}
	
	public void setResume(EventHandler<ActionEvent> handler){
		resumeButton.setOnAction(handler);
	}
	
	public void setSave(EventHandler<ActionEvent> handler){
		resumeButton.setOnAction(handler);
	}
	
	public Slider getSlider(){
		return speedSlider;
	}
	
	private Slider makeSpeedSlider() {
		Slider slider = new Slider(0.1, 5, 1);
		slider.setOrientation(Orientation.HORIZONTAL);
		slider.setMajorTickUnit(0.5);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setPrefWidth(300);
		return slider;
	}
	
    private Button makeButton(String property) {
    	Button result = new Button();
    	String label = myResources.getString(property);
    	result.setText(label);
    	result.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
    	return result;
    }
	
}
