package bpogoda.learning.testanalyzer.app.view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bpogoda.learning.testanalyzer.api.model.answered.AnsweredTest;
import bpogoda.learning.testanalyzer.api.model.template.TestTemplate;
import bpogoda.learning.testanalyzer.app.MainApp;
import bpogoda.learning.testanalyzer.app.util.TestAnswersCsvFileReader;
import bpogoda.learning.testanalyzer.app.util.TestTemplateXmlFileReader;
import bpogoda.learning.testanalyzer.app.view.histogram.HistogramController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainController {

	@FXML
	private BorderPane borderPane;

	@FXML
	private TabPane tabPane;

	@FXML
	private MenuItem loadTemplate, loadAnswers;

	@FXML
	private Text templateFileTxt, answersFileTxt;

	private TestTemplate testTemplate;

	private List<AnsweredTest> answeredTests;

	private List<TestDataHandlingController> testDataHandlingControllers;

	@FXML
	public void initialize() throws IOException {
		testDataHandlingControllers = new ArrayList<>();
		
		initHistogramPane();
		initGradeBalancePane();
		disableTabPanes();
	}

	/**
	 * Called when the user clicks on the load template menu item.
	 */
	@FXML
	private void handleLoadTemplate() {
		resetUponNewTemplateSelection();

		Stage stage = new Stage();

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Template File");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML", "*.xml*"));

		File selectedFile = fileChooser.showOpenDialog(stage);

		if (selectedFile != null) {

			try {
				TestTemplateXmlFileReader templateReader = new TestTemplateXmlFileReader();
				testTemplate = templateReader.read(selectedFile);

				templateFileTxt.setText(selectedFile.getName());
				loadAnswers.setDisable(false);

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Template loaded succesfully");
				alert.setContentText("Now you can load file containing answers.");

				alert.showAndWait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Called when the user clicks on the load answers menu item.
	 */
	@FXML
	private void handleLoadAnswers() {
		Stage stage = new Stage();

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open File Containing Answers");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv*"));

		File selectedFile = fileChooser.showOpenDialog(stage);

		if (selectedFile != null) {
			try {
				TestAnswersCsvFileReader answersReader = new TestAnswersCsvFileReader();
				answeredTests = answersReader.read(selectedFile);

				answersFileTxt.setText(selectedFile.getName());

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Answers loaded succesfully.");

				alert.show();
				processTestResults();
				enableTabPanes();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void resetUponNewTemplateSelection() {
		templateFileTxt.setText("Not selected");
		answersFileTxt.setText("Not selected");
		loadAnswers.setDisable(true);

		testDataHandlingControllers.forEach((controller) -> controller.clear());
	}

	private void processTestResults() {
		testDataHandlingControllers.forEach((controller) -> controller.processTestData(testTemplate, answeredTests));
	}

	public void initHistogramPane() throws IOException {
		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(MainApp.class.getResource("view/histogram/HistogramPane.fxml"));
		AnchorPane histogramPane = (AnchorPane) loader.load();
		testDataHandlingControllers.add(loader.getController());

		addDynamicPane("Histogram", histogramPane);
	}
	
	public void initGradeBalancePane() throws IOException {
		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(MainApp.class.getResource("view/grades/GradeBalancePane.fxml"));
		AnchorPane gradeBalancePane = (AnchorPane) loader.load();
		testDataHandlingControllers.add(loader.getController());

		addDynamicPane("Grade balance", gradeBalancePane);
	}

	public void addDynamicPane(String title, Pane pane) {
		tabPane.getTabs().add(new Tab(title, pane));
	}
	
	public void disableTabPanes() {
		ObservableList<Tab> tabs = tabPane.getTabs();
		for(int i = 1; i < tabs.size() ; i++) {
			tabs.get(i).setDisable(true);
		}
	}
	
	public void enableTabPanes() {
		ObservableList<Tab> tabs = tabPane.getTabs();
		for(int i = 1; i < tabs.size() ; i++) {
			tabs.get(i).setDisable(false);
		}
	}

}
