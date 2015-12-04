package application.gui.packages;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;

public class gui_packages implements Initializable{
	@FXML
	private Button btn_create_item, btn_cancel, btn_create, btn_info;
	
	@FXML
	public void gui_items_open()
	{
		try {
			Stage stage = new Stage();
	        Parent root = FXMLLoader.load(getClass().getResource("/application/gui/packages/gui_packages.fxml"));
	        
	        Scene scene = new Scene(root);
	        
	        //stage.initStyle(StageStyle.UNDECORATED);
	        stage.setResizable(false);
	        stage.setScene(scene);   
	        stage.show();
	        
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.setImplicitExit(false);
	}
	
}
