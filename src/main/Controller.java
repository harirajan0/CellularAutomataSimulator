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

	/** This controller class is the central nexus control of the entire program.
	 * It will handle things like when to update the model, when to update the view,
	 * this class holds the cell simulation together
	 */
	public class Controller {
		
		
		// Dimension of the Grid, obtained from Loader
		private Model myModel;
		SimulationGUI myGUI;
		// Controller holds View in order to update it.
		private SimulationView cellSimulationDisplay;
		// Control Panel will provide all the button visuals.
		private ControlPanel cp;
		private SimulationControlPanel bp;
		
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
		 */
		public Controller(){
			myGUI = new SimulationGUI();
			cellSimulationDisplay = myGUI.getSimulationView();
			cp = new ControlPanel();
			bp = new SimulationControlPanel();
			setupCP();
			fps = Resources.DEFAULT_FPS;
			myXMLCreator = new XMLCreator();
			currentShape = cp.getShapeType();
		}
		
		/**
		 * Gets the GUI for the application
		 * @return GUI for the application
		 */
		public SimulationGUI getGUI(){
			return myGUI;
		}
		
		/**
		 * Sets all action listeners for the <code>ControlPanel</code>
		 */
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
			
			bp.setZoomIn(e -> zoomIn());
			bp.setZoomOut(e -> zoomOut());
			bp.setZoomReset(e -> zoomReset());
			cp.addToHBox();
			
			bp.addToHBox();
			
			myGUI.createCP(cp.getControlPanel());
			myGUI.createBP(bp.getSimulationControlPanel());
		}
		
		/**
		 * Called when the display is zoomed in
		 */
		private void zoomIn(){
			myGUI.simViewZoomIn();
		}
		
		/**
		 * Called when the display is zoomed out
		 */
		private void zoomOut(){
			myGUI.simViewZoomOut();
		}
		
		/**
		 * Called when the zoom is reset to default
		 */
		private void zoomReset(){
			myGUI.simViewZoomReset();
		}
		

		/**
		 * Starts the animation
		 */

		// this should be for starting a new simulation maybe? still need to look into it
		private void start() {

			KeyFrame frame = new KeyFrame(Duration.millis(1000/fps), e -> step());
			animation = new Timeline();
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.getKeyFrames().add(frame);
			animation.play();
		}
		
		/**
		 * Resumes the animation
		 */
		private void resume(){
			animation.play();
		}
		
		/**
		 * Pauses the animation
		 */
		private void pause() {
			animation.pause();
		}
		
		/**
		 * Resets the simulation to its initial state
		 */
		private void reset() {
			if(animation != null){
				animation.stop();
			}
			l = new Loader(dataFile, currentShape);
			myModel = l.getFirstGrid();
			myModel.initializeNeighbors();
			myModel.resetIteration();
			cellSimulationDisplay.displayGrid(myModel, currentShape);
			myGUI.createGraphSidePanel(l.getSimulationName(), myModel.getGraph());
		}
		
		/**
		 * Called on each iteration of the animation
		 */
		private void step() {
			myModel.updateModel();
			myGUI.createGraphSidePanel(l.getSimulationName(), myModel.getGraph());
			cellSimulationDisplay.updateGrid(myModel);
		}
		
		/**
		 * Called in GUI once the user clicks the Load button.
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
			myGUI.createGraphSidePanel(l.getSimulationName(), myModel.getGraph());
//			myGUI.createVBox(myModel.getGraph());
			reset();
		}

		
		/**
		 * Writes an XML file of the current simulation state
		 */
		private void save() {
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
			//simulationType, simulationName, numRows, numCols, myList
		}
		
		/**
		 * Creates a <code>FileChooser</code> for selecting a simulation to load
		 * @param extensionAccepted
		 * @return
		 */
	    private FileChooser makeChooser (String extensionAccepted) {
	        FileChooser result = new FileChooser();
	        result.setTitle(Resources.getString("FileChooserTitle"));
	        // pick a reasonable place to start searching for files
	        result.setInitialDirectory(new File(System.getProperty("user.dir"), "./src/resources"));
	        result.getExtensionFilters().setAll(new ExtensionFilter("Text Files", extensionAccepted));
	        return result;
	    }
	    
	    /**
	     * Changes the speed based on a slider's value
	     * @param value Speed to set
	     */
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