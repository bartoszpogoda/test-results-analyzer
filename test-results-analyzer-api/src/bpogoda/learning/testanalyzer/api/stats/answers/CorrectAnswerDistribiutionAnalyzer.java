package bpogoda.learning.testanalyzer.api.stats.answers;

import java.util.ArrayList;
import java.util.List;

import bpogoda.learning.testanalyzer.api.model.answered.AnsweredTest;
import bpogoda.learning.testanalyzer.api.model.template.TestTemplate;

/**
 * Provides operation to analyze distribution of correct answers in tests.
 * 
 * @author Student225988
 *
 */
public class CorrectAnswerDistribiutionAnalyzer {
	/**
	 * For given template and list of answered tests it calculates number of correct answers for each question.
	 * 
	 * @param testTemplate 
	 * @param answeredTests
	 * @return an ordered list containing sequence of numbers of correct answers for each question
	 */
	public List<Integer> analyze(TestTemplate testTemplate, List<AnsweredTest> answeredTests) {
		List<Integer> correctAnswersForQuestions = new ArrayList<>();
		
		for(int i = 0 ; i < testTemplate.getItems().size() ; i++) {
			char correctAnswer = testTemplate.getItems().get(i).getCorrectAnswer();
			
			int numberOfCorrectAnswers = 0;
			for(AnsweredTest answeredTest : answeredTests) {
				if(answeredTest.getAnswers().get(i).charValue() == correctAnswer) {
					numberOfCorrectAnswers++;
				}
			}
			
			correctAnswersForQuestions.add(numberOfCorrectAnswers);
		}
		
		return correctAnswersForQuestions;
	}
}
