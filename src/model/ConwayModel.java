package model;

import java.util.ArrayList;
import java.util.List;

import cells.Cell;
import cells.ConwayCell;
import loader.XMLParser;
import main.ApplicationStartup;

public class ConwayModel extends Model {
	public ConwayModel(int r, int c) {
		super(r, c);
	}
	
	@Override
	public void populateCells(XMLParser parser, double param) {
		int cellNum = 0;
		int sideLength = ApplicationStartup.WINDOW_SIZE / Math.max(getRows(), getCols());
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				int xPosition = row * sideLength;
				int yPosition = col * sideLength;
				set(row, col, new ConwayCell(parser.getTextValue("state" + Integer.toString(cellNum)),
						xPosition, yPosition, sideLength));
				cellNum++;
			}
		}
	}
}
