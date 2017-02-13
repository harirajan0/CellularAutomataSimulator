package cellshapeviews;

/**
 * Shape view for hexagonal cells
 */
public class HexagonShapeView extends PolygonShapeView {
	
	private double sideLength;

	/**
	 * Creates a hexagonal shape view
	 * @param r Row number of cell
	 * @param c Column number of cell
	 * @param sideLength Length of the cell's sides
	 */
	public HexagonShapeView(int r, int c, double sideLength) {
		super(r, c, sideLength);
		this.sideLength = sideLength/1.6;
		definePolygon();
	}

	@Override
	protected void definePolygon() {
		getPolygon().getPoints().addAll(this.calculateCoordinates());
	}
	
	/**
	 * Calculates all the x,y coordinates for six vertices, from the upper
	 * left corner, in clockwise order.
	 */
	@Override
	protected Double[] calculateCoordinates() {
		Double[] coord = new Double[12];
		Double[] tmp = pivotPoint();
		double x = tmp[0];
		double y = tmp[1];
		coord[0] = x;
		coord[1] = y;
		coord[2] = x + sideLength*Math.sqrt(3.0)/2;
		coord[3] = y - sideLength/2;
		coord[4] = x + sideLength*Math.sqrt(3.0);
		coord[5] = y;
		coord[6] = x + sideLength*Math.sqrt(3.0);
		coord[7] = y + sideLength;
		coord[8] = x + sideLength*Math.sqrt(3.0)/2;
		coord[9] = y + sideLength*1.5;
		coord[10] = x;
		coord[11] = y + sideLength;
		return coord;
	}

	@Override
	protected Double[] pivotPoint(){
		Double[] myXY = new Double[2];
		if (oddRow()){
			myXY[0] = sideLength*Math.sqrt(3.0)*(0.5+getCol());
		} else{
			myXY[0] = sideLength*Math.sqrt(3.0)*getCol();
		}
		myXY[1] = sideLength*(0.5+1.5*getRow());
		return myXY;
	}
}
