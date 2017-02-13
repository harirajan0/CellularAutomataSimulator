package cellshapeviews;

public class TriangleShapeView extends PolygonShapeView {
	
	private double sideLength;
	private final double FACTOR = 1.9;

	public TriangleShapeView(int r, int c, double size) {
		super(r, c, size);
		this.sideLength = size*FACTOR;
		definePolygon();
	}

	@Override
	public void definePolygon() {
		getPolygon().getPoints().addAll(this.calculateCoordinates());
	}

	@Override
	public Double[] calculateCoordinates() {
		Double[] coord = new Double[6];
		Double[] tmp = pivotPoint();
		double x = tmp[0];
		double y = tmp[1];
		coord[0] = x;
		coord[1] = y;
		if (sameParity()){
			coord[2] = x + sideLength;
			coord[3] = y;
			coord[4] = x + sideLength/2;
			coord[5] = y + Math.sqrt(3.0)/2 * sideLength;
		} else{
			coord[2] = x + sideLength/2;
			coord[3] = y + Math.sqrt(3.0)/2 * sideLength;
			coord[4] = x - sideLength/2;
			coord[5] = y + Math.sqrt(3.0)/2 * sideLength;
		}
		return coord;
	}

	@Override
	public Double[] pivotPoint() {
		Double[] myXY = new Double[2];
		if (oddRow()){
			if (getCol()%2!=0){
				myXY[0] = sideLength * ((getCol()-1)/2 + 0.5);
			} else{
				myXY[0] = sideLength * (getCol()/2 + 0.5);
			}
		} else{
			if (getCol()%2==0){
				myXY[0] = sideLength * getCol()/2;
			} else{
				myXY[0] = sideLength * (getCol()+1)/2;
			}	
		}
		myXY[1] = sideLength * Math.sqrt(3.0)/2 * getRow();
		return myXY;
	}

}
