package application.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import application.types.o_smartpost;
import application.smartpost.*;

public class handler extends DefaultHandler {
	public final String[] __values = {"place", "code", "city", "address", "postoffice", "lat", "lng"};
	public boolean[] __bools = {false, false, false, false, false, false, false}; 

	public Integer __count = -1;
	public smartpost __smartpost;
	
	handler()
	{
		super();
		__smartpost = smartpost.getInstance();
	}
	
	//check if the __value is the same as any of the values. sets boolean in index i if found to true.
	@Override
	public void startElement(String uri, String localName, String __value, Attributes attributes)	
	{
		int i = 0;
		
		for (i = 0; i < __values.length; i++)
			__bools[i] = __values[i].equals(__value);	
	}

	//clears all the booleans
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	int i;
    	
    	for (i = 0; i < __values.length; i++)
    		__bools[i] = false;
    }

    //handles every situation when a single or none of the booleans is true.
    @Override
    public void characters(char ch[], int start, int length) throws SAXException 
    {
    	int i = 0;
    	//should I say something here?
    	for (i = 0; i < __bools.length; i++)
    	{
    		if (__bools[i])
    		{	
    			switch(i)
    			{
    				case 0:
    				{
    					__smartpost.get_locations().add(new o_smartpost());
    					__count++;
    					break;
    				}
    				case 1:
    				{
    					__smartpost.get_locations().get(__count).setZip_code(new String(ch, start, length));
    					break;
    				}
    				
    				case 2:
    				{
    					__smartpost.get_locations().get(__count).setCity(new String(ch, start, length));
    					break;
    				}
    				
    				case 3:
    				{
    					__smartpost.get_locations().get(__count).setAddress(new String(ch, start, length));
    					break;
    				}
    				
    				case 4:
    				{
    					__smartpost.get_locations().get(__count).setName(new String(ch, start, length));
    					break;
    				}
    				case 5:
    				{
    					__smartpost.get_locations().get(__count).setGps_lat(new String(ch, start, length));
    					break;
    				}
    				case 6:
    				{
    					__smartpost.get_locations().get(__count).setGps_lng(new String(ch, start, length));
    					break;
    				}
    			}
    		}
    	}
    	
    	//clear all the booleans to false
    	for (i = 0; i < __values.length; i++)
    		__bools[i] = false;
    }
}