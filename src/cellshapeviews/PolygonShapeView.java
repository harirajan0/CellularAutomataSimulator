package cellshapeviews;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;

/**
 * Abstract class for all cell shape views.
 */
public abstract class PolygonShapeView {
	
	private Polygon myPolygon;
	private int row, col;
	private double sideLength;

	/**
	 * Creates a shape view
	 * @param r Row number of the cell
	 * @param c Column number of the cell
	 * @param size Side length of the cell
	 */
	public PolygonShapeView(int r, int c, double size) {
		myPolygon = new Polygon();
		row = r;
		col = c;
		sideLength = size;
	}
	
	/**
	 * Calculates all the coordinates needed to define a polygon.
	 */
	protected abstract void definePolygon();
	
	/**
	 * Calculates the coordinates for the simulation view
	 * @return The coordinates in the simulation view
	 */
	protected abstract Double[] calculateCoordinates();
	
	/**
	 * Calculates the pivot point of the cell
	 * @return The coordinates of the pivot point of the cell
	 */
	protected abstract Double[] pivotPoint();
	
	/**
	 * Changes the state of the cell on click
	 * @param value Click event 
	 */
	protected void changeCellState(EventHandler<? super MouseEvent> value){
		myPolygon.setOnMouseClicked(value);
	}
	
	/**
	 * Gets the row number of the cell
	 * @return The row number of the cell
	 */
	public int getRow(){
		return row;
	}
	
	/**
	 * Gets the column number of the cell
	 * @return The column number of the cell
	 */
	public int getCol(){
		return col;
	}
	
	/**
	 * Gets the side length of the cell
	 * @return The side length of the cell
	 */
	public double getSideLength(){
		return sideLength;
	}
	

	/**
	 * Tells whether or not the row number is odd
	 * @return Whether or not the row number is odd
	 */
	protected boolean oddRow(){
		return row%2==1;
	}
	/**
	 * Returns whether or not the row and column are both even or odd
	 * @return Whether or not the row and column are both even or odd
	 */
	protected boolean sameParity(){
		return (row+col)%2==0;
	}
	
	/**
	 * Gets the <code>Polygon</code> corresponding to the cell
	 * @return
	 */
	public Polygon getPolygon(){
		return myPolygon;
	}
}
