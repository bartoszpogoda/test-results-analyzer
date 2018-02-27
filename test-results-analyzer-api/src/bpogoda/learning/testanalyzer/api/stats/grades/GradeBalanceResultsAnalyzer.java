package bpogoda.learning.testanalyzer.api.stats.grades;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import bpogoda.learning.testanalyzer.api.model.answered.AnsweredTest;
import bpogoda.learning.testanalyzer.api.model.template.TestTemplate;
import bpogoda.learning.testanalyzer.api.stats.util.AnsweredTestScorer;

/**
 * Provides operation to analyze balance of received grades.
 * 
 * @author Student225988
 *
 */
public class GradeBalanceResultsAnalyzer {
	/**
	 * Calculates and returns mapping of grades to their scores - number of occurrences in test results.
	 * 
	 * @param gradeRanges
	 * @param testTemplate
	 * @param answeredTests
	 * @return
	 */
	public Map<Grade, Integer> calculateGradeScores(GradeRanges gradeRanges, TestTemplate testTemplate, List<AnsweredTest> answeredTests) {
		Map<Grade, Integer> gradeScores = new TreeMap<>();
		
		for(Grade possibleGrade : Grade.values()) {
			gradeScores.put(possibleGrade, 0);
		}
		
		AnsweredTestScorer testScorer = new AnsweredTestScorer();
		
		for (AnsweredTest answeredTest : answeredTests) {
			int score = testScorer.score(testTemplate, answeredTest);

			Grade receivedGrade = gradeRanges.getGradeForScore(score);
			
			gradeScores.put(receivedGrade, gradeScores.get(receivedGrade) + 1);
		}
		
		return gradeScores;
	}
}
