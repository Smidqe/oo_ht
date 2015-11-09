package application.types;

import java.util.ArrayList;

class opening_hours
{
	String t_open;
	String t_close;
	
	String t_days[];
}

abstract class __smartpost 
{
	
	public ArrayList<opening_hours> open[];
	public String address[];
	public String gps_loc[];
	
	public o_storage storage;
	/*

	*/
}

public class o_smartpost extends __smartpost
{
	/*
	TODO: Methods:
	
	o_smartpost
	{
	}
	
	void create_package()
	void get_package()
	void send_package()
	void distance(String city, String address, int postal)
	void get_time(open t);
	boolean is_open();
	
	
	*/
}
