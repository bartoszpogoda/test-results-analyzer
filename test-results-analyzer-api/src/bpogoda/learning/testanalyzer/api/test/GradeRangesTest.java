package bpogoda.learning.testanalyzer.api.test;

import static org.junit.Assert.*;

import org.junit.Test;

import bpogoda.learning.testanalyzer.api.stats.Grade;
import bpogoda.learning.testanalyzer.api.stats.GradeRanges;

public class GradeRangesTest {

	@Test
	public void shouldReturnCorrectGradesForScore1() {
		
		GradeRanges gradeRanges = new GradeRanges();
		gradeRanges.setRange(Grade.GR_3, 1);
		gradeRanges.setRange(Grade.GR_3_5, 5);
		
		Grade gradeForScore = gradeRanges.getGradeForScore(6);
		
		assertEquals(Grade.GR_3_5, gradeForScore);
		
	}
	
	@Test
	public void shouldReturnCorrectGradesForScore2() {
		
		GradeRanges gradeRanges = new GradeRanges();
		gradeRanges.setRange(Grade.GR_4, 10);
		gradeRanges.setRange(Grade.GR_3_5, 7);
		
		Grade gradeForScore = gradeRanges.getGradeForScore(8);
		
		assertEquals(Grade.GR_3_5, gradeForScore);
		
	}
	
	@Test
	public void shouldReturnCorrectGradesForScore3() {
		
		GradeRanges gradeRanges = new GradeRanges();
		gradeRanges.setRange(Grade.GR_3, 5);
		gradeRanges.setRange(Grade.GR_5, 5);
		
		Grade gradeForScore = gradeRanges.getGradeForScore(5);
		
		assertEquals(Grade.GR_5, gradeForScore);
		
	}

}
