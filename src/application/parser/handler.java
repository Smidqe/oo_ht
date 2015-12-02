package application.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import application.types.o_smartpost;
import application.smartpost.*;

public class handler extends DefaultHandler {
	public final String[] __values = {"place", "code", "city", "address", "postoffice", "lat", "lng"}; //TODO: Add the corresponding ID:s
	public boolean[] __bools = {false, false, false, false, false, false, false}; 

	public Integer __count = 0;
	private smartpost __smartpost;
	
	handler()
	{
		super();
		__smartpost = smartpost.getInstance();
	}
	
	@Override
	public void startElement(String uri, String localName, String __value, Attributes attributes)	
	{
		int i = 0;
		
		for (i = 0; i < __values.length; i++)
			__bools[i] = __values[i].equals(__value);	
	}

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	int i;
    	
    	for (i = 0; i < __values.length; i++)
    		__bools[i] = false;
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException 
    {
    	int i = 0;
    	
    	for (i = 0; i < __bools.length; i++)
    	{
    		if (__bools[i])
    		{
    			//TODO: Add the necessary checks here!
   
    			
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
    					__smartpost.get_locations().get(__count).zip_code = new String(ch, start, length);
    				}
    				
    				case 2:
    				{
    					__smartpost.get_locations().get(__count).city = new String(ch, start, length);
    				}
    				
    				case 3:
    				{
    					__smartpost.get_locations().get(__count).address = new String(ch, start, length);
    				}
    				
    				case 4:
    				{
    					__smartpost.get_locations().get(__count).name = new String(ch, start, length);
    					break;
    				}
    				case 5:
    				{
    					__smartpost.get_locations().get(__count).gps_lat = new String(ch, start, length);
    					break;
    				}
    				case 6:
    				{
    					__smartpost.get_locations().get(__count).gps_lng = new String(ch, start, length);
    					break;
    				}
    			}
    			

    		}
    	}
    	
    	for (i = 0; i < __values.length; i++)
    		__bools[i] = false;
    }
}