package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import alerts.CellSocietyAlerts;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import loader.Loader;
import loader.XMLCreator;
import model.Model;
import resources.Resources;

	// This controller class is the central nexus control of the entire program.
	// It will handle things like when to update the model, when to update the view,
	// this class holds the cell simulation togethers
	public class Controller {
		
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
		private String currentShape;

	    // it is generally accepted behavior that the chooser remembers where user left it last
	    private FileChooser myChooser = makeChooser(Resources.DATA_FILE_EXTENSION);

	    private XMLCreator myXMLCreator;
		
		/**
		 * Constructor for the Controller
		 * Sets view for the instance View in Controller.
		 * Can be called in GUI multiple times to set up different views.
		 * @param view
		 */
		public Controller(){
			myGUI = new SimulationGUI("English");
			cellSimulationDisplay = myGUI.getSimulationView();
			cp = new ControlPanel();
			setupCP();
			fps = Resources.DEFAULT_FPS;
			myXMLCreator = new XMLCreator();
			currentShape = cp.getShapeType();
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
			cp.getChoiceBox().valueProperty().addListener(e -> 
					changeShape(Resources.SHAPES[cp.getChoiceBox().getSelectionModel().getSelectedIndex()]));
			
			cp.setZoomIn(e -> zoomIn());
			cp.setZoomOut(e -> zoomOut());
			cp.setZoomReset(e -> zoomReset());

			cp.addToHBox();
			
			myGUI.createCP(cp.getControlPanel());
		}
		
		private void zoomIn(){
			myGUI.simViewZoomIn();
		}
		
		private void zoomOut(){
			myGUI.simViewZoomOut();
		}
		
		private void zoomReset(){
			myGUI.simViewZoomReset();
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
			l = new Loader(dataFile, currentShape);
			myModel = l.getFirstGrid();
			myModel.initializeNeighbors();
			cellSimulationDisplay.displayGrid(myModel, currentShape);
		}
		
		private void step() {
			myModel.updateModel();
			cellSimulationDisplay.updateGrid(myModel);
		}
		
		/**
		 * This method will be called in GUI once the user clicks the Load button.
		 * @return
		 */
		private void load() {
			if ((dataFile = myChooser.showOpenDialog(null)) == null) return;
			try {
				l = new Loader(dataFile, currentShape);
			} catch (Exception e) {
				if (CellSocietyAlerts.xmlError(e, dataFile)) load();
				return;
			} 
			myModel = l.getFirstGrid();
			myModel.initializeNeighbors();
			reset();
		}
		
		private void save() {
			//call method to write XML based on current state
			List<String> states = new ArrayList<String>();
			for (int row = 0; row < myModel.getRows(); row++) {
				StringBuilder rowData = new StringBuilder("");
				for (int col = 0; col < myModel.getCols(); col++) {
					rowData.append(Integer.toString(myModel.get(row, col).getCurrentState().getStateValue()));
				}
				states.add(rowData.toString());
			}
			myXMLCreator.createXML
				(l.getSimulationType(), l.getSimulationName(), myModel.getRows(), myModel.getCols(), states, l.getParameter());
		}
		
		// set some sensible defaults when the FileChooser is created
	    private FileChooser makeChooser (String extensionAccepted) {
	        FileChooser result = new FileChooser();
	        result.setTitle(Resources.getString("FileChooserTitle"));
	        // pick a reasonable place to start searching for files
	        result.setInitialDirectory(new File(System.getProperty("user.dir"), "./src/resources"));
	        result.getExtensionFilters().setAll(new ExtensionFilter("Text Files", extensionAccepted));
	        return result;
	    }
	    
		private void changeSpeed(double value) {
			fps = Resources.DEFAULT_FPS * value;
			animation.stop();
			KeyFrame frame = new KeyFrame(Duration.millis(1000/fps),
					e -> step());
			animation = new Timeline();
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.getKeyFrames().add(frame);
			animation.play();
		}
		
		private void changeShape(String newShape) {
			currentShape = newShape;
			cellSimulationDisplay.displayGrid(myModel, currentShape);
		}
	}