package main;

import javafx.scene.layout.VBox;
import resources.Resources;

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
		graphBox.setStyle(Resources.WHITE_PANE_STYLE);
		graphBox.setSpacing(25);
		
		NumberAxis tAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		tAxis.setLabel(Resources.getString("Iterations"));
		yAxis.setLabel(Resources.getString("Population"));
		
		chart = new LineChart<Number, Number>(tAxis, yAxis);
		chart.getXAxis().setAutoRanging(true);
		chart.getYAxis().setAutoRanging(true);
		
		for(String state : states){
			//Initialize new series for each type
			XYChart.Series<Number, Number> series = 
					new XYChart.Series<Number, Number>();
			series.setName(state);
			chart.getData().add(series);
		}
		graphBox.getChildren().add(chart);
	}
	
	public void update(List<Double> populations, double iteration){
		if(populations.size() != chart.getData().size()){
			throw new IllegalArgumentException("Only " + populations.size() + 
					" states passed. Need " + chart.getData().size());
		}
		
		for(int index = 0; index < chart.getData().size(); index++){
			chart.getData().get(index).getData().add(
					new XYChart.Data<Number, Number>(iteration, populations.get(index)));
		}
	}
	
	public VBox getGraph(){
		return graphBox;
	}
}