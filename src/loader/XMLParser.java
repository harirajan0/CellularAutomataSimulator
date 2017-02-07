/**
 * 
 */
package loader;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



/**
 * @author harirajan
 *
 */
public class XMLParser {

    // keep only one documentBuilder because it is expensive to make and can reset it before parsing
    private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
    
    private Element root;

    public XMLParser(File file) {
    	Element root = getRootElement(file);
    }
    
    /**
     * returns the associated string value for the given tag
     * @param tagName tag to parse XML file for
     * @return returns text value for given tagName
     */
    public String getTextValue(String tagName) {
		return getTextValue(root, tagName);
    }

    // Get root element of an XML file
    private Element getRootElement (File xmlFile) {
        try {
            DOCUMENT_BUILDER.reset();
            Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile);
            return xmlDocument.getDocumentElement();
        }
        catch (SAXException | IOException e) {
        	throw new XMLException(e);
        }
    }

    // Get value of Element's text
    private String getTextValue (Element e, String tagName) {
        NodeList nodeList = e.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        else {
            // FIXME: empty string or null, is it an error to not find the text value?
        	throw new XMLException("Cannot find text value for tag %s", tagName);
        }
    }

    // Helper method to do the boilerplate code needed to make a documentBuilder.
    private static DocumentBuilder getDocumentBuilder() {
    	try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		}
    }
    
}