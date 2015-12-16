package application.gui.items;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

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
	private Label lbl_break;
	@FXML
	private RadioButton rb_break;
	
	private items __info;
	
	
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
		return (tf_name.getText().isEmpty() && tf_size.getText().isEmpty() && tf_weight.getText().isEmpty());
	}
	
	@FXML
	public void save()
	{
		if (all_information_filled())
		{
			o_item f = __info.__items.get(cmb_items.getItems().indexOf(cmb_items.getValue()));
			
			f.name = tf_name.getText();
			f.weight = Integer.parseInt(tf_weight.getText());
			
			
		}
	}
	
	@FXML
	public void load_item_information(ActionEvent e)
	{
		o_item f = __info.__items.get(cmb_items.getItems().indexOf(cmb_items.getValue()));
		
		tf_name.setText(f.name);
		tf_size.setText(f.size.toString());
		tf_weight.setText(Integer.toString(f.weight));
		
		rb_break.setSelected(f.breakable);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		__info = items.getInstance();
		
		
		
        sl_break_chance.valueProperty().addListener(new ChangeListener<Object>() 
        {
                @Override
                public void changed(ObservableValue<?> arg0, Object o1, Object o2)
                {
                    lbl_break.setText("Hajoamisen mahdollisuus: " + String.format("%.2f", sl_break_chance.getValue()));
                }
                
        });
        
        
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
