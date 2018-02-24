package bpogoda.learning.testanalyzer.api.model.template;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents one entry for the test template, which contains
 * information about the question (String), possible answers and the correct
 * answer.
 * 
 * @author Student225988
 *
 */
public class TestTemplateItem {
	private String question;
	private Map<Character, String> answers;
	private char correctAnswer;

	public TestTemplateItem(String question) {
		this();
		
		this.question = question;
	}
	
	public TestTemplateItem() {
		this.answers = new HashMap<>();
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Map<Character, String> getAnswers() {
		return answers;
	}

	public void setAnswers(Map<Character, String> answers) {
		this.answers = answers;
	}

	public char getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(char correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
}
