package com.boxfox.music.controller;

import java.awt.RenderingHints.Key;
import java.net.URL;
import java.util.ResourceBundle;

import com.boxfox.music.Event;
import com.boxfox.music.MainApplication;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MainFormController extends BaseController {

	@FXML
	private Pane p_main;
	@FXML
	private Label label;
	@FXML
	private TextField top;

	public MainFormController() {
		super(MainFormController.class);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		label.setMaxWidth(Double.MAX_VALUE);
		label.setAlignment(Pos.CENTER);
		MainApplication.musicManager.registerController(this);
		top.setOnKeyTyped(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent key) {
				if (key.getCharacter().equals(";"))
	            {
					String text = top.getText();
					top.setText("");
					MainApplication.musicManager.getYoutube(text);
	            }
			}
		});
	}

	public WebEngine useWebView() {
		WebView view = new WebView();
		p_main.getChildren().add(view);
		view.setVisible(false);
		WebEngine engine = view.getEngine();
		engine.setJavaScriptEnabled(true);
		return engine;
	}

	@Override
	public void eventPerformed(Event e) {
		label.setText(e.getArg().toString());
	}
	
	public void setDisable(boolean check){
		top.setDisable(check);
	}

}