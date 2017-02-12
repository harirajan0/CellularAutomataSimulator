package cellshapeviews;
public class SquareShapeView extends PolygonShapeView {
	public SquareShapeView(int r, int c, double size) {
		super(r, c, size);
		definePolygon();
	}
	@Override
	public void definePolygon() {
		getPolygon().getPoints().addAll(this.calculateCoordinates());
	}
	@Override
	public Double[] calculateCoordinates() {
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
	public Double[] pivotPoint() {
		return null;
	}
}