package bpogoda.learning.testanalyzer.app.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import bpogoda.learning.testanalyzer.api.model.answered.AnsweredTest;

public class TestAnswersCsvFileReader {
	public List<AnsweredTest> read(File csvFile) throws SAXException, IOException, ParserConfigurationException {
		List<AnsweredTest> answeredTests = new ArrayList<>();

		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {

				AnsweredTest answeredTest = new AnsweredTest();
				String[] answersLine = line.split(cvsSplitBy);

				for(String answer : answersLine) {
					answeredTest.getAnswers().add(answer.charAt(0));
				}
				
				answeredTests.add(answeredTest);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return answeredTests;
	}
}
