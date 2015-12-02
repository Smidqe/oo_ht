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
	/*
	void create_package()
	void get_package()
	void send_package()
	void distance(String city, String address, int postal)
	void get_time(open t);
	boolean is_open();
	
	
	*/
}
