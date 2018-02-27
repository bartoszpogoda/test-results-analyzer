package bpogoda.learning.testanalyzer.app.view.grades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import bpogoda.learning.testanalyzer.api.model.answered.AnsweredTest;
import bpogoda.learning.testanalyzer.api.model.template.TestTemplate;
import bpogoda.learning.testanalyzer.api.stats.grades.Grade;
import bpogoda.learning.testanalyzer.api.stats.grades.GradeBalanceResultsAnalyzer;
import bpogoda.learning.testanalyzer.api.stats.grades.GradeRanges;
import bpogoda.learning.testanalyzer.app.view.TestDataHandlingController;
import javafx.fxml.FXML;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

public class GradeBalanceController implements TestDataHandlingController {

	private TestTemplate testTemplate;
	private List<AnsweredTest> answeredTests;
	private GradeRanges gradeRanges;

	@FXML
	private Text numberOfQuestionsTxt, maxNumberOfPointsTxt;

	@FXML
	private TextField range3, range3_5, range4, range4_5, range5, range5_5;

	@FXML
	private BarChart<String, Number> gradeBalanceBarChart;

	public GradeBalanceController() {
		gradeRanges = new GradeRanges();
	}

	@FXML
	public void applyConfiguration() {
		if(validateConfiguration()) {
			gradeRanges.setRange(Grade.GR_3, Integer.parseInt(range3.getText()));
			gradeRanges.setRange(Grade.GR_3_5, Integer.parseInt(range3_5.getText()));
			gradeRanges.setRange(Grade.GR_4, Integer.parseInt(range4.getText()));
			gradeRanges.setRange(Grade.GR_4_5, Integer.parseInt(range4_5.getText()));
			gradeRanges.setRange(Grade.GR_5, Integer.parseInt(range5.getText()));
			gradeRanges.setRange(Grade.GR_5_5, Integer.parseInt(range5_5.getText()));
			
			updateChart();
		}
	}

	private boolean validateConfiguration() {

		List<Integer> rangeLowerBounds = new ArrayList<>();
		try {
			rangeLowerBounds.add(Integer.parseInt(range3.getText()));
			rangeLowerBounds.add(Integer.parseInt(range3_5.getText()));
			rangeLowerBounds.add(Integer.parseInt(range4.getText()));
			rangeLowerBounds.add(Integer.parseInt(range4_5.getText()));
			rangeLowerBounds.add(Integer.parseInt(range5.getText()));
			rangeLowerBounds.add(Integer.parseInt(range5_5.getText()));

		} catch (NumberFormatException e) {
			showValidationError("Fields must be filled with numbers");
			return false;
		}

		for (int i = 0; i < rangeLowerBounds.size(); i++) {
			if (rangeLowerBounds.get(i) < 0) {
				showValidationError("Numbers can't be negative");
				return false;
			}

			if (rangeLowerBounds.get(i) > testTemplate.getItems().size()) {

				showValidationError("Numbers can't be bigger than max number of points to get.");
				return false;
			}

			if (i < rangeLowerBounds.size() - 1 && rangeLowerBounds.get(i) > rangeLowerBounds.get(i + 1)) {
				showValidationError("Bounds must be in ascending order regarding to grades");
				return false;

			}
		}

		return true;
	}

	private void showValidationError(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Validation error");
		alert.setHeaderText(message);

		alert.show();
	}

	@Override
	public void processTestData(TestTemplate testTemplate, List<AnsweredTest> answeredTests) {
		this.testTemplate = testTemplate;
		this.answeredTests = answeredTests;

		int maxNumberOfPoints = testTemplate.getItems().size();

		numberOfQuestionsTxt.setText(Integer.toString(maxNumberOfPoints));
		maxNumberOfPointsTxt.setText(Integer.toString(maxNumberOfPoints));

		// default ranges
		gradeRanges.setRange(Grade.GR_3, (int) (0.5 * maxNumberOfPoints));
		gradeRanges.setRange(Grade.GR_3_5, (int) (0.6 * maxNumberOfPoints));
		gradeRanges.setRange(Grade.GR_4, (int) (0.7 * maxNumberOfPoints));
		gradeRanges.setRange(Grade.GR_4_5, (int) (0.8 * maxNumberOfPoints));
		gradeRanges.setRange(Grade.GR_5, (int) (0.9 * maxNumberOfPoints));
		gradeRanges.setRange(Grade.GR_5_5, (int) (0.95 * maxNumberOfPoints));

		range3.setText(Integer.toString(gradeRanges.getLowerBound(Grade.GR_3)));
		range3_5.setText(Integer.toString(gradeRanges.getLowerBound(Grade.GR_3_5)));
		range4.setText(Integer.toString(gradeRanges.getLowerBound(Grade.GR_4)));
		range4_5.setText(Integer.toString(gradeRanges.getLowerBound(Grade.GR_4_5)));
		range5.setText(Integer.toString(gradeRanges.getLowerBound(Grade.GR_5)));
		range5_5.setText(Integer.toString(gradeRanges.getLowerBound(Grade.GR_5_5)));

		updateChart();
	}

	private void updateChart() {
		GradeBalanceResultsAnalyzer gradeBalanceResultsAnalyzer = new GradeBalanceResultsAnalyzer();

		Map<Grade, Integer> calculatedGradeScores = gradeBalanceResultsAnalyzer.calculateGradeScores(gradeRanges,
				testTemplate, answeredTests);

		Axis<String> xAxis = gradeBalanceBarChart.getXAxis();
		Axis<Number> yAxis = gradeBalanceBarChart.getYAxis();

		XYChart.Series<String, Number> series1 = new XYChart.Series<>();

		for (Grade grade : calculatedGradeScores.keySet()) {
			series1.getData().add(new XYChart.Data<>(grade.toString(), calculatedGradeScores.get(grade)));
		}

		gradeBalanceBarChart.getData().clear();
		gradeBalanceBarChart.getData().add(series1);
		
		gradeBalanceBarChart.setCategoryGap(0);
		gradeBalanceBarChart.setBarGap(0);
		gradeBalanceBarChart.autosize();

		xAxis.setLabel("Grades");
		yAxis.setLabel("Number of results");
	}

	@Override
	public void clear() {
		gradeBalanceBarChart.getData().clear();
	}

}
