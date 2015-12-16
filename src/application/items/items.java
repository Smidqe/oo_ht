package application.items;

import java.io.IOException;
import java.util.ArrayList;

import application.files.files;
import application.types.o_item;

public class items {
	public ArrayList<o_item> __items;
	
	items()
	{
		__items = new ArrayList<o_item>();
	}

	
	public void create(o_item __new)
	{
		__items.add(__new);
	}
	
	public void create()
	{
		__items.add(new o_item());
	}
	
	
	public boolean load(files __file)
	{
		if (!__file.exists())
			return false;
		
		return false;
	}
	
	public boolean save(files __file, boolean overwrite) throws IOException
	{
		if (!__file.exists())
			__file.createNewFile();
		
		for (int i = 0; i < __items.size(); i++)
			__file.write(__items.get(i).toString(), !overwrite);
		
		return false;
	}


	public static items getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
}
