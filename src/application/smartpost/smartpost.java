package application.smartpost;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import application.parser.parser;
import application.types.o_smartpost;
import javafx.scene.control.ComboBox;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class smartpost 
{
	private static smartpost __smartpost = new smartpost();
	private ArrayList<o_smartpost> __locations;

	public static smartpost getInstance() {
		return __smartpost;
	}
	
	private smartpost()
	{
		__locations = new ArrayList<o_smartpost>();
		
	}
	
	public ArrayList<o_smartpost> get_locations()
	{
		return __locations;
	}
	
	public void set_on_map(int index, WebEngine __engine)
	{
		StringBuilder __builder = new StringBuilder();
		__builder.append("document.goToLocation('");
		__builder.append(__locations.get(index).address + ", " + __locations.get(index).zip_code + " " + __locations.get(index).city + "'");
		__builder.append(", '' ,");
		__builder.append("'red'");
		__builder.append(")");
		
		
		//String s = "document.goToLocation('" + __locations.get(index).address + "', '" + __locations.get(index).zip_code + " " + __locations.get(index).city + "', 'red'" + ")";
		System.out.println(__builder.toString());
		__engine.executeScript(__builder.toString());
		//JSObject __obj = (JSObject)__engine.executeScript("document.goToLocation");
		//System.out.println("OBJ: " + __obj.toString());
		
		
		//System.out.println("Vals: " + __locations.get(index).zip_code + " " + __locations.get(index).city);
		//__obj.call("goToLocation()", __locations.get(index).address, __locations.get(index).zip_code + " " + __locations.get(index).city, "red");
	}
	
	public void populate(ComboBox<String> b, boolean force)
	{
		if ((b.getItems().size() > 0) && !force)
			return;
		
		System.out.println("Array: " + __locations.toString());
		
		for (int i = 0; i < __locations.size(); i++)
			b.getItems().add(__locations.get(i).name);
	
		//__log.write("Added all the locations to the box_locations");
		System.out.println("Populated");
	}
	
	public void retrieve_all()
	{
		URL __url = null;

    	try {
			__url = new URL("http://smartpost.ee/fi_apt.xml");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	parser p = new parser();
        
        try {
			p.parse(new InputSource(__url.openStream()));
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println("Something happened");
			e.printStackTrace();
		}
	}
	
	public void clear()
	{
		
	}
}		

