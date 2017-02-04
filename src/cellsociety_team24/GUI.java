package cellsociety_team24;

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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * A class used to display the different View objects and hold buttons.
 * @author Bihan Zhuang
 *
 */
public class GUI {
	
	public static final Dimension2D DEFAULT_SIZE = new Dimension2D(800, 600);
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    //public static final String STYLESHEET = "default.css";
    public static final String BLANK = " ";
    
    private View myView1;
    private Controller myController;
    // scene, needed to report back to Application
    private Scene myScene;
    // information area (????)
    private Label myStatus;
    // navigation
    private TextField mySimulation, myNumGeneration;
    private Button myStartButton, myPauseButton, myStepButton, myResetButton, myLoadButton;
    private Slider mySlider;
    // resources
    private ResourceBundle myResources;
    
    
    /**
     * GUI constructor.
     */
	public GUI() {
		// Might need to change constructor based on View implementation (because, for example, a View to display the Cell Grid 
		// will be different from the View to display other things, like Number of generation, etc.)
		// Also, if more than one View is needed in the future, initialize them in the constructor as well.
		//
		// This is just placeholder for CellView
		myView1 = new View();
		
		// set resource path
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
		
		BorderPane root = new BorderPane();
		// Center of GUI displays the cell grid
		root.setCenter(makeCellDisplay());
        // Bottom of GUI displays the control panel that consists of all the buttons
        root.setBottom(makeControlPanel());
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
    	return result;
    }

	/**
	 * Initialize all the buttons and sliders.
	 * @return
	 */
	private Node makeControlPanel() {
		HBox result = new HBox();
		// create buttons and slider to change speed
		myStartButton = makeButton("StartCommand", e -> myController.start());
		myPauseButton = makeButton("PauseCommand", e -> myController.pause());
		myStepButton = makeButton("StepCommand", e -> myController.step());
		myResetButton = makeButton("ResetCommand", e -> myController.reset());
		myLoadButton = makeButton("LoadCommand", e -> myController.load());
		mySlider = new Slider(0, 1000, 50);
		mySlider.setOnDragDropped(e -> myController.changeSpeed());
		result.getChildren().addAll(myStartButton, myPauseButton, myStepButton, myResetButton, myLoadButton);
		result.getChildren().add(mySlider);
		return result;
	}
	
	/**
	 * Initialize the Cell Grid from a CellView.
	 * @return
	 */
	private Node makeCellDisplay() {
		return null;
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
