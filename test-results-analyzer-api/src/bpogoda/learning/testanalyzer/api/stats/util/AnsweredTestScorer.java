package bpogoda.learning.testanalyzer.api.stats.util;

import bpogoda.learning.testanalyzer.api.model.answered.AnsweredTest;
import bpogoda.learning.testanalyzer.api.model.template.TestTemplate;

/**
 * Class that provides operation to compare answered test against template in
 * order to provide score.
 * 
 * @author Student225988
 *
 */
public class AnsweredTestScorer {
	/**
	 * Scores given test based on answer comparison with template's correct
	 * answers. Correct answer is +1, Incorrect answer +0.
	 * 
	 * @param testTemplate template to compare the answered test with
	 * @param answeredTest answered test to check
	 * @return the score
	 */
	public int score(TestTemplate testTemplate, AnsweredTest answeredTest) {
		int score = 0;

		for (int i = 0; i < answeredTest.getAnswers().size(); i++) {
			if (answeredTest.getAnswers().get(i).equals(testTemplate.getItems().get(i).getCorrectAnswer())) {
				score++;
			}
		}

		return score;
	}
}
