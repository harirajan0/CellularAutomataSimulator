package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

import cells.ConwayCell;
import cells.SegregationCell;
import cells.SpreadingFireCell;
import cells.WaTorCell;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;
import loader.Loader;

import cells.Cell;
import model.Model;
import model.SquareModel;

// This controller class is the central nexus control of the entire program.
// It will handle things like when to update the model, when to update the view,
// this class holds the cell simulation togethers

public class Controller {
	
	// Dimension of the Grid, obtained from Loader
	private int rows, cols;
	// Grid instance variable
	private Model myModel;
	// Controller holds View in order to update it.
	private SimulationView cellSimulationDisplay;
	
	private Loader loader;
	
	private static final int default_fps = 60;
	
	private int fps;
	private int mil_delay;
	private double sec_delay;
	private Timeline animation;

	private Model model;
	
	// kind of data files to look for
    public static final String DATA_FILE_EXTENSION = "*.xml";
    // it is generally accepted behavior that the chooser remembers where user left it last
    private FileChooser myChooser = makeChooser(DATA_FILE_EXTENSION);
    
	private Stage myStage;
	
	
	/**
	 * Constructor for the Controller
	 * Sets view for the instance View in Controller.
	 * Can be called in GUI multiple times to set up different views.
	 * @param view
	 */
	public Controller(SimulationGUI gui, Stage stage){
//		myCellView = view;
		cellSimulationDisplay = gui.getSimulationView();
		myStage = stage;
//		stage = s;
		fps = default_fps;
		mil_delay = 1000/fps;
		sec_delay = 1.0/fps;
		
	}

	// this should be for starting a new simulation maybe? still need to look into it
	public void start() {
		KeyFrame frame = new KeyFrame(Duration.millis(mil_delay),
				e -> step());
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
		
	}
	
	// this should be for playing a paused simulation? maybe... rip idk if this actually works.
	// just a guess.
	public void resume(){
		animation.play();
	}

	public void pause() {
		// not sure if this is how you would stop the animation, but maybe this would work.
		animation.pause();
	}
	
	/**
	 * Call on every iteration of animation
	 */
	public void step() {
		// update model
		myModel.updateModel();
		// update view
		
		// this method should e less than 5 lines. probably just 2 method calls will already be enough,
		// but we'll see.
		myModel.updateModel();
		cellSimulationDisplay.displayGrid(myModel);
	}

	public void reset() {
		// not sure if this is actually how you would stop the animation, but maybe this would work.
		animation.stop();
		
	}

	/**
	 * This method will be called in GUI once the user clicks the Load button.
	 * @param filename
	 * @return
	 */
	public void load() {
		File dataFile = myChooser.showOpenDialog(myStage);
		Loader l = new Loader(dataFile);
		rows = l.getRows();
		cols = l.getCols();
		myModel = l.getFirstGrid();
	}
	
	
	// set some sensible defaults when the FileChooser is created
    private FileChooser makeChooser (String extensionAccepted) {
        FileChooser result = new FileChooser();
        result.setTitle("Open Data File");
        // pick a reasonable place to start searching for files
        result.setInitialDirectory(new File(System.getProperty("user.dir")));
        result.getExtensionFilters().setAll(new ExtensionFilter("Text Files", extensionAccepted));
        return result;
    }
    
	private Model loadModel(String filename){

		return model;
	}

	public void changeSpeed(double currentSpeed) {
		fps *= currentSpeed;
	}

	/** !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * Should change once we have the Grid class set up.
	 * Returns the grid in order to pass it to View in GUI.
	 * @return
	 */
	public Model getGrid() {
		return myModel;
	}

	
}