package application.storage;

import java.util.ArrayList;

import application.types.o_package;
import javafx.scene.control.ComboBox;

public class storage {
	private static int __max_amount_packages;
	private ArrayList<o_package> __packages;
	private static storage __storage = new storage();
	
	//following a singleton principal.
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
	
	//prints every package that exists in this storage. (not used)
	public void contains()
	{
		for (int i = 0; i < __packages.size(); i++)
			System.out.println(__packages.get(i).toString());
	}

	//calculates the fill amount of the storage.
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
	
	//grabs the package from the storage by given index number
	public o_package take(int i) 
	{
		if (__packages.size() == 0)
			return null;
	
		return __packages.remove(i);
	}
	
	//stores the package to the storage.
	public boolean store(o_package shipment)
	{
		if (isFilled())
			return false; //return it to the sender!
			
		__packages.add(shipment);
		return true;
	}

	//adds the package names to the combobox cmb, if forced then clears the items first.
	public void populate(ComboBox<String> cmb, boolean force) {
		if (force)
			cmb.getItems().clear();
		
		for (int i = 0; i < __packages.size(); i++)
			cmb.getItems().add(__packages.get(i).getName());
	}
	
	//obvious getters and setters.
	public static int get__max_amount_packages() {
		return __max_amount_packages;
	}

	public ArrayList<o_package> get__packages() {
		return __packages;
	}

	public static storage get__storage() {
		return __storage;
	}

	public static void set__max_amount_packages(int __max_amount_packages) {
		storage.__max_amount_packages = __max_amount_packages;
	}

	public void set__packages(ArrayList<o_package> __packages) {
		this.__packages = __packages;
	}

	public static void set__storage(storage __storage) {
		storage.__storage = __storage;
	}

}
