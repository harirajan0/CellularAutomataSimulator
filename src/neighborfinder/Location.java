// This entire file is part of my masterpiece.
// Bihan Zhuang

package neighborfinder;

/**
 * A class that does one thing -- to hold the x, y 
 * coordinates of a Cell within the grid.
 * @author zhuangbihan
 *
 */
public class Location {
	
	private int x, y;

	public Location(int a, int b) {
		x = a;
		y = b;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

}
