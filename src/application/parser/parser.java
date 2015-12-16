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

        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser __parser = factory.newSAXParser();
            handler __handler = new handler();

            __parser.parse(filename, __handler);
            __parser.reset();
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : Invalid XML");
        } catch (IOException e) {
            System.out.println("IO error");
        }
	}
}
