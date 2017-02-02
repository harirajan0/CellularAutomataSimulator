package cellsociety_team24;
import java.util.Collections;
import java.util.List;

public class Controller {
	
	private Cell[][] grid;
	private int rows;
	private int cols;
	private static final String SPREADING_FIRE = "FIRE";
	private static final String WATOR = "WATOR";
	private static final String SEGREGATION = "SEGREGATION";
	private static final String CONWAY = "CONWAY";
	
	private void initializeGrid(int rows, int cols, List<String> states,
			double param, String sim){
		
		this.rows = rows;
		this.cols = cols;
		Collections.shuffle(states);
		grid = new Cell[rows][cols];
		
		for(int r = 0; r < rows; r++){
			for(int c = 0; c < cols; c++){
				int xcor = ViewClass.WINDOW_SIZE/rows*r;
				int ycor = ViewClass.WINDOW_SIZE/cols*c;
				switch(sim){
					case SPREADING_FIRE:
						grid[r][c] = new SpreadingFireCell(states.remove(0), xcor, ycor, param);
						break;
					case WATOR : 
						grid[r][c] = new WaTorCell(states.remove(0),xcor, ycor);
						break;
					case CONWAY : 
						grid[r][c] = new ConwayCell(states.remove(0), xcor, ycor);
						break;
					case SEGREGATION : 
						grid[r][c] = new SegregationCell(states.remove(0), xcor, ycor, param);
						break;
					default : 
							break;
				}
			}
		}
	}
	
	public void updateGrid() {
		
		for(int r = 0; r < rows; r++){
			for(int c = 0; c < cols; c++){
				
				grid[r][c].update();
			}
		}
		
		for(int r = 0; r < rows; r++){
			for(int c = 0; c < cols; c++){
				
				grid[r][c].nextGeneration();
			}
		}
	}

}
