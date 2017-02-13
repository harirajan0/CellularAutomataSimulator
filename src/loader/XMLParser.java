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


import resources.Resources;


/**
 * Parses the XML file for data
 */
public class XMLParser {

    // keep only one documentBuilder because it is expensive to make and can reset it before parsing
    private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
    
    private Element myRoot;
    private File myFile;

    public XMLParser(File file) {
    	myRoot = getRootElement(file);
    	myFile = file;
    }
    
    /**
     * returns the associated string value for the given tag
     * @param tagName tag to parse XML file for
     * @return returns text value for given tagName
     */
    public String getTextValue(String tagName) {
		return getTextValue(myRoot, tagName);
    }

    /**
     * Gets the root element of the XML file
     * @param xmlFile XML file to scan
     * @return root element of <code>xmlFile</code>
     */
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

    /**
     * Gets the value of an element's text
     * @param e Element to get the value of
     * @param tagName Tag to get the text at
     * @return The text at <code>e</code>
     */
    private String getTextValue (Element e, String tagName) {
        NodeList nodeList = e.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        else {
        	throw new XMLException(Resources.getString("XMLInvalidTagError"), tagName);
        }
    }

    /**
     * Boilerplate method to build a DocumentBuilder
     * @return DocumentBuilder to use for XML file parsing
     */
    private static DocumentBuilder getDocumentBuilder() {
    	try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new XMLException(e.getMessage());
		}
    }
    
    /**
     * Gets the file being used
     * @return The file being used
     */
    public File getFile() {
    	return myFile;
    }
    
}