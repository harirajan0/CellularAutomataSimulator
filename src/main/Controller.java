	package main;

import java.io.File;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;
import loader.Loader;

import model.Model;

// This controller class is the central nexus control of the entire program.
// It will handle things like when to update the model, when to update the view,
// this class holds the cell simulation togethers

public class Controller {
	
	// Dimension of the Grid, obtained from Loader
	private Model myModel;
	// Controller holds View in order to update it.
	private SimulationView cellSimulationDisplay;
	
	
	private static final int default_fps = 60;
	
	private int fps;
	private int mil_delay;
	private Timeline animation;
	private Loader l;
	private File dataFile;
	
	
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

	public void reset() {
		// not sure if this is actually how you would stop the animation, but maybe this would work.
		if(animation != null){
			animation.stop();
		}
		l = new Loader(dataFile);
		myModel = l.getFirstGrid();
		myModel.initializeNeighbors();
		cellSimulationDisplay.displayGrid(myModel);
	}
	
	public void step() {
		myModel.updateModel();
	}

	/**
	 * This method will be called in GUI once the user clicks the Load button.
	 * @param filename
	 * @return
	 */
	public void load() {
		if(animation != null){
			animation.stop();
		}
		dataFile = myChooser.showOpenDialog(myStage);
		l = new Loader(dataFile);
		myModel = l.getFirstGrid();
		myModel.initializeNeighbors();
		cellSimulationDisplay.displayGrid(myModel);
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
    

	public void changeSpeed(double value) {
		fps *= value;
		KeyFrame frame = new KeyFrame(Duration.millis(1000/fps),
				e -> step());
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	/** !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * Should change once we have the Grid class set up.
	 * Returns the grid in order to pass it to View in GUI.
	 * @return
	 */
	public Model getModel() {
		return myModel;
	}

	
}