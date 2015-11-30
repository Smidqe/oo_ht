package application.files;


import java.io.File;
import java.util.ArrayList;



class __file extends File
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String path;
	String name;
	
	boolean open;
	boolean binary;
	
	__file(String path, String name)
	{
		super(path + name);
		
		this.path = path;
		this.name = name;
	}
	
	public boolean exists()
	{
		return this.exists() && this.isFile();
	}
	
	public boolean write()
	{
		return false;
	}
	
	public boolean read()
	{
		return false;
	}
}

public class files {
	
	ArrayList<__file> __files = new ArrayList<__file>();
	
	public String get_name(String path)
	{
		if (!exists(path))
			return "";
		
		return new File(path).getName();
	}
	
	public void open(String path)
	{
		if (is_open(get_name(path)))
			__files.add(new __file(path, get_name(path)));
		
	}
	
	public boolean is_open(String path)
	{
		int i;
		for (i = 0; i < __files.size(); i++)
		{
			if (exists(__files.get(i).path))
				return true;
			
			//
		}
		
		return false;
	}
	
	public boolean close()
	{
		
		
		return false;
	}
	
	public ArrayList<String> read()
	{
		return null;
	}
	
	public boolean write(String path, int t)
	{
		return false;
	}
	
	public boolean exists(String path)
	{
		File f = new File(path);
	
		return f.exists() && f.isFile();
	}
	
	public boolean create(String path, String name, boolean binary)
	{

		return false;
	}
	
	public int size()
	{
		return -1;
	}
}
