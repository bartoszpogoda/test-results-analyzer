package bpogoda.learning.testanalyzer.api.model.template;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents template of the test. It aggregates test template
 * items.
 * 
 * @author Student225988
 *
 */
public class TestTemplate {
	private List<TestTemplateItem> items;

	public TestTemplate() {
		items = new LinkedList<>();
	}

	public List<TestTemplateItem> getItems() {
		return items;
	}

	public void setItems(List<TestTemplateItem> items) {
		this.items = items;
	}
}
