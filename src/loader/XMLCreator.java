/**
 * 
 */
package loader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import alerts.CellSocietyAlerts;
import resources.Resources;

/**
 * Writes current state of simulation to an XML file.
 */
public class XMLCreator {
	
	private Document doc;
	private int xmlCreationNumber = 0;

	/**
	 * Saves the current state of the simulation to an XML file in src/resources
	 * @param simulationType Type of the simulation
	 * @param simulationName Name of the simulation
	 * @param numRows The number of rows in the grid
	 * @param numCols The number of columns in the grid
	 * @param states All possible states
	 * @param param Additional simulation parameter
	 */
	public void createXML
		(String simulationType, String simulationName, int numRows, int numCols, 
				List<String> states, double param) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();
			
			Element dataElement = doc.createElement("data");
			doc.appendChild(dataElement);
			
			addElement(Resources.SIMULATION_TYPE, simulationType, dataElement);
			addElement(Resources.SIMULATION_NAME, simulationName, dataElement);
			addElement(Resources.INPUT_TYPE, Resources.SPECIFIC, dataElement);
			addElement(Resources.NUM_ROWS, Integer.toString(numRows), dataElement);
			addElement(Resources.NUM_COLUMNS, Integer.toString(numCols), dataElement);
			
			for (int row = 0; row < numRows; row++) addElement(String.format("row%d", row), states.get(row), dataElement);
			
			addElement(Resources.PARAM, Double.toString(param), dataElement);
			
			saveXML(String.format(Resources.XML_FILE_PATH, xmlCreationNumber));
			
		} catch (Exception e) {
			throw new XMLException(Resources.getString("XMLGeneratorError"));
		}
	}
	
	/**
	 * Adds element with tag elementTitle and data elementData to root element
	 * @param elementTitle Title of the element
	 * @param elementData Data of the element
	 * @param root Root element
	 */
	private void addElement(String elementTitle, String elementData, Element root) {
		Element newElement = doc.createElement(elementTitle);
		newElement.appendChild(doc.createTextNode(elementData));
		root.appendChild(newElement);
	}
	
	/**
	 * Saves the document to the given file path
	 * @param filePath Path at which to save file
	 * @throws TransformerException
	 * @throws IOException
	 */
	private void saveXML(String filePath) throws TransformerException, IOException {
		TransformerFactory transfac = TransformerFactory.newInstance();
		Transformer transformer = transfac.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		StringWriter stringWriter = new StringWriter();
		StreamResult result = new StreamResult(stringWriter);
		DOMSource source = new DOMSource(doc);
		transformer.transform(source, result);
		String xmlString = stringWriter.toString();
		FileWriter fileWriter = new FileWriter(filePath);
		fileWriter.write(xmlString);
		fileWriter.close();
		CellSocietyAlerts.xmlGenerated(filePath);
	}
}
