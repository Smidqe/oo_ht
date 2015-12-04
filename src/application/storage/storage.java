package application.storage;

import java.util.ArrayList;

import application.types.o_package;

public class storage {
	public int __max_amount_packages;
	public ArrayList<o_package> __storage;
		
		
	//TODO: Methods
	
	public storage(int capacity)
	{
		__max_amount_packages = capacity;
		__storage = new ArrayList<o_package>();
	}
	
	public storage()
	{
		__max_amount_packages = 100;
		__storage = new ArrayList<o_package>();
	}
	
	public void contains()
	{
		for (int i = 0; i < __storage.size(); i++)
			System.out.println(__storage.get(i).toString());
	}

	public float filled()
	{
		return (__storage.size() / __max_amount_packages);
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
		if (__storage.size() == 0)
			return null;
	
		return __storage.remove(i);
	}
	
	public boolean store(o_package shipment)
	{
		if (isFilled())
			return false; //return it to the sender!
			
		__storage.add(shipment);
		return true;
	}
}
