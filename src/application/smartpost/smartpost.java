package application.smartpost;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import application.parser.parser;
import application.storage.storage;
import application.types.o_package;
import application.types.o_smartpost;
import javafx.scene.control.ComboBox;
import javafx.scene.web.WebEngine;

public class smartpost 
{
	private static smartpost __smartpost = new smartpost();
	private ArrayList<o_smartpost> __locations;
	private storage __storage;

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
		if (__locations.get(index).added)
			return;
		
		StringBuilder __builder = new StringBuilder();
		
		__builder.append("document.goToLocation('");
		__builder.append(__locations.get(index).address + ", " + __locations.get(index).zip_code + " " + __locations.get(index).city + "'"); //append the address!
		__builder.append(", '' ,"); //skipping to the next parameter
		__builder.append("'red'"); //appending the color of the marker
		__builder.append(")"); //and the ending of the whole string

		__engine.executeScript(__builder.toString());
		__locations.get(index).added = true;
	}
	
	public void populate(ComboBox<String> b, boolean force)
	{
		if ((b.getItems().size() > 0) && !force)
			return;
		else
			b.getItems().clear();
		
		for (int i = 0; i < __locations.size(); i++)
			b.getItems().add(__locations.get(i).name);
	
		//__log.write("Added all the locations to the box_locations");
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
	
	public double draw_path(WebEngine __engine, o_package pack)
	{
		if (!(pack.__from.added && pack.__to.added))
			return 0;
		
		StringBuilder __builder = new StringBuilder();
		
		__builder.append("document.createPath('");
		
		__builder.append("[" + pack.__from.gps_lat + ", ");
		__builder.append(pack.__from.gps_lng + ", ");
		
		__builder.append(pack.__to.gps_lat + "', '");
		__builder.append(pack.__to.gps_lng + "], '");
		
		__builder.append("red', ");
		__builder.append(pack.__class);
		
		__builder.append(")"); //and the ending of the whole string
		
		System.out.println(__builder.toString());
		
		return (double) __engine.executeScript(__builder.toString());
	}
	
	public o_package send_package(WebEngine __engine, int __index)
	{
		__storage = storage.getInstance();
		
		if (__storage.__packages.size() == 0 || __index > __storage.__packages.size())
			return null;
		else
			return __storage.take(__index);
	}
	
	public void clear(WebEngine __engine)
	{
		__engine.executeScript("document.deletePaths()");
	}
}		

