package bpogoda.learning.testanalyzer.app.view;

import java.util.List;

import bpogoda.learning.testanalyzer.api.model.answered.AnsweredTest;
import bpogoda.learning.testanalyzer.api.model.template.TestTemplate;

public interface TestDataHandlingController {
	public void processTestData(TestTemplate testTemplate, List<AnsweredTest> answeredTests);
	
	public void clear();
}
