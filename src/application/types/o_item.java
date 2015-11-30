package application.types;

import application.types.o_enums;

@SuppressWarnings("unused")
abstract class __item {
	String name;
	
	int weight;
	int size[] = new int[3];
	
	float durability; //0 extremely fragile, 1 never breaks;
	
	boolean breakable;
}

public class o_item extends __item
{
	/*
	o_item()
	{
		
	}
	
	o_item(String name, int weight, int size[3], boolean breakable)
	{
		
	}
	
	
	float get_volume();
	float get_value();
	int[3] get_size();
	boolean get_breakable();
	
	void 
	
	 */
}