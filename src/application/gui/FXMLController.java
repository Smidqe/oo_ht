package application.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.control.MenuButton;

public class FXMLController implements Initializable{
	@FXML
	private Button button_save;
	@FXML
	private Button button_load;
	@FXML
	private Label label_error;
	@FXML
	private TextArea text_area_log;
	@FXML
	private MenuButton button_menu;
	@FXML
	private MenuItem menu_button_settings;
	@FXML
	private WebView screen_web;

	// Event Listener on Button[#button_save].onAction
	@FXML
	public void action_save_to_file(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#button_load].onAction
	@FXML
	public void action_load_from_file(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on MenuItem.onAction
	@FXML
	public void action_close(ActionEvent event) {
		Platform.exit();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.setImplicitExit(false);
		final WebEngine engine = screen_web.getEngine();
		engine.load(getClass().getResource("html/index.html").toExternalForm());
	}
	
}
