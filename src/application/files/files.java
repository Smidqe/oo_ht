package application.files;


import java.io.File;
import java.util.ArrayList;



class __file
{
	String path;
	String name;
	
	boolean open;
	boolean binary;
}

public class files {
	
	ArrayList<__file> __files = new ArrayList<__file>();
	
	String get_name(String path)
	{
		if (!exists(path))
			return "";
		
		return new File(path).getName();
	}
	
	boolean open(String path)
	{
		if (is_open(get_name(path)))
			return true;
		
		
		
		return false;
	}
	
	boolean is_open(String name)
	{
		int i;
		for (i = 0; i < __files.size(); i++)
		{
			if (__files.get(i).name.equals(name))
				return true;
			
			//
		}
		
		return false;
	}
	
	boolean close()
	{
		
		
		return false;
	}
	
	boolean read()
	{
		return false;
	}
	
	boolean write(String path, int t)
	{
		return false;
	}
	
	boolean exists(String path)
	{
		File f = new File(path);
	
		return f.exists() && f.isFile();
	}
	
	int size()
	{
		return -1;
	}
}
