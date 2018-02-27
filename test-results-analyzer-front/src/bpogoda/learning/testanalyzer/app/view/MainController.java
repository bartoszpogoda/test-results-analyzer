package bpogoda.learning.testanalyzer.app.view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bpogoda.learning.testanalyzer.api.compability.TemplateAnswerCompabilityChecker;
import bpogoda.learning.testanalyzer.api.model.answered.AnsweredTest;
import bpogoda.learning.testanalyzer.api.model.template.TestTemplate;
import bpogoda.learning.testanalyzer.app.MainApp;
import bpogoda.learning.testanalyzer.app.util.TestAnswersCsvFileReader;
import bpogoda.learning.testanalyzer.app.util.TestTemplateXmlFileReader;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

	@FXML
	private ImageView imageAccent;

	private TestTemplate testTemplate;

	private List<AnsweredTest> answeredTests;

	private List<TestDataHandlingController> testDataHandlingControllers;

	private Stage primaryStage;

	@FXML
	public void initialize() throws IOException {
		testDataHandlingControllers = new ArrayList<>();

		imageAccent.setImage(new Image(MainApp.class.getResourceAsStream("icon.png")));

		initGeneralStatisticsPane();
		initHistogramPane();
		initGradeBalancePane();
		initCorrectAnswerDistributionPane();
		disableTabPanes();
	}

	@FXML
	private void quit() {
		this.primaryStage.close();
	}

	@FXML
	private void showAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText("Test results analyzer");
		alert.setContentText("Created by bpogoda for Advanced programming in Java course @PWR.");

		alert.showAndWait();
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

				if (validateInputFiles()) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText("Answers loaded succesfully.");

					alert.show();
					processTestResults();
					enableTabPanes();
				} else {
					resetUponNewTemplateSelection();

					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Compability error");
					alert.setHeaderText(
							"List of loaded answered tests doesn't match with test template. Please provide proper input.");

					alert.show();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private boolean validateInputFiles() {
		TemplateAnswerCompabilityChecker compabilityChecker = new TemplateAnswerCompabilityChecker();

		return compabilityChecker.check(testTemplate, answeredTests);
	}

	public void resetUponNewTemplateSelection() {
		templateFileTxt.setText("Not selected");
		answersFileTxt.setText("Not selected");
		loadAnswers.setDisable(true);

		testDataHandlingControllers.forEach((controller) -> controller.clear());

		tabPane.getSelectionModel().select(0);
		disableTabPanes();
	}

	private void processTestResults() {
		testDataHandlingControllers.forEach((controller) -> controller.processTestData(testTemplate, answeredTests));

	}

	private void initGeneralStatisticsPane() throws IOException {
		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(MainApp.class.getResource("view/general/GeneralStatisticsPane.fxml"));
		AnchorPane generalStatisticsPane = (AnchorPane) loader.load();
		testDataHandlingControllers.add(loader.getController());

		addDynamicPane("General stats", generalStatisticsPane);
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

	public void initCorrectAnswerDistributionPane() throws IOException {
		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(MainApp.class.getResource("view/answers/CorrectAnswerDistributionPane.fxml"));
		AnchorPane correctAnswerDistributionPane = (AnchorPane) loader.load();
		testDataHandlingControllers.add(loader.getController());

		addDynamicPane("Correct answer distribution", correctAnswerDistributionPane);
	}

	public void addDynamicPane(String title, Pane pane) {
		tabPane.getTabs().add(new Tab(title, pane));
	}

	public void disableTabPanes() {
		ObservableList<Tab> tabs = tabPane.getTabs();
		for (int i = 1; i < tabs.size(); i++) {
			tabs.get(i).setDisable(true);
		}
	}

	public void enableTabPanes() {
		ObservableList<Tab> tabs = tabPane.getTabs();
		for (int i = 1; i < tabs.size(); i++) {
			tabs.get(i).setDisable(false);
		}
	}

	public void setStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}
