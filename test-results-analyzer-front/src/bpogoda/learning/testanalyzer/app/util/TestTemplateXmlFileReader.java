package bpogoda.learning.testanalyzer.app.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import bpogoda.learning.testanalyzer.api.model.template.TestTemplate;
import bpogoda.learning.testanalyzer.api.model.template.builder.TestTemplateBuilder;

public class TestTemplateXmlFileReader {
	public TestTemplate read(File xmlFile) throws SAXException, IOException, ParserConfigurationException {

		TestTemplateBuilder testTemplateBuilder = new TestTemplateBuilder();

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();

		NodeList questionNodeList = doc.getElementsByTagName("question");

		for (int i = 0; i < questionNodeList.getLength(); i++) {

			Node questionNode = questionNodeList.item(i);
			Element questionElement = (Element) questionNode;

			testTemplateBuilder.addQuestionItem(questionElement.getElementsByTagName("title").item(0).getTextContent());

			Node answersNode = questionElement.getElementsByTagName("answers").item(0);
			Element answersElement = (Element) answersNode;

			testTemplateBuilder.setCorrectAnswerToLatestQuestion(
					answersElement.getElementsByTagName("correct").item(0).getTextContent().charAt(0));

			NodeList answerNodeList = answersElement.getElementsByTagName("answer");

			for (int j = 0; j < answerNodeList.getLength(); j++) {
				Node answerNode = answerNodeList.item(j);
				Element answerElement = (Element) answerNode;
				
				char key = answerElement.getElementsByTagName("key").item(0).getTextContent().charAt(0);
				String value = answerElement.getElementsByTagName("value").item(0).getTextContent();
				
				testTemplateBuilder.appendAnswerToLatestQuestion(key, value);
			}

		}
		
		return testTemplateBuilder.build();
	}
}
