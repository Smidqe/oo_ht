package application.gui.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import application.files.log;
import application.smartpost.smartpost;
import application.storage.storage;
import application.types.o_log_struct;
import application.types.o_package;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class gui_main implements Initializable{
	@FXML
	private Button btn_save, btn_load, btn_main_reset, btn_packages, btn_items, btn_add_route, btn_send, btn_add;
	@FXML
	private Label label_error;
	@FXML
	private TextArea text_area_log;
	@FXML
	private WebView screen_web;
	@FXML
	private ComboBox<String> cmb_post_offices, cmb_packages;
	@FXML
	private TableView<o_log_struct> tbl_log;
	@FXML
	private TableColumn<o_log_struct, String> tb_time, tb_from, tb_to, tb_text;
	
	//all necessary variables that are included in this application and not in JDK or any other library.
	private smartpost __smartpost;
	private WebEngine __engine;
	private log __log;
	private storage __storage;
	
	@FXML
	public void gui_shipments_open()
	{
		__log.entry("Pakettienhallinta avattu.", true);
		try {
			Stage stage = new Stage(); //same structure as in the main.java
	        Parent root = FXMLLoader.load(getClass().getResource("/application/gui/packages/gui_packages.fxml"));
	        
	        Scene scene = new Scene(root);
	        
	        //stage.initStyle(StageStyle.UNDECORATED);
	        stage.setResizable(false); //don't allow resizing on minor windows.
	        stage.setScene(scene);   
	        stage.show();
	        
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void gui_items_open()
	{
		__log.entry("Esineidenhallinta avattu.", true);
		try {
			Stage stage = new Stage();
	        Parent root = FXMLLoader.load(getClass().getResource("/application/gui/items/gui_items.fxml"));
	        
	        Scene scene = new Scene(root);
	        
	        //stage.initStyle(StageStyle.UNDECORATED);
	        stage.setResizable(false);
	        stage.setScene(scene);   
	        stage.show();
	        
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void add_post_to_map()
	{
		if (cmb_post_offices.getValue() == null)
			return;

		//set the selected smartpost to the map and clear the selection
		__smartpost.set_on_map(cmb_post_offices.getSelectionModel().getSelectedIndex(), __engine);
		__log.entry("Automaatti: " + cmb_post_offices.getValue() + " lisätty kartalle.", true);
		cmb_post_offices.getSelectionModel().clearSelection();
	}
	
	@FXML
	public void clear()
	{
		__smartpost.clear(__engine);
	}
	
	@FXML
	public void action_send(ActionEvent e)
	{
		o_package __package = __smartpost.send_package(__engine, cmb_packages.getItems().indexOf(cmb_packages.getValue()));
		
		if (__package == null)
			return;
		
		double dist = __smartpost.draw_path(__engine, __package); //draw the path and get the distance from it.
		cmb_packages.getSelectionModel().clearSelection(); //clear the selection.
		__storage.populate(cmb_packages, true); //forcefully update the package situation.
		__log.entry(dist, __package, true, "Paketti lähetetty, matkan pituus: ");
		
		//calculate the chance of breaking, and inform about it if break does happen.
		Random __rand = new Random();
		if (__package.getSafety_rating() > __rand.nextFloat())
			__log.entry("Valitettavasti paketti hajosi matkalla.", true);
	}
	@FXML
	public void load()
	{
		__storage.populate(cmb_packages, true);
		__log.entry("Paketit ladattu.", true);
	}
	
	@FXML
	public void action_close(ActionEvent event) {
		Platform.exit();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		__engine = screen_web.getEngine(); 
		__engine.load(getClass().getResource("html/index.html").toExternalForm()); //load the html file
		
        __log = log.getInstance(); //create the log if not created
		__log.set_view(tbl_log); //set log to point to the tableviw
                
		__smartpost = smartpost.getInstance(); //get the current smartpost instance
		__smartpost.retrieve_all(); //get every smartpost
		__smartpost.populate(cmb_post_offices, false); //fill the combobox with all the smartpost objects
		
		__storage = storage.getInstance(); //get the current storage
		
		//set the cells to hold whatever property they need 
        tb_time.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        tb_from.setCellValueFactory(cellData -> cellData.getValue().fromProperty());
        tb_to.setCellValueFactory(cellData -> cellData.getValue().toProperty());
        tb_text.setCellValueFactory(cellData -> cellData.getValue().textProperty());
		
		__log.entry("Kaikki tarvittava tieto ladattu.", true);
	}
	
}
