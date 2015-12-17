package application.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class files extends File
{

	private static final long serialVersionUID = 1L;

	String path;
	String name;
	
	boolean open;
	boolean binary;
	boolean __exists;
	boolean __in_use;
	
	files(String path, String name)
	{
		super(path + name);
		
		this.path = path;
		this.name = name;
	}
	
	public files(String path) {
		
		super(path);
		
		this.path = path;
		this.name = "";
		// TODO Auto-generated constructor stub
	}

	public boolean exists()
	{
		return __exists = super.exists() && isFile();
	}
	
	public boolean write(String __text, boolean append) throws IOException
	{
		if (!canWrite())
			return false;
		
		if (!append && exists())
		{
			 delete();
			 createNewFile();
		}
		
		OutputStreamWriter r = null;
		try {
			r = new OutputStreamWriter(new FileOutputStream(path, append), "UTF-8");
			r.write(__text);
			r.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public ArrayList<String> read() throws IOException
	{
		BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		
		ArrayList<String> txt = new ArrayList<String>();
		String __tmp;
		
		while ((__tmp = r.readLine()) != null)
			txt.add(__tmp);
		
		r.close();
		return txt;
	}
}
