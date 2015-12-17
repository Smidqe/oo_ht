package application.gui.packages;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

import application.items.items;
import application.smartpost.smartpost;
import application.storage.storage;
import application.types.o_package;

public class gui_packages implements Initializable{
	@FXML
	private Button btn_create_item, btn_cancel, btn_create, btn_info;
	@FXML
	private ComboBox<String> cmb_item, cmb_class, cmb_from, cmb_to;

	private items __items;
	private smartpost __locations;
	private storage __storage;
	@FXML
	private boolean filled()
	{
		return (cmb_item.getValue() != null && cmb_class.getValue() != null && cmb_from.getValue() != null && cmb_to.getValue() != null);
	}
	
	@FXML
	public void create()
	{
		if (!filled())
			return;

		__storage.store(new o_package(__items.find(cmb_item.getValue()), __locations.find(cmb_from.getValue()), __locations.find(cmb_to.getValue()), Integer.parseInt(cmb_class.getValue())));
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		__items = items.getInstance();
		__items.populate(cmb_item, true);
		
		__storage = storage.getInstance();
		
		__locations = smartpost.getInstance();
		
		__locations.populate(cmb_from, false);
		__locations.populate(cmb_to, false);

		for (int i = 0; i < 3; i++)
			cmb_class.getItems().add(String.valueOf(i + 1));
	}
	
}
