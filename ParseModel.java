import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParseModel{

public static void main(String[] args){

try{


	File model = new File(args[0]);
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(model);
	doc.getDocumentElement().normalize();

	updateElementValue(doc,0,"1.0");


	TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("test.xml"));
	transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            System.out.println("XML file updated successfully");

	

}catch (SAXException | ParserConfigurationException|IOException | TransformerException  e1)
{
	e1.printStackTrace();

	}
}


private static void updateElementValue(Document doc, int cytokine,String value){

NodeList cytokines = doc.getElementsByTagName("InitialPopulation");

Node fox = (Node) cytokines.item(0);
Node ror = (Node) cytokines.item(1);
System.out.println(fox.getTextContent());
fox.setTextContent("99.0");
ror.setTextContent("99.0");




System.out.println(fox.getTextContent());

}












}


