package bpogoda.learning.testanalyzer.api.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bpogoda.learning.testanalyzer.api.model.template.TestTemplate;
import bpogoda.learning.testanalyzer.api.model.template.builder.TestTemplateBuilder;

public class TestTemplateBuilderTest {

	private TestTemplateBuilder testTemplateBuilder;

	@Before
	public void setUp() {
		testTemplateBuilder = new TestTemplateBuilder();
	}

	@Test
	public void shouldBuildCorrectObject1() {
		testTemplateBuilder.addQuestionItem("Question number one").appendAnswerToLatestQuestion('a', "Answer a")
				.appendAnswerToLatestQuestion('b', "Answer b");

		TestTemplate builtTestTemplate = testTemplateBuilder.build();

		assertEquals(1, builtTestTemplate.getItems().size());

		assertEquals("Answer a", builtTestTemplate.getItems().get(0).getAnswers().get('a'));
		assertEquals("Answer b", builtTestTemplate.getItems().get(0).getAnswers().get('b'));
	}

	@Test
	public void shouldBuildCorrectObject2() {
		testTemplateBuilder.addQuestionItem("Question number one").appendAnswerToLatestQuestion('a', "1. Answer a")
				.appendAnswerToLatestQuestion('b', "1. Answer b");
		
		testTemplateBuilder.addQuestionItem("Question number two").appendAnswerToLatestQuestion('a', "2. Answer a")
		.appendAnswerToLatestQuestion('b', "2. Answer b");

		TestTemplate builtTestTemplate = testTemplateBuilder.build();

		assertEquals(2, builtTestTemplate.getItems().size());

		assertEquals(2, builtTestTemplate.getItems().get(0).getAnswers().size());
		assertEquals(2, builtTestTemplate.getItems().get(1).getAnswers().size());
		
		assertEquals("1. Answer a", builtTestTemplate.getItems().get(0).getAnswers().get('a'));
		assertEquals("1. Answer b", builtTestTemplate.getItems().get(0).getAnswers().get('b'));
		
		assertEquals("2. Answer a", builtTestTemplate.getItems().get(1).getAnswers().get('a'));
		assertEquals("2. Answer b", builtTestTemplate.getItems().get(1).getAnswers().get('b'));
	}

}
