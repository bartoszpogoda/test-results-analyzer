package bpogoda.learning.testanalyzer.api.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import bpogoda.learning.testanalyzer.api.model.answered.AnsweredTest;
import bpogoda.learning.testanalyzer.api.model.template.TestTemplate;
import bpogoda.learning.testanalyzer.api.model.template.TestTemplateItem;
import bpogoda.learning.testanalyzer.api.stats.AnsweredTestScorer;

public class AnsweredTestScorerTest {

	private AnsweredTestScorer answeredTestScorer;

	@Before
	public void setUp() {
		answeredTestScorer = new AnsweredTestScorer();
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
		
		AnsweredTest answeredTest = new AnsweredTest();
		answeredTest.setAnswers(Arrays.asList('a', 'd'));
		
		// when
		int score = answeredTestScorer.score(testTemplate, answeredTest);
		
		// then
		assertEquals(2, score);
	}
	
	@Test
	public void shouldScoreProperlyWhenAnswersPartiallyCorrect() {
		// given
		TestTemplate testTemplate = new TestTemplate();
		
		TestTemplateItem testTemplateItem1 = new TestTemplateItem("Abc");
		testTemplateItem1.setCorrectAnswer('a');
		
		TestTemplateItem testTemplateItem2 = new TestTemplateItem("Abc");
		testTemplateItem2.setCorrectAnswer('d');
		
		testTemplate.getItems().addAll(Arrays.asList(testTemplateItem1, testTemplateItem2));
		
		AnsweredTest answeredTest = new AnsweredTest();
		answeredTest.setAnswers(Arrays.asList('d', 'd'));
		
		// when
		int score = answeredTestScorer.score(testTemplate, answeredTest);
		
		// then
		assertEquals(1, score);
	}
	
	@Test
	public void shouldScoreProperlyWhenAnswersIncorrect() {
		// given
		TestTemplate testTemplate = new TestTemplate();
		
		TestTemplateItem testTemplateItem1 = new TestTemplateItem("Abc");
		testTemplateItem1.setCorrectAnswer('a');
		
		TestTemplateItem testTemplateItem2 = new TestTemplateItem("Abc");
		testTemplateItem2.setCorrectAnswer('d');
		
		testTemplate.getItems().addAll(Arrays.asList(testTemplateItem1, testTemplateItem2));
		
		AnsweredTest answeredTest = new AnsweredTest();
		answeredTest.setAnswers(Arrays.asList('b', 'b'));
		
		// when
		int score = answeredTestScorer.score(testTemplate, answeredTest);
		
		// then
		assertEquals(0, score);
	}

}
