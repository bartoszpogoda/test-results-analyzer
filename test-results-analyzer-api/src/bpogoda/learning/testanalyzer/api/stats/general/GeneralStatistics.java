package bpogoda.learning.testanalyzer.api.stats.general;

/**
 * Represents pack of some general statistics for test result.
 * 
 * @author Student225988
 *
 */
public class GeneralStatistics {
	private int numberOfQuestions;
	private int numberOfParticipants;
	private double averageScore;

	public GeneralStatistics(int numberOfQuestions, int numberOfParticipants, double averageScore) {
		this.numberOfQuestions = numberOfQuestions;
		this.numberOfParticipants = numberOfParticipants;
		this.averageScore = averageScore;
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public int getNumberOfParticipants() {
		return numberOfParticipants;
	}

	public double getAverageScore() {
		return averageScore;
	}

}
