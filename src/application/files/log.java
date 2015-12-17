package application.files;

import application.types.o_package;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import application.files.files;
import application.types.o_log_struct;

public class log {
	private files __files;
	private final String FILE_NAME = "log.txt";
	private static log __instance = new log();
	private ObservableList<o_log_struct> __entries = FXCollections.observableArrayList();
	private TableView<o_log_struct> __view;
	
	private log()
	{
		__files = new files(FILE_NAME);
		create();
	}
	
	public static log getInstance()
	{
		return __instance;
	}

	public void set_view(TableView<o_log_struct> tbl)
	{
		this.__view = tbl;
	}
	
	public boolean exists()
	{
		return __files.exists();
	}
	
	
	public boolean create()
	{
		if (__files.exists())
			__files.delete();
	
		try {
			return __files.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return false;
	}	
	
	//void load_by(timetype t)
	//void sort(sort_by s);
	//void clear() 

	public void write(double dist, o_package __package) {
		if (!__files.exists())
			return;
		
		try {
			__files.write(getTime(true) + ", " + getTime(false) + ", Matka: " + dist + '\n', true); //TODO: Finish this.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getTime(boolean date) {
		// TODO Auto-generated method stub
		SimpleDateFormat df;
		if (date)
			df = new SimpleDateFormat("dd/MM/yy");
		else
			df = new SimpleDateFormat("HH:mm:ss");
		
		Date __date = new Date();
		
		return df.format(__date);
	}
	
	public void to_table(String __from, String __to, String __message)
	{
		 __entries.add(new o_log_struct(getTime(false) + ", " + getTime(true), __message, __from, __to));
		 __view.setItems(__entries);
	}
	
	public void entry(double dist, o_package __package, boolean to_file, String __message) {
		//decide what to do.
		to_table(__package.__from.name, __package.__to.name, __message);
		
		if (to_file)
			write(dist, __package);
	}
	
	public void entry(String __txt, boolean to_file)
	{
		to_table("", "", __txt);

		if (to_file)
			write(__txt);
	}

	private void write(String __txt) {
		if (!__files.exists())
			return;
		
		try {
			__files.write(getTime(false) + ", " + getTime(true) + ": " + __txt + '\n', true); //TODO: Finish this.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
