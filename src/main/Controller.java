package main;

import java.io.File;
import java.util.Optional;
import java.util.ResourceBundle;

import alerts.CellSocietyAlerts;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import loader.Loader;
import loader.XMLException;
import model.Model;

	// This controller class is the central nexus control of the entire program.
	// It will handle things like when to update the model, when to update the view,
	// this class holds the cell simulation togethers
	public class Controller {
		
		// kind of data files to look for
	    public static final String DATA_FILE_EXTENSION = "*.xml";
	    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
		private static final double DEFAULT_FPS = 1;
		
		// Dimension of the Grid, obtained from Loader
		private Model myModel;
		SimulationGUI myGUI;
		// Controller holds View in order to update it.
		private SimulationView cellSimulationDisplay;
		// Control Panel will provide all the button visuals.
		private ControlPanel cp;
		
		private double fps;
		private Timeline animation;
		private Loader l;
		private File dataFile;

	    // it is generally accepted behavior that the chooser remembers where user left it last
	    private FileChooser myChooser = makeChooser(DATA_FILE_EXTENSION);
		
		
		/**
		 * Constructor for the Controller
		 * Sets view for the instance View in Controller.
		 * Can be called in GUI multiple times to set up different views.
		 * @param view
		 */
		public Controller(){
			myGUI = new SimulationGUI("English");
			cellSimulationDisplay = myGUI.getSimulationView();
			cp = new ControlPanel(ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English"));
			setupCP();
			fps = DEFAULT_FPS;
		}
		
		public SimulationGUI getGUI(){
			return myGUI;
		}
		
		private void setupCP(){
			cp.setStart(e -> start());
			cp.setPause(e -> pause());
			cp.setStep(e -> step());
			cp.setReset(e -> reset());
			cp.setLoad(e -> load());
			cp.setResume(e -> resume());
			cp.setSave(e -> save());
			cp.getSlider().valueProperty().addListener(e -> changeSpeed(cp.getSlider().getValue()));
			cp.addToHBox();
			myGUI.createCP(cp.getControlPanel());
		}
		
		// this should be for starting a new simulation maybe? still need to look into it
		public void start() {
			KeyFrame frame = new KeyFrame(Duration.millis(1000/fps), e -> step());
			animation = new Timeline();
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.getKeyFrames().add(frame);
			animation.play();
		}
		
		private void resume(){
			animation.play();
		}
		
		private void pause() {
			animation.pause();
		}
		
		private void reset() {
			if(animation != null){
				animation.stop();
			}
			try {
				l = new Loader(dataFile, cp.getShapeType());
			} catch (XMLException e) {
				//alert with e.getString Please choose another file.
				if (CellSocietyAlerts.tagNameError(e, dataFile)) load();
				return;
			} catch (StringIndexOutOfBoundsException e) {
				if(CellSocietyAlerts.cellDataError(e)) load();
				return;
			}
			myModel = l.getFirstGrid();
			myModel.initializeNeighbors();
			cellSimulationDisplay.displayGrid(myModel);
		}
		
		private void step() {
			myModel.updateModel();
		}
		
		/**
		 * This method will be called in GUI once the user clicks the Load button.
		 * @return
		 */
		private void load() {
			if ((dataFile = myChooser.showOpenDialog(null)) == null) return;
			reset();
		}
		
		private void save() {
			//call method to write XML based on current state
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
	    
		private void changeSpeed(double value) {
			fps = DEFAULT_FPS*value;
			animation.stop();
			KeyFrame frame = new KeyFrame(Duration.millis(1000/fps),
					e -> step());
			animation = new Timeline();
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.getKeyFrames().add(frame);
			animation.play();
		}
	}