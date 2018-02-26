package bpogoda.learning.testanalyzer.api.stats;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GradeRanges {
	
	private Map<Grade, Integer> gradeRanges;
	
	public GradeRanges() {
		this.gradeRanges = new TreeMap<>();
	}
	
	public void setRange(Grade grade, int scoreLowerBound) {
		gradeRanges.put(grade, scoreLowerBound);
	}
	
	public int getLowerBound(Grade grade) {
		return gradeRanges.get(grade);
	}
	
	public Grade getGradeForScore(int score) {
		
		Set<Grade> orderedGrades = gradeRanges.keySet();
		
		Grade currentBestGrade = Grade.GR_2;
		
		for(Grade grade : orderedGrades) {
			if(gradeRanges.get(grade) <= score) {
				currentBestGrade = grade;
			} else {
				break;
			}
		}
		
		return currentBestGrade;
	}
}
