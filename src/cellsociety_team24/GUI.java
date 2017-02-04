package cellsociety_team24;

import java.util.ResourceBundle;

import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * A class used to display the different View objects and hold buttons.
 * @author Bihan Zhuang
 *
 */
public class GUI {
	
	public static final Dimension2D DEFAULT_SIZE = new Dimension2D(800, 600);
    //public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    //public static final String STYLESHEET = "default.css";
    public static final String BLANK = " ";

    // scene, needed to report back to Application
    private Scene myScene;
    // information area
    private Label myStatus;
    // navigation
    private TextField mySimulation;
    private TextField myNumGeneration;
    private Button myPlayButton;
    private Button myPauseButton;
    private Button myStepButton;
    private Button myResetButton;
    private Button myLoadButton;
    private View myView1;
    
    // favorites
    private ComboBox<String> myFavorites;
    // get strings from resource file
    //private ResourceBundle myResources;
    // the Model for the particular simulation
    private Grid myGrid;
    
    
    /**
     * GUI constructor.
     */
	public GUI() {
		
	}

}
