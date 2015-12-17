package application.smartpost;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import application.files.log;
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
		__builder.append(__locations.get(index).getAddress() + ", " + __locations.get(index).getZip_code() + " " + __locations.get(index).getCity() + "'"); //append the address!
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
			b.getItems().add(__locations.get(i).getName());
	
		log.getInstance().entry("Paikkatiedot ladattu", true);
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
        
        log.getInstance().entry("Automaattien tiedot parsittu ja lisätty.", true);
	}
	
	public double draw_path(WebEngine __engine, o_package pack)
	{
		if (!(pack.get_from().added))
			set_on_map(find(pack.get_from().getName(), false), __engine);
		
		if (!(pack.get_to().added))
			set_on_map(find(pack.get_to().getName(), false), __engine);
		
		StringBuilder __builder = new StringBuilder();
		
		__builder.append("document.createPath(");
		
		__builder.append("[" + pack.get_from().getGps_lat() + ", ");
		__builder.append(pack.get_from().getGps_lng() + ", ");
		
		__builder.append(pack.get_to().getGps_lat() + ", ");
		__builder.append(pack.get_to().getGps_lng() + "], ");
		
		__builder.append("'red', ");
		__builder.append(pack.get_class());
		
		__builder.append(")"); //and the ending of the whole string
		
		log.getInstance().entry("Piirretään reitti: " + pack.get_from().getName() + " - " + pack.get_to().getName(), true);
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
		log.getInstance().entry("Kaikki piirretyt reitit pyyhitty.", true);
	}

	public o_smartpost find(String __name) {
		if (__locations.size() <= 0)
			return null;
		
		for (int i = 0; i < __locations.size(); i++)
		{
			if (__locations.get(i).getName().equals(__name))
				return __locations.get(i);
		}
		
		return null;
	}

	public int find(String __name, boolean debug) {
		if (__locations.size() <= 0)
			return -1;
		
		for (int i = 0; i < __locations.size(); i++)
		{
			if (__locations.get(i).getName().equals(__name))
				return i;
		}
		
		return -1;
	}
}		

