package application.parser;


import java.io.IOException;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class parser {
	
	public void parse(InputSource filename) throws ParserConfigurationException, SAXException, IOException
	{

        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser __parser = factory.newSAXParser();
            handler __handler = new handler();

            //loads the handler and parses it.
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
