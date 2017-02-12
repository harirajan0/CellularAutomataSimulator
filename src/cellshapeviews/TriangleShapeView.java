package cellshapeviews;

public class TriangleShapeView extends PolygonShapeView {

	public TriangleShapeView(int r, int c, double size) {
		super(r, c, size);
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
			coord[2] = x + getSideLength();
			coord[3] = y;
			coord[4] = x + getSideLength()/2;
			coord[5] = y + Math.sqrt(3.0)/2 * getSideLength();
		} else{
			coord[2] = x + getSideLength()/2;
			coord[3] = y + Math.sqrt(3.0)/2 * getSideLength();
			coord[4] = x - getSideLength()/2;
			coord[5] = y + Math.sqrt(3.0)/2 * getSideLength();
		}
		return coord;
	}

	@Override
	public Double[] pivotPoint() {
		Double[] myXY = new Double[2];
		if (oddRow()){
			if (getCol()%2!=0){
				myXY[0] = getSideLength() * ((getCol()-1)/2 + 0.5);
			} else{
				myXY[0] = getSideLength() * (getCol()/2 + 0.5);
			}
		} else{
			if (getCol()%2==0){
				myXY[0] = getSideLength() * getCol()/2;
			} else{
				myXY[0] = getSideLength() * (getCol()+1)/2;
			}	
		}
		myXY[1] = getSideLength() * Math.sqrt(3.0)/2 * getRow();
		return myXY;
	}

}
