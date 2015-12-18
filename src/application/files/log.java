package application.files;

import application.types.o_package;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

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
	
	//creates a new file, if it exists then destroy it and create a new one.
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

	//write to a file.
	private void write(double dist, o_package __package) {
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
		// gets the date or time depending on boolean date.
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
		//add the entry to the entries, and then insert all the entries to the table in second tab.
		 __entries.add(new o_log_struct(getTime(true) + ", " + getTime(false), __message, __from, __to));
		 __view.setItems(__entries);
	}
	
	public void entry(double dist, o_package __package, boolean to_file, String __message) {
		//add to the table
		to_table(__package.get_from().getName(), __package.get_to().getName(), __message + dist);
		
		//add to the file aswell if possible.
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
		
		//write to the log.
		try {
			__files.write(getTime(true) + ", " + getTime(false) + ": " + __txt + '\n', true); //TODO: Finish this.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Welcome. You are now entering the end of this file. Here you will experience the relief of having no more lines to go through. But beware there are more to come on next file. Have fun.
}
