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
	
	public o_smartpost()
	{
		__storage = new storage();
	}
	
	public o_package get_package(int __index)
	{
		return null;
	}
	
	public o_package get_package(String __name)
	{
		return null;
	}
	
	public boolean send_package(o_package __package, o_smartpost __to)
	{
		if (!__to.__storage.isFilled())
			return false;
	
		__to.__storage.store(__package);
		return true;
	}
	
	void distance(String city, String address, int postal)
	{
		
	}
	
	@Override
	public String toString()
	{
		StringBuilder __builder = new StringBuilder();
		
		__builder.append("Name: " + this.name + ", Address: " + this.address);
		return __builder.toString();
	}
}
