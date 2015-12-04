package application.parser;

import application.types.o_smartpost;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import application.files.files;

@SuppressWarnings("unused")
public class parser {
	
	public void parse(InputSource filename) throws ParserConfigurationException, SAXException, IOException
	{
		/*
		if (!xml_exists(filename))
		{
			System.out.println("XML not found");
			return;
		}
		*/
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser __parser = factory.newSAXParser();
            handler __handler = new handler();

            __parser.parse(filename, __handler);
            __parser.reset();
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
	}
	
	public boolean xml_exists(InputSource __source) throws IOException
	{
		
		if (__source.toString().contains("http"))
		{
			URL url = new URL(__source.toString());
			HttpURLConnection __site = (HttpURLConnection) url.openConnection();
			__site.setRequestMethod("GET");
			
			return (__site.getResponseCode() == HttpURLConnection.HTTP_OK);
		}

		files __file = new files();
		return __file.exists(__source.toString());
	}
}
