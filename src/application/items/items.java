package application.items;

import java.io.IOException;
import java.util.ArrayList;

import application.files.files;
import application.files.log;
import application.types.o_item;
import javafx.scene.control.ComboBox;

public class items {
	private ArrayList<o_item> __items;
	private static items __instance = new items();
	private files __file;
	
	private items()
	{
		__items = new ArrayList<o_item>();
		__file = new files("items.txt");
		load(__file);
	}

	
	public void create(o_item __new)
	{
		__items.add(__new);
	}
	
	public void create()
	{
		__items.add(new o_item());
	}
	
	//load all the items from the file.
	public boolean load(files __file)
	{
		if (!__file.exists() || !__file.canRead())
			return false;
		
		//get all the lines from the file.
		ArrayList<String> __lines = new ArrayList<String>();
		try {
			__lines = __file.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//parse the data to items.
		for (int i = 0; i < __lines.size(); i++)
		{
			if (__lines.get(i).trim().isEmpty())
				continue;
			
			String[] __split = __lines.get(i).split(",");
			o_item __item = new o_item();
			
			for (int j = 0; j < __split.length; j++)
				switch(j)
				{
					case 0: 
					{
						__item.name = __split[j];
						break;
					}
				
					case 1: 
					{
						__item.weight = Float.parseFloat(__split[j].trim());
						break;
					}
					case 2:
					case 3:
					case 4: 
					{
						__item.size[j - 2] = Integer.parseInt(__split[j].replaceAll("\\[", "").replaceAll("\\]", "").split(",")[0].trim()); //get rid of the brackets!
						break;
					}
					case 5: 
					{
						__item.durability = Float.parseFloat(__split[j].trim());
						break;
					}
					case 6: 
					{
						__item.breakable = Boolean.parseBoolean(__split[j].trim());
						break;
					}
				}
		
			create(__item);
		}
			
			
		return true;
	}
	
	//save every item, recreate the file if necessary.
	public boolean save(boolean overwrite) throws IOException
	{
		if (overwrite)
		{
			__file.delete();
			__file.createNewFile();
		}
		
		for (int i = 0; i < __items.size(); i++)
			__file.write(__items.get(i).toString() + '\n', true);
		
		return __file.exists() && __file.length() > 0;
	}

	public static items getInstance() {
		return __instance;
	}

	//again add all items (names) to the combobox b use force if you need to.
	public void populate(ComboBox<String> b, boolean force) {
		if ((b.getItems().size() > 0) && !force)
			return;
		else
			b.getItems().clear();
		
		for (int i = 0; i < __items.size(); i++)
			b.getItems().add(__items.get(i).name);
		
	}

	public ArrayList<o_item> list() {
		return __items;
	}


	//find the item.
	public o_item find(String __name) {
		if (list().size() <= 0)
			return null;
		
		for (int i = 0; i < list().size(); i++)
		{
			if (list().get(i).name.trim().equals(__name.trim()))
				return list().get(i);
		}
		
		log.getInstance().entry("Kyseistä esinettä ei löytynyt.", false);
		
		return null;
	}
}
