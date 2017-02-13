package cellshapeviews;

/**
 * A shape view for a square cell
 */
public class SquareShapeView extends PolygonShapeView {
	/**
	 * Creates a square shape view
	 * @param r The row number of the cell
	 * @param c The column number of the cell
	 * @param size The side length of the cell
	 */
	public SquareShapeView(int r, int c, double size) {
		super(r, c, size);
		definePolygon();
	}
	
	@Override
	protected void definePolygon() {
		getPolygon().getPoints().addAll(this.calculateCoordinates());
	}
	
	@Override
	protected Double[] calculateCoordinates() {
		Double[] coord = new Double[8];
		double x = getSideLength()*getCol();
		double y = getSideLength()*getRow();
		coord[0] = x;
		coord[1] = y;
		coord[2] = x + getSideLength();
		coord[3] = y;
		coord[4] = x + getSideLength();
		coord[5] = y + getSideLength();
		coord[6] = x;
		coord[7] = y + getSideLength();
		return coord;
	}
	
	@Override
	protected Double[] pivotPoint() {
		return null;
	}
}