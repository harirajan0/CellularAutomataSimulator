package main;

import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

public class ControlPanel {
	
	private final String[] SHAPES = new String[]{"Square", "Triangle", "Hexagon"};
	private final ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
			"Square", "Triangle", "Hexagon"));
	private final int BTN_WIDTH  = 70, BTN_HEIGHT = 20;
	
	private HBox buttonsPanel;
	private Button startButton, pauseButton, stepButton, resetButton, loadButton, resumeButton, saveButton, zoomInButton, zoomOutButton, zoomResetButton;
	private Slider speedSlider;
    private ResourceBundle myResources;
    private String shapeType;

	public ControlPanel(ResourceBundle resources){
		myResources = resources;

		buttonsPanel = new HBox();
		buttonsPanel.setStyle("-fx-background-color: gray");
		buttonsPanel.setPadding(new Insets(15, 15, 15, 15));
		buttonsPanel.setSpacing(10);
		
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

		initializeChoicebox();
		
		speedSlider = makeSpeedSlider();
		
		buttonsPanel.setMaxHeight(speedSlider.getHeight());
	}
	
	public HBox getControlPanel(){
		return buttonsPanel;
	}
	
	public void addToHBox(){
		buttonsPanel.getChildren().addAll(startButton, resumeButton, pauseButton, stepButton, resetButton, loadButton, saveButton, cb, speedSlider, zoomInButton,
				zoomOutButton, zoomResetButton);
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
	
	public String getShapeType(){
		return shapeType;
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
	
    private void initializeChoicebox(){
    	cb.getSelectionModel().selectFirst();
    	shapeType = SHAPES[cb.getSelectionModel().getSelectedIndex()];
    	cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				shapeType = SHAPES[newValue.intValue()];
			}
    	});
    }
}
