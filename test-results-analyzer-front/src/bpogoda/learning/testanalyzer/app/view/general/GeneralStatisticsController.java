package bpogoda.learning.testanalyzer.app.view.general;

import java.util.List;

import bpogoda.learning.testanalyzer.api.model.answered.AnsweredTest;
import bpogoda.learning.testanalyzer.api.model.template.TestTemplate;
import bpogoda.learning.testanalyzer.api.stats.general.GeneralStatistics;
import bpogoda.learning.testanalyzer.api.stats.general.GeneralStatisticsAnalyzer;
import bpogoda.learning.testanalyzer.app.view.TestDataHandlingController;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class GeneralStatisticsController implements TestDataHandlingController{
	
	@FXML
	private Text numberOfQuestionsTxt, numberOfParticipantsTxt, averageScoreTxt;
	
	@Override
	public void processTestData(TestTemplate testTemplate, List<AnsweredTest> answeredTests) {
			GeneralStatisticsAnalyzer generalStatisticsAnalyzer = new GeneralStatisticsAnalyzer();
			
			GeneralStatistics generalStatistics = generalStatisticsAnalyzer.analyze(testTemplate, answeredTests);
	
			numberOfQuestionsTxt.setText(Integer.toString(generalStatistics.getNumberOfQuestions()));
			numberOfParticipantsTxt.setText(Integer.toString(generalStatistics.getNumberOfParticipants()));
			averageScoreTxt.setText(String.format("%.1f", generalStatistics.getAverageScore()));
	}

	@Override
	public void clear() {
		
	}
	
}
