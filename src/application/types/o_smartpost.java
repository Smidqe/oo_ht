package application.types;

import application.files.log;
import application.storage.storage;

public class o_smartpost
{
	private String name;
	
	private String address;
	private String zip_code;
	private String city;
	
	private String gps_lat;
	private String gps_lng;
	 
	private storage __storage;
	public boolean added;
	
	public o_smartpost()
	{
		__storage = storage.getInstance();
	}
	
	public o_package get_package(int __index)
	{
		if (__index < 0)
			return null;
		
		return __storage.get__packages().get(__index);
	}
	
	//returns the package if found by it's name.
	public o_package get_package(String __name)
	{
		return get_package(find_package(__name, false));
	}

	//finds the package by it's name, and prints to the log if print is true
	public int find_package(String __name, boolean print)
	{
		if (__storage.get__packages().isEmpty())
			return -1;
		
		int r = -1;
		for (int i = 0; i < __storage.get__packages().size(); i++)
			if (__storage.get__packages().get(i).getItem().name.equals(__name))
			{
				r = i;
				
				if (print)
					log.getInstance().entry("Paketti: " + __name + " löytyi.", false);
				break;
			}
		
		if (print)
			log.getInstance().entry("Paketti: " + __name + " ei löytynyt.", false);
		
		return r;
	}
	
	//a wrapper for the previous function only accepts the name.
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

	//Getters and setters, all created automatically by Eclipse.
	
	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getZip_code() {
		return zip_code;
	}

	public String getCity() {
		return city;
	}

	public String getGps_lat() {
		return gps_lat;
	}

	public String getGps_lng() {
		return gps_lng;
	}

	public storage get__storage() {
		return __storage;
	}

	public boolean isAdded() {
		return added;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setGps_lat(String gps_lat) {
		this.gps_lat = gps_lat;
	}

	public void setGps_lng(String gps_lng) {
		this.gps_lng = gps_lng;
	}

	public void set_storage(storage __storage) {
		this.__storage = __storage;
	}

	public void setAdded(boolean added) {
		this.added = added;
	}
}
