package cellshapeviews;

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
	public abstract void definePolygon();
	
	public abstract Double[] calculateCoordinates();
	
	public abstract Double[] pivotPoint();
	
	public int getRow(){
		return row;
	}
	
	public int getCol(){
		return col;
	}
	
	public double getSideLength(){
		return sideLength;
	}
	
	public boolean oddRow(){
		return row%2==1;
	}
	
	public boolean sameParity(){
		return (row+col)%2==0;
	}
	
	public Polygon getPolygon(){
		return myPolygon;
	}
}
