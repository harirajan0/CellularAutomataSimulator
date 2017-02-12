package main;

import javafx.scene.layout.VBox;
import java.util.HashMap;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class GraphPanel {
	private VBox graphBox;
	private LineChart<Number, Number> chart;
	
	/**
	 * TODO: Set up chart colors using CSS (preferred method)
	 * @param statesMap
	 */
	public GraphPanel(HashMap<String, Double> statesMap){
		graphBox = new VBox();
		graphBox.setStyle("-fx-background-color: gray");
		graphBox.setSpacing(25);
		
		NumberAxis tAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		tAxis.setLabel("Iterations");
		yAxis.setLabel("Population");
		
		chart = new LineChart<Number, Number>(tAxis, yAxis);
		
		for(String state : statesMap.keySet()){
			//Initialize new series for each type
			XYChart.Series<Number, Number> series = 
					new XYChart.Series<Number, Number>();
			series.setName(state);
			
			//Plot initial state
			series.getData().add(
					new XYChart.Data<Number, Number>(0, statesMap.get(state)));
			chart.getData().add(series);
		}
		graphBox.getChildren().add(chart);
	}
	
	public void update(HashMap<String, Double> populations, double iteration){
		if(populations.size() != chart.getData().size()){
			throw new IllegalArgumentException("Invalid number of states passed");
		}
		
		for(int index = 0; index < chart.getData().size(); index++){
			chart.getData().get(index).getData().add(
					new XYChart.Data<Number, Number>(0, populations.get(index)));
		}
	}
}
