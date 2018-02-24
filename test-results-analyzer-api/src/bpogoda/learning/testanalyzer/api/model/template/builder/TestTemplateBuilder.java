package bpogoda.learning.testanalyzer.api.model.template.builder;

import bpogoda.learning.testanalyzer.api.model.template.TestTemplate;
import bpogoda.learning.testanalyzer.api.model.template.TestTemplateItem;

/**
 * Builder class responsible for handling construction of TestTemplate objects.
 * 
 * @author Student225988
 *
 */
public class TestTemplateBuilder {
	private TestTemplate builtTestTemplate;

	/**
	 * A default constructor.
	 */
	public TestTemplateBuilder() {
		this.builtTestTemplate = new TestTemplate();
	}

	/**
	 * Adds new question item constructed with specified question string.
	 * 
	 * @param question
	 *            question string to create question item with
	 * @return the builder instance
	 */
	public TestTemplateBuilder addQuestionItem(String question) {
		builtTestTemplate.getItems().add(new TestTemplateItem(question));

		return this;
	}

	/**
	 * Appends answer item to the latest added question item constructed with
	 * given character key and answer string.
	 * 
	 * @param key
	 *            key
	 * @param answer
	 *            answer
	 * @return the builder instance
	 */
	public TestTemplateBuilder appendAnswerToLatestQuestion(char key, String answer) {
		builtTestTemplate.getItems().get(builtTestTemplate.getItems().size() - 1).getAnswers().put(key, answer);

		return this;
	}

	/**
	 * Sets the correct answer for the latest added question.
	 * 
	 * @param correctKey
	 *            the correct key
	 * @return the builder instance
	 */
	public TestTemplateBuilder setCorrectAnswerToLatestQuestion(char correctKey) {
		builtTestTemplate.getItems().get(builtTestTemplate.getItems().size() - 1).setCorrectAnswer(correctKey);

		return this;
	}

	/**
	 * Builds an object constructed using previously invoked operations. The
	 * builder then forgets returned instance.
	 * 
	 * @return built TestTemplate object
	 */
	public TestTemplate build() {
		TestTemplate builtTestTemplateToReturn = builtTestTemplate;
		builtTestTemplate = new TestTemplate();

		return builtTestTemplateToReturn;
	}

}
