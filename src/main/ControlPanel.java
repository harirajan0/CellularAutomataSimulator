package main;

import java.util.Arrays;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import resources.Resources;

public class ControlPanel {
	
	private final ChoiceBox<String> cb;
	private final int BTN_WIDTH  = 70, BTN_HEIGHT = 20;
	
	private HBox buttonsPanel;
	private Button startButton, pauseButton, stepButton, resetButton, loadButton, resumeButton, saveButton;
	private Slider speedSlider;
    private String shapeType;

	public ControlPanel(){
		buttonsPanel = new HBox();
		buttonsPanel.setStyle(Resources.GRAY_PANE_STYLE);
		buttonsPanel.setPadding(new Insets(15, 15, 15, 15));
		buttonsPanel.setSpacing(10);
		
		startButton = makeButton("StartCommand");
		pauseButton = makeButton("PauseCommand");
		stepButton = makeButton("StepCommand");
		resetButton = makeButton("ResetCommand");
		loadButton = makeButton("LoadCommand");
		resumeButton = makeButton("ResumeCommand");
		saveButton = makeButton("SaveCommand");


		cb = new ChoiceBox<String>(FXCollections.observableArrayList(Arrays.asList(Resources.SHAPES)));
		initializeChoicebox();
		
		speedSlider = makeSpeedSlider();
		
		buttonsPanel.setMaxHeight(speedSlider.getHeight());
	}
	
	public HBox getControlPanel(){
		return buttonsPanel;
	}
	
	protected void addToHBox(){
		buttonsPanel.getChildren().addAll(startButton, resumeButton, pauseButton, stepButton, resetButton, loadButton, saveButton, cb, speedSlider);
	}
	
	// setter methods to set actions for buttons; used in the controller
	protected void setStart(EventHandler<ActionEvent> handler){
		startButton.setOnAction(handler);
	}

	
	protected void setPause(EventHandler<ActionEvent> handler){
		pauseButton.setOnAction(handler);
	}
	
	protected void setStep(EventHandler<ActionEvent> handler){
		stepButton.setOnAction(handler);
	}
	
	protected void setReset(EventHandler<ActionEvent> handler){
		resetButton.setOnAction(handler);
	}
	
	protected void setLoad(EventHandler<ActionEvent> handler){
		loadButton.setOnAction(handler);
	}
	
	protected void setResume(EventHandler<ActionEvent> handler){
		resumeButton.setOnAction(handler);
	}
	
	protected void setSave(EventHandler<ActionEvent> handler){
		saveButton.setOnAction(handler);
	}
	
	protected Slider getSlider(){
		return speedSlider;
	}
	
	protected String getShapeType(){
		return shapeType;
	}
	
	protected ChoiceBox<String> getChoiceBox() {
		return cb;
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
    	String label = Resources.getString(property);
    	result.setText(label);
    	result.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
    	return result;
    }
	
    private void initializeChoicebox(){
    	cb.getSelectionModel().selectFirst();
    	shapeType = Resources.SHAPES[cb.getSelectionModel().getSelectedIndex()];
    	cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				shapeType = Resources.SHAPES[newValue.intValue()];
			}
    	});
    }
}