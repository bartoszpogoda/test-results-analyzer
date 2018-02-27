package bpogoda.learning.testanalyzer.app.view.histogram;

import java.util.List;

import bpogoda.learning.testanalyzer.api.model.answered.AnsweredTest;
import bpogoda.learning.testanalyzer.api.model.template.TestTemplate;
import bpogoda.learning.testanalyzer.api.stats.histogram.HistogramResultsAnalyzer;
import bpogoda.learning.testanalyzer.app.view.TestDataHandlingController;
import javafx.fxml.FXML;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class HistogramController implements TestDataHandlingController {

	@FXML
	private BarChart<String, Number> histogramBarChart;

	@Override
	public void processTestData(TestTemplate testTemplate, List<AnsweredTest> answeredTests) {

		HistogramResultsAnalyzer histogramResultsAnalyzer = new HistogramResultsAnalyzer();
		
		int[] histogramData = histogramResultsAnalyzer.generateHistogramData(testTemplate, answeredTests);
		
        Axis<String> xAxis = histogramBarChart.getXAxis();
        Axis<Number> yAxis = histogramBarChart.getYAxis();

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Histogram");

        series1.getData().add(new XYChart.Data<>("0-10", histogramData[0]));
        series1.getData().add(new XYChart.Data<>("10-20", histogramData[1]));
        series1.getData().add(new XYChart.Data<>("20-30", histogramData[2]));
        series1.getData().add(new XYChart.Data<>("30-40", histogramData[3]));
        series1.getData().add(new XYChart.Data<>("40-50", histogramData[4]));
        series1.getData().add(new XYChart.Data<>("50-60", histogramData[5]));
        series1.getData().add(new XYChart.Data<>("60-70", histogramData[6]));
        series1.getData().add(new XYChart.Data<>("70-80", histogramData[7]));
        series1.getData().add(new XYChart.Data<>("80-90", histogramData[8]));
        series1.getData().add(new XYChart.Data<>("90-100", histogramData[9]));
        
        histogramBarChart.getData().clear();
        histogramBarChart.getData().add(series1);
        histogramBarChart.setCategoryGap(0);
        histogramBarChart.setBarGap(0);
        histogramBarChart.autosize();
        
        xAxis.setLabel("Percentage result boundaries");
        yAxis.setLabel("Number of results");
	}

	@Override
	public void clear() {
		histogramBarChart.getData().clear();
	}

}
