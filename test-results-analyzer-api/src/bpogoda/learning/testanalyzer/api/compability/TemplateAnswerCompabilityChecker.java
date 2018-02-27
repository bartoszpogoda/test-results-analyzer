package bpogoda.learning.testanalyzer.api.compability;

import java.util.List;

import bpogoda.learning.testanalyzer.api.model.answered.AnsweredTest;
import bpogoda.learning.testanalyzer.api.model.template.TestTemplate;

/**
 * Provides operation to check compability of test template and answered tests.
 * 
 * @author Student225988
 *
 */
public class TemplateAnswerCompabilityChecker {
	/**
	 * Checks if given test template and list of answered tests are compatible.
	 * 
	 * @param testTemplate template to check
	 * @param answeredTests list of answered tests to check
	 * @return true if objects are compatible, false otherwise
	 */
	public boolean check(TestTemplate testTemplate, List<AnsweredTest> answeredTests) {
		int numberOfQuestionsInTemplate = testTemplate.getItems().size();
		
		for(AnsweredTest answeredTest : answeredTests) {
			if(answeredTest.getAnswers().size() != numberOfQuestionsInTemplate) {
				return false;
			}
		}
		
		return true;
	}
}
