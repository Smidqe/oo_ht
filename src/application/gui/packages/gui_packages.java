package application.gui.packages;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

import application.items.items;

public class gui_packages implements Initializable{
	@FXML
	private Button btn_create_item, btn_cancel, btn_create, btn_info;
	@FXML
	private ComboBox<String> cmb_item, cmb_class, cmb_from, cmb_to;

	private items __items;
	
	@FXML
	private boolean filled()
	{
		
		
		return false;
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		__items = items.getInstance();
		__items.populate(cmb_item, true);
		
		for (int i = 0; i < 3; i++)
			cmb_class.getItems().add(String.valueOf(i + 1));
	}
	
}
