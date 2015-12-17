package application.storage;

import java.util.ArrayList;
import java.util.Random;

import application.files.log;
import application.types.o_package;
import javafx.scene.control.ComboBox;

public class storage {
	private static int __max_amount_packages;
	public ArrayList<o_package> __packages;
	private static storage __storage = new storage();
	
	public static storage getInstance()
	{
		return __storage; 
	}
	
	private storage(int capacity)
	{
		__max_amount_packages = capacity;
		__packages = new ArrayList<o_package>();
	}
	
	private storage()
	{
		__max_amount_packages = 100;
		__packages = new ArrayList<o_package>();
	}
	
	public void contains()
	{
		for (int i = 0; i < __packages.size(); i++)
			System.out.println(__packages.get(i).toString());
	}

	public float filled()
	{
		return (__packages.size() / __max_amount_packages);
	}
	
	public boolean filled(int min)
	{
		return (filled() * 100 >= min);
	}
	
	public boolean isFilled()
	{
		return (filled() == 1);
	}
	
	public o_package take(int i)
	{
		if (__packages.size() == 0)
			return null;
	
		return __packages.remove(i);
	}
	
	public boolean store(o_package shipment)
	{
		if (isFilled())
			return false; //return it to the sender!
			
		Random __rand = new Random();
		if (shipment.getSafety_rating() > __rand.nextFloat())
			__packages.add(shipment);
		else
			log.getInstance().entry("Valitettavasti paketti hajosi matkan aikana.", true);
		
		return true;
	}

	public void populate(ComboBox<String> cmb, boolean force) {
		for (int i = 0; i < __packages.size(); i++)
			cmb.getItems().add(__packages.get(i).getName());
	}
}
