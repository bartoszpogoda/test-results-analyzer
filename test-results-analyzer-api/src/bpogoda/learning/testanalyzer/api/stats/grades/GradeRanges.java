package bpogoda.learning.testanalyzer.api.stats.grades;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Contains information about possible grades and their lower point bounds. 
 * 
 * @author Student225988
 *
 */
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
	
	/**
	 * Returns grade that applies to given score.
	 * 
	 * @param score
	 * @return grade for given score
	 */
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
