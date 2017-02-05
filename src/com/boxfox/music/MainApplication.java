package com.boxfox.music;

import com.boxfox.music.controller.MainFormController;
import com.boxfox.music.manager.MusicManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainApplication extends Application {
	private MainFormController mainController;
	public static MusicManager musicManager;

	@Override
	public void start(Stage primaryStage) {
		musicManager = new MusicManager();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/boxfox/fxml/MainForm.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
	        primaryStage.setAlwaysOnTop(true);
			primaryStage.show();
			primaryStage.setOnCloseRequest((Event) -> {
				System.exit(0);
			});
			mainController = loader.getController();
			musicManager.loadMusic();
			musicManager.registerController(mainController);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void response(Object obj) {
		if (obj instanceof YoutubeMusic) {
			YoutubeMusic yMusic = (YoutubeMusic) obj;
			musicManager.playMusic(yMusic);
		}
	}
}
