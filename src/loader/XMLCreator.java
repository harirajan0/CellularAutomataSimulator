/**
 * 
 */
package loader;

import java.io.FileWriter;
import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import alerts.CellSocietyAlerts;



/**
 * @author harirajan
 *
 */
public class XMLCreator {
	
	private Document doc;
	private int xmlCreationNumber = 0;

	public void createXML
		(String simulationType, String simulationName, int numRows, int numCols, List<String> states, double param) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();
			
			Element dataElement = doc.createElement("data");
			doc.appendChild(dataElement);
			
			addElement("simulationType", simulationType, dataElement);
			addElement("simulationName", simulationName, dataElement);
			addElement("inputType", "SPECIFIC", dataElement);
			addElement("numRows", Integer.toString(numRows), dataElement);
			addElement("numCols", Integer.toString(numCols), dataElement);
			
			for (int row = 0; row < numRows; row++) addElement(String.format("row%d", row), states.get(row), dataElement);
			
			addElement("param", Double.toString(param), dataElement);
			
			TransformerFactory transfac = TransformerFactory.newInstance();
			Transformer trans = transfac.newTransformer();
			trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");

			StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(doc);
            trans.transform(source, result);
            String xmlString = sw.toString();
            String newFilePath = String.format("src/resources/mySimulation%d.xml", xmlCreationNumber);
            FileWriter fw = new FileWriter(newFilePath);
            fw.write(xmlString);
            fw.close();
            CellSocietyAlerts.xmlGenerated(newFilePath);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void addElement(String elementTitle, String elementData, Element root) {
		Element newElement = doc.createElement(elementTitle);
		newElement.appendChild(doc.createTextNode(elementData));
		root.appendChild(newElement);
	}
}
