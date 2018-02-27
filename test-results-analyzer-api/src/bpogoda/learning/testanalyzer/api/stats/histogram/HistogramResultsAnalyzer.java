package bpogoda.learning.testanalyzer.api.stats.histogram;

import java.util.List;

import bpogoda.learning.testanalyzer.api.model.answered.AnsweredTest;
import bpogoda.learning.testanalyzer.api.model.template.TestTemplate;
import bpogoda.learning.testanalyzer.api.stats.util.AnsweredTestScorer;

/**
 * Class containing operations to generate data for results histogram.
 * 
 * @author Student225988
 *
 */
public class HistogramResultsAnalyzer {

	/**
	 * Generates histogram data for given test template and actual test answers.
	 * The results are percentage and divided into 10 histogram ranges. 0 - 10%,
	 * 10% - 20%, etc.
	 * 
	 * @param testTemplate
	 *            test template to check the answers against
	 * @param answeredTests
	 *            list of answered tests
	 * @return 10-element array consisting of histogram values
	 */
	public int[] generateHistogramData(TestTemplate testTemplate, List<AnsweredTest> answeredTests) {
		int[] histogramResults = new int[10];

		AnsweredTestScorer testScorer = new AnsweredTestScorer();

		int maxPointsToGet = testTemplate.getItems().size();

		for (AnsweredTest answeredTest : answeredTests) {
			int score = testScorer.score(testTemplate, answeredTest);

			double percentageScore = ((double) score / maxPointsToGet) * 100;

			histogramResults[(int) ((percentageScore - 1) / 10)]++;
		}

		return histogramResults;
	}

}
