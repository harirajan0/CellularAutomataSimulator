package main;

import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * A class used to display the different View objects and hold buttons.
 * @author Bihan Zhuang
 *
 */
public class GUI {
	
	public static final Dimension2D DEFAULT_SIZE = new Dimension2D(800, 600);
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    //public static final String STYLESHEET = "default.css";
    //public static final String BLANK = " ";
    
    private SimulationView myCellView;
    private Controller myController;
    // scene, needed to report back to Application
    private Scene myScene;
    // navigation
    private TextField mySimulation, myNumGeneration;
    private Button myStartButton, myPauseButton, myStepButton, myResetButton, myLoadButton;
    private Slider mySlider;
    // resources
    private ResourceBundle myResources;
    
    private String filename;
    
    
    /**
     * GUI constructor.
     */
	public GUI(String language) {
		// Might need to change constructor based on View implementation (because, for example, a View to display the Cell Grid 
		// will be different from the View to display other things, like Number of generation, etc.)
		// Also, if more than one View is needed in the future, initialize them in the constructor as well.
		//
		// This is just placeholder for CellView
		myCellView = new View();
		
		// Create a Controller
		myController = new Controller(myCellView);
		
		// set resource path
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		
		BorderPane root = new BorderPane();
		// Center of GUI displays the cell grid
		root.setCenter(makeCellDisplay());
        // Bottom of GUI displays the control panel that consists of all the buttons
        root.setRight(makeControlPanel());
        // control the navigation
        enableButtons();
        // create scene to hold UI
        myScene = new Scene(root, DEFAULT_SIZE.getWidth(), DEFAULT_SIZE.getHeight());
        //myScene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
	}
	
	/**
     * Returns scene for this view so it can be added to stage.
     */
    public Scene getScene () {
        return myScene;
    }
    
    /** !!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * Display given message as an error in the GUI. 
     * (??????? what type of error can be thrown from XML loading...)
     */
    public void showError (String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(myResources.getString("ErrorTitle"));
        alert.setContentText(message);
        alert.showAndWait();
    }
	
	/**
	 * Enable buttons if certain conditions are satisfied.
	 */
	private void enableButtons() {
		
	}
	
	/**
	 * Makes a button using either an image or a label
	 * @param property
	 * @param handler
	 * @return
	 */
    private Button makeButton(String property, EventHandler<ActionEvent> handler) {
    	Button result = new Button();
    	String label = myResources.getString(property);
    	result.setText(label);
    	result.setOnAction(handler);
    	result.setPrefSize(70, 20);
    	return result;
    }

	/**
	 * Initialize all the buttons and sliders.
	 * @return
	 */
	private Node makeControlPanel() {
		VBox result = new VBox(30);
		result.setPadding(new Insets(15, 12, 15, 12));
		result.setStyle("-fx-background-color: #8FBC8F;");
		result.setAlignment(Pos.CENTER);
		// create buttons and slider to change speed
		myStartButton = makeButton("StartCommand", e -> myController.start());
		myPauseButton = makeButton("PauseCommand", e -> myController.pause());
		myStepButton = makeButton("StepCommand", e -> myController.step());
		myResetButton = makeButton("ResetCommand", e -> myController.reset());
		//Don'r actually need a filename to call Loader. see class example to make the pop up window instead
		// will change this later.
		myLoadButton = makeButton("LoadCommand", e -> myController.load(filename));
		makeSlider();
		
		result.getChildren().addAll(myStartButton, myPauseButton, myStepButton, myResetButton, myLoadButton);
		result.getChildren().add(mySlider);
		return result;
	}
	
	/**
	 * Create the slider (unit is Delay in ms)
	 */
	private void makeSlider(){
		mySlider = new Slider(0, 1000, 50);
		mySlider.setOnDragDropped(e -> myController.changeSpeed());
		mySlider.setMajorTickUnit(100);
		mySlider.setShowTickLabels(true);
		mySlider.setShowTickMarks(true);
		mySlider.setSnapToTicks(true);
		mySlider.setPrefHeight(250);
		mySlider.setOrientation(Orientation.VERTICAL);
	}
	
	/**
	 * Initialize the Cell Grid from a CellView.
	 * @return
	 */
	private Node makeCellDisplay() {
		Pane result = new Pane();
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! should change once Grid class is up
		result.getChildren().add(myCellView.displayGrid(myController.getGrid()));
		return result;
	}
	
	

	/**
	 * Inner class to deal with link-clicks and mouse-overs
	 */
    //   http://blogs.kiyut.com/tonny/2013/07/30/javafx-webview-addhyperlinklistener/
    private class LinkListener implements ChangeListener<State> {

        /*public static final String HTML_LINK = "href";
        public static final String EVENT_CLICK = "click";
        public static final String EVENT_MOUSEOVER = "mouseover";
        public static final String EVENT_MOUSEOUT = "mouseout";

        @Override
        public void changed (ObservableValue<? extends State> ov, State oldState, State newState) {
            if (newState == Worker.State.SUCCEEDED) {
                EventListener listener = event -> {
                    final String href = ((Element) event.getTarget()).getAttribute(HTML_LINK);
                    if (href != null) {
                        String domEventType = event.getType();
                        if (domEventType.equals(EVENT_CLICK)) {
                            showPage(href);
                        }
                        else if (domEventType.equals(EVENT_MOUSEOVER)) {
                            showStatus(href);
                        }
                        else if (domEventType.equals(EVENT_MOUSEOUT)) {
                            showStatus(BLANK);
                        }
                    }
                };
                Document doc = myPage.getEngine().getDocument();
                NodeList nodes = doc.getElementsByTagName("a");
                for (int i = 0; i < nodes.getLength(); i++) {
                    EventTarget node = (EventTarget)nodes.item(i);
                    node.addEventListener(EVENT_CLICK, listener, false);
                    node.addEventListener(EVENT_MOUSEOVER, listener, false);
                    node.addEventListener(EVENT_MOUSEOUT, listener, false);
                }
            }
        }*/
    	
    	@Override
		public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
			
		}
    };
}
