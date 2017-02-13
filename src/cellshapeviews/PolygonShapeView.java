package cellshapeviews;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;

public abstract class PolygonShapeView {
	
	private Polygon myPolygon;
	private int row, col;
	private double sideLength;

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
	
	protected abstract Double[] calculateCoordinates();
	
	protected abstract Double[] pivotPoint();
	
	protected void changeCellState(EventHandler<? super MouseEvent> value){
		myPolygon.setOnMouseClicked(value);
	}
	
	public int getRow(){
		return row;
	}
	
	public int getCol(){
		return col;
	}
	
	public double getSideLength(){
		return sideLength;
	}
	
	protected boolean oddRow(){
		return row%2==1;
	}
	
	protected boolean sameParity(){
		return (row+col)%2==0;
	}
	
	public Polygon getPolygon(){
		return myPolygon;
	}
}
