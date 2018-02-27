package bpogoda.learning.testanalyzer.app.view.answers;

import java.util.List;

import bpogoda.learning.testanalyzer.api.model.answered.AnsweredTest;
import bpogoda.learning.testanalyzer.api.model.template.TestTemplate;
import bpogoda.learning.testanalyzer.api.stats.answers.CorrectAnswerDistribiutionAnalyzer;
import bpogoda.learning.testanalyzer.app.view.TestDataHandlingController;
import javafx.fxml.FXML;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class CorrectAnswerDistributionController implements TestDataHandlingController {

	@FXML
	private BarChart<String, Number> correctAnswerDistributionBarChart;

	@Override
	public void processTestData(TestTemplate testTemplate, List<AnsweredTest> answeredTests) {
		CorrectAnswerDistribiutionAnalyzer correctAnswerDistribiutionAnalyzer = new CorrectAnswerDistribiutionAnalyzer();

		List<Integer> correctAnswerDistribution = correctAnswerDistribiutionAnalyzer.analyze(testTemplate, answeredTests);
	
		Axis<String> xAxis = correctAnswerDistributionBarChart.getXAxis();
		Axis<Number> yAxis = correctAnswerDistributionBarChart.getYAxis();

		XYChart.Series<String, Number> series1 = new XYChart.Series<>();

		for (int i = 0 ; i < correctAnswerDistribution.size() ; i++ ) {
			series1.getData().add(new XYChart.Data<>(Integer.toString(i+1), correctAnswerDistribution.get(i)));
		}

		correctAnswerDistributionBarChart.getData().clear();
		correctAnswerDistributionBarChart.getData().add(series1);
		
		correctAnswerDistributionBarChart.setCategoryGap(0);
		correctAnswerDistributionBarChart.setBarGap(0);
		correctAnswerDistributionBarChart.autosize();

		xAxis.setLabel("Question identifiers");
		yAxis.setLabel("Number of correct answers");
	}

	@Override
	public void clear() {
		correctAnswerDistributionBarChart.getData().clear();

	}

}
