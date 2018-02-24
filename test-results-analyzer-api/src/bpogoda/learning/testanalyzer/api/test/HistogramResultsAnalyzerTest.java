package bpogoda.learning.testanalyzer.api.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import bpogoda.learning.testanalyzer.api.model.answered.AnsweredTest;
import bpogoda.learning.testanalyzer.api.model.template.TestTemplate;
import bpogoda.learning.testanalyzer.api.model.template.TestTemplateItem;
import bpogoda.learning.testanalyzer.api.stats.HistogramResultsAnalyzer;

public class HistogramResultsAnalyzerTest {
	private HistogramResultsAnalyzer histogramResultsAnalyzer;

	@Before
	public void setUp() {
		histogramResultsAnalyzer = new HistogramResultsAnalyzer();
	}
	
	@Test
	public void shouldScoreProperlyWhenAllAnswersCorrect() {
		// given
		TestTemplate testTemplate = new TestTemplate();
		
		TestTemplateItem testTemplateItem1 = new TestTemplateItem("Abc");
		testTemplateItem1.setCorrectAnswer('a');
		
		TestTemplateItem testTemplateItem2 = new TestTemplateItem("Abc");
		testTemplateItem2.setCorrectAnswer('d');
		
		testTemplate.getItems().addAll(Arrays.asList(testTemplateItem1, testTemplateItem2));
		
		AnsweredTest answeredTest1 = new AnsweredTest();
		answeredTest1.setAnswers(Arrays.asList('a', 'd'));

		AnsweredTest answeredTest2 = new AnsweredTest();
		answeredTest2.setAnswers(Arrays.asList('a', 'd'));

		AnsweredTest answeredTest3 = new AnsweredTest();
		answeredTest3.setAnswers(Arrays.asList('a', 'd'));
		
		List<AnsweredTest> answeredTests =  Arrays.asList(answeredTest1, answeredTest2, answeredTest3);
		
		// when
		int results[] = histogramResultsAnalyzer.generateHistogramData(testTemplate, answeredTests);
		
		// then
		for(int i = 0 ; i < 9 ; i++) {
			assertEquals(0, results[i]);
		}
		assertEquals(3, results[9]);
	}
	
	@Test
	public void shouldScoreProperlyWhenAllAnswersIncorrect() {
		// given
		TestTemplate testTemplate = new TestTemplate();
		
		TestTemplateItem testTemplateItem1 = new TestTemplateItem("Abc");
		testTemplateItem1.setCorrectAnswer('a');
		
		TestTemplateItem testTemplateItem2 = new TestTemplateItem("Abc");
		testTemplateItem2.setCorrectAnswer('d');
		
		testTemplate.getItems().addAll(Arrays.asList(testTemplateItem1, testTemplateItem2));
		
		AnsweredTest answeredTest1 = new AnsweredTest();
		answeredTest1.setAnswers(Arrays.asList('c', 'c'));

		AnsweredTest answeredTest2 = new AnsweredTest();
		answeredTest2.setAnswers(Arrays.asList('c', 'c'));

		AnsweredTest answeredTest3 = new AnsweredTest();
		answeredTest3.setAnswers(Arrays.asList('b', 'b'));
		
		List<AnsweredTest> answeredTests =  Arrays.asList(answeredTest1, answeredTest2, answeredTest3);
		
		// when
		int results[] = histogramResultsAnalyzer.generateHistogramData(testTemplate, answeredTests);
		
		// then
		assertEquals(3, results[0]);
		for(int i = 1 ; i < 10 ; i++) {
			assertEquals(0, results[i]);
		}
	}
}
