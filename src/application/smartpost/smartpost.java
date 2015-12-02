package application.smartpost;

import java.util.ArrayList;
import application.types.o_smartpost;
import javafx.scene.web.WebEngine;

public class smartpost 
{
	private static smartpost __smartpost = new smartpost();
	private ArrayList<o_smartpost> locations = new ArrayList<o_smartpost>();
	
	smartpost instance = new smartpost();

	public static smartpost getInstance() {
		// TODO Auto-generated method stub
		return __smartpost;
	}
	
	private smartpost()
	{
		
	}
	
	public ArrayList<o_smartpost> get_locations()
	{
		return locations;
	}
	
	public void set_on_map(int index, WebEngine __engine)
	{	
		__engine.executeScript("document.goToLocation(" + locations.get(index).address + ", " + locations.get(index).zip_code + ", " + locations.get(index).city + ")");	
	}
	
	public void clear()
	{
		
	}
}		

