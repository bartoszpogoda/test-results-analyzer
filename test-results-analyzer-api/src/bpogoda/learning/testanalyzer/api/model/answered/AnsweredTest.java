package bpogoda.learning.testanalyzer.api.model.answered;

import java.util.List;

/**
 * This class represents an ordered list of character answers for the test of
 * choice.
 * 
 * @author Student225988
 *
 */
public class AnsweredTest {
	private List<Character> answers;

	public List<Character> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Character> answers) {
		this.answers = answers;
	}
}
