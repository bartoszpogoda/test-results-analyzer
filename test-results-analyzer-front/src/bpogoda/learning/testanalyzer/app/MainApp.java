package bpogoda.learning.testanalyzer.app;

import java.io.IOException;

import bpogoda.learning.testanalyzer.app.view.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane mainLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Test Results Analyzer");
		this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MainLayout.fxml"));
			mainLayout = (BorderPane) loader.load();
			
			MainController mainController = (MainController) loader.getController();
			mainController.setStage(primaryStage);
			
			Scene scene = new Scene(mainLayout);
	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
