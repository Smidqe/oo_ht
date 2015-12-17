package application.files;

import application.types.o_package;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import application.files.files;
class __log_struct
{
	String day;
	String month;
	String year;
	String time;
	String __class;
	String item;
}

public class log {
	private files __files;
	private final String PATH_FILE = "log.txt";
	
	log()
	{
		__files = new files(PATH_FILE);
	}
	
	/*
	
	String date;
	String path;
	
	boolean log_exists()
	{
		return is_open(path + "log_" + date);
	}
	
	
	void log_create(String path)
	{
		if log_exists()
			return true;
	
		return 		
	}
	
	void log_load()
	void log_save()
	void log_print()
	{
		if (
	
	}
	
	
	void load_by(timetype t)
	void sort(sort_by s);
	void clear() 
	 */

	public void write(double dist, o_package __package) {
		if (!__files.exists())
			return;
		
		try {
			__files.write(getTime(true) + ", " + getTime(false) + ", ", true); //TODO: Finish this.
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

	public void add_entry(double dist, o_package __package, boolean to_file) {
		//decide what to do.
		
		if (to_file)
			write(dist, __package);
	}
}
