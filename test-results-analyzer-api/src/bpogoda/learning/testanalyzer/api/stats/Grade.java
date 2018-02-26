package bpogoda.learning.testanalyzer.api.stats;

public enum Grade {
	GR_2("2.0"), GR_3("3.0"), GR_3_5("3.5"), GR_4("4.0"), GR_4_5("4.5"), GR_5("5"), GR_5_5("5.5");
	
	private String value;

	Grade(String value) {
		this.value = value;
		
	}
	
	@Override
	public String toString() {
		return value;
	}
	
}
