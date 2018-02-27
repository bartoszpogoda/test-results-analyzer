package bpogoda.learning.testanalyzer.api.stats.general;

import java.util.ArrayList;
import java.util.List;

import bpogoda.learning.testanalyzer.api.model.answered.AnsweredTest;
import bpogoda.learning.testanalyzer.api.model.template.TestTemplate;
import bpogoda.learning.testanalyzer.api.stats.util.AnsweredTestScorer;

/**
 * Provides operation to obtain some general statistics on test results.
 * 
 * @author Student225988
 *
 */
public class GeneralStatisticsAnalyzer {
	
	/**
	 * Calculates and returns some basic statistics for given test template and list of answered tests.
	 * 
	 * @param testTemplate
	 * @param answeredTests
	 * @return
	 */
	public GeneralStatistics analyze(TestTemplate testTemplate, List<AnsweredTest> answeredTests) {
		List<Integer> scores = new ArrayList<Integer>();
		
		int numberOfQuestions = testTemplate.getItems().size();
		int numberOfParticipants = answeredTests.size();
		
		AnsweredTestScorer answeredTestScorer = new AnsweredTestScorer();
		for(AnsweredTest answeredTest : answeredTests) {
			scores.add(answeredTestScorer.score(testTemplate, answeredTest));
		}
		
		int sumOfScores = 0;
		for(Integer score : scores) {
			sumOfScores += score;
		}
		
		return new GeneralStatistics(numberOfQuestions, numberOfParticipants, (double)sumOfScores/numberOfParticipants);
	}
}
