package application.gui.items;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import application.files.log;
import application.items.*;
import application.types.o_item;

public class gui_items implements Initializable{
	@FXML
	private ComboBox<String> cmb_items;
	@FXML
	private TextField tf_name, tf_size, tf_weight;
	@FXML
	private Slider sl_break_chance;
	@FXML
	private Label lbl_break, lbl_information;
	@FXML
	private RadioButton rb_break;
	
	@FXML
	private Button btn_create, btn_remove, btn_update;
	
	private items __info;
	private log __log;
	
	
	@FXML
	public void show_break_chance(ActionEvent e)
	{
		sl_break_chance.setVisible(rb_break.isSelected());
		lbl_break.setVisible(rb_break.isSelected());
		
		sl_break_chance.setDisable(!rb_break.isSelected());
		lbl_break.setDisable(!rb_break.isSelected());
	}
	
	
	@FXML
	public boolean all_information_filled()
	{
		return (!tf_name.getText().isEmpty() && !tf_size.getText().isEmpty() && !tf_weight.getText().isEmpty());
	}
	
	@FXML
	public void remove(ActionEvent e)
	{
		if (cmb_items.getValue() == null)
			return;
		
		//refresh the item list after removing one.
		__info.list().remove(cmb_items.getItems().indexOf(cmb_items.getValue()));
		__info.populate(cmb_items, true);
		
		//save the information to the file. Gotta keep it updated.
		try {
			__info.save(true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		if (cmb_items.getValue() != null)
			cmb_items.getSelectionModel().clearSelection();
	}
	
	@FXML
	public void save(ActionEvent e)
	{
		if (all_information_filled())
		{
			o_item f;
			
			//if we are creating a new item create a new one, otherwise load the existing item
			if (cmb_items.getValue() != null)
				f = __info.list().get(cmb_items.getItems().indexOf(cmb_items.getValue()));
			else
				f = new o_item();
			
			f.name = tf_name.getText();
			f.weight = Float.parseFloat(tf_weight.getText());
			f.breakable = rb_break.isSelected();
			
			if (f.breakable)
				f.durability = (float) sl_break_chance.getValue();
			
			//split the size text to array of 3 and turn them into integers.
			String[] __t = tf_size.getText().split("\\*");
			for (int i = 0 ; i < f.size.length; i++)
				f.size[i] = Integer.parseInt(__t[i].trim());

			//check if we are not pushing the update button, if we are don't create a new item!
			if (!e.getTarget().toString().equals("Button[id=btn_update, styleClass=button]'Päivitä'"))
				__info.create(f);
		
			//add a log entry.
			__log.entry("ESINEET: Uusi esine luotu/päivitetty: " + f.toString(), true);
			
			//refill the combobox with items.
			__info.populate(cmb_items, true);
			
			
			//save the items again.
			try {
				__info.save(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			if (cmb_items.getValue() != null)
				cmb_items.getSelectionModel().clearSelection();
		}
	}
	
	//loads the item information and inserts them to the corresponding slots.
	@FXML 
	public void load_item_information(ActionEvent e)
	{
		if (cmb_items.getValue() == null)
			return;
		
		//find the item.
		o_item f = __info.list().get(cmb_items.getItems().indexOf(cmb_items.getValue()));
		
		//set the text's
		tf_name.setText(f.name);
		tf_size.setText(f.size[0] + "*" + f.size[1] + "*" + f.size[2]);
		tf_weight.setText(Float.toString(f.weight));
		
		//set the breakable and the slider to the corresponding values
		rb_break.setSelected(f.breakable);
		sl_break_chance.setDisable(!rb_break.isSelected());
		sl_break_chance.setValue((double) f.durability);
        lbl_break.setText("Hajoamisen mahdollisuus: " + String.format("%.2f", sl_break_chance.getValue()));

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		__info = items.getInstance();
		__info.populate(cmb_items, false);
		
		__log = log.getInstance();
		
		//add a listener to the break chance slider.
        sl_break_chance.valueProperty().addListener(new ChangeListener<Object>() 
        {
                @Override
                public void changed(ObservableValue<?> arg0, Object o1, Object o2)
                {
                    lbl_break.setText("Hajoamisen mahdollisuus: " + String.format("%.2f", sl_break_chance.getValue()));
                }
                
        });
        
        //add a listener to the combobox, everytime it's value changes you get it's properties.
        cmb_items.valueProperty().addListener(new ChangeListener<Object>() 
        {
                @Override
                public void changed(ObservableValue<?> arg0, Object o1, Object o2)
                {
                    load_item_information(new ActionEvent());
                }
                
        });
	}
	
}
