package application.gui.packages;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import application.files.log;
import application.items.items;
import application.smartpost.smartpost;
import application.storage.storage;
import application.types.o_package;

public class gui_packages implements Initializable{
	@FXML
	private Button btn_create_item, btn_cancel, btn_create, btn_info;
	@FXML
	private ComboBox<String> cmb_item, cmb_class, cmb_from, cmb_to;
	@FXML
	private Label lbl_information;

	private items __items;
	private smartpost __locations;
	private storage __storage;
	@FXML
	private boolean filled()
	{
		return (cmb_item.getValue() != null && cmb_class.getValue() != null && cmb_from.getValue() != null && cmb_to.getValue() != null);
	}
	
	@FXML
	public void show_info()
	{
    	lbl_information.setText("Luokka 1: \n\t- Maksimi etäisyys: 150 km\n\t- Maksimikoko: 10 * 10 * 10\n\t- Maksimipaino 2 kg\n\n"
    						  + "Luokka 2: \n\t- Maksimi etäisyys: 550 km\n\t- Maksimikoko: 20 * 20 * 20\n\t- Maksimipaino 4 kg\n\n"
    						  + "Luokka 3: \n\t- Maksimi etäisyys: 950 km\n\t- Maksimikoko: 30 * 30 * 30\n\t- Maksimipaino 6 kg");

	}
	
	@FXML
	public void create()
	{
		if (!filled())
			return;

        o_package pack = new o_package(__items.find(cmb_item.getValue()), __locations.find(cmb_from.getValue()), __locations.find(cmb_to.getValue()), Integer.parseInt(cmb_class.getValue()));
       
        if (!pack.fits(pack.getItem(), pack.get_from(), pack.get_to(), pack.get_class()))
        {
        	lbl_information.setText("Annettu esine ja paketin luokan määrittelemät rajat eivät ole yhteensopivia, tarkista luokan vaatimukset.");
        	return;
        }
        
        lbl_information.setText("Paketti luotu onnistuneesti.");
        
        cmb_item.getSelectionModel().clearSelection();
        cmb_from.getSelectionModel().clearSelection();
        cmb_to.getSelectionModel().clearSelection();
        cmb_class.getSelectionModel().clearSelection();
        
        
        log.getInstance().entry(0.0, pack, true, "Paketti luotu: " + pack.toString());
		__storage.store(pack);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		lbl_information.setWrapText(true);
		
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
