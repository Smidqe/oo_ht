package application.types;

import application.storage.storage;

public class o_smartpost
{
	public String name;
	
	public String address;
	public String zip_code;
	public String city;
	
	public String gps_lat;
	public String gps_lng;
	 
	public storage __storage;
	//TODO: Methods:

	public boolean added;
	
	public o_smartpost()
	{
		__storage = storage.getInstance();
	}
	
	public o_package get_package(int __index)
	{
		return __storage.__packages.get(__index);
	}
	
	public o_package get_package(String __name)
	{
		return get_package(find_package(__name, false));
	}

	public int find_package(String __name, boolean print)
	{
		if (__storage.__packages.isEmpty())
			return -1;
		
		int r = -1;
		for (int i = 0; i < __storage.__packages.size(); i++)
		{
			if (__storage.__packages.get(i).item.name.equals(__name))
			{
				r = i;
				break;
			}
		}

		return r;
	}
	
	public boolean find_package(String __name)
	{
		return find_package(__name, false) != -1;
	}
	
	@Override
	public String toString()
	{
		StringBuilder __builder = new StringBuilder();
		
		__builder.append("Name: " + this.name + ", Address: " + this.address);
		return __builder.toString();
	}
}
