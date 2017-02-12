package main;

import javafx.scene.layout.VBox;
import java.util.List;
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
	public GraphPanel(List<String> states){
		graphBox = new VBox();
		graphBox.setStyle("-fx-background-color: gray");
		graphBox.setSpacing(25);
		
		NumberAxis tAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		tAxis.setLabel("Iterations");
		yAxis.setLabel("Population");
		
		chart = new LineChart<Number, Number>(tAxis, yAxis);
		
		for(String state : states){
			//Initialize new series for each type
			XYChart.Series<Number, Number> series = 
					new XYChart.Series<Number, Number>();
			series.setName(state);
		}
		graphBox.getChildren().add(chart);
	}
	
	public void update(List<Double> populations, double iteration){
		if(populations.size() != chart.getData().size()){
			throw new IllegalArgumentException("Invalid number of states passed");
		}
		
		for(int index = 0; index < chart.getData().size(); index++){
			chart.getData().get(index).getData().add(
					new XYChart.Data<Number, Number>(0, populations.get(index)));
		}
	}
	
	public VBox getGraph(){
		return graphBox;
	}
}