package main;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cells.Cell;
import cells.ConwayCell;
import cells.SegregationCell;
import cells.SpreadingFireCell;
import cells.WaTorCell;
import loader.Loader;

public class Controller {
	
	private Cell[][] grid;
	private int rows;
	private int cols;
	private static final String SPREADING_FIRE = "FIRE";
	private static final String WATOR = "WATOR";
	private static final String SEGREGATION = "SEGREGATION";
	private static final String CONWAY = "CONWAY";
	
	public Controller(){
		
		Loader l = new Loader("filename");
		rows = l.getRows();
		cols = l.getCols();
		
		List<String> states = l.getStates();
		Collections.shuffle(states);
		grid = new Cell[rows][cols];
		createCells(states, l.getParameter(), l.getSimulationType());
		initializeNeighbors();
	}
		
	private void createCells(List<String> states, double param, String sim)
	{
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
	
	private void initializeNeighbors()
	{
		for(int r = 0; r < rows; r++){
			for(int c = 0; c < cols; c++){
				
				List<Cell> neighbors = new ArrayList<Cell>();
				for(int horiz = r-1; horiz <= r+1; horiz += 2){
					for(int vert = c-1; vert <= c+1; vert += 2){
						
						if(horiz >= 0 && horiz < rows && vert >= 0 && vert < cols)
						{
							neighbors.add(grid[horiz][vert]);
						}
					}
				}
				grid[r][c].setNeighbors(neighbors);
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