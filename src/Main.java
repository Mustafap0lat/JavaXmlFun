import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse("src/sma_gentext.xml");
        doc.getDocumentElement().normalize();
        NodeList transUnits = doc.getElementsByTagName("trans-unit");

        for (int i = 0; i < transUnits.getLength(); i++) {
            Node transUnit = transUnits.item(i);

            if (transUnit.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) transUnit;

                if (element.getAttribute("id").equals("42007")) {
                    String transUnitID = element.getAttribute("id");

                    File file = new File("output.txt");
                    PrintWriter writer = new PrintWriter(file);

                    //Kunde valt att enbart skriva ut target men valde att skriva ut alla. Hoppas att det är ok.
                    writer.println("Trans-unit ID: " + transUnitID);
                    writer.println("Source Value: " + GetValueFromElementByTagName("source", element));
                    writer.println("Target Value: " + GetValueFromElementByTagName("target", element));
                    writer.println();
                    writer.close();
                    break;
                }
                System.out.println("\033[31mCheck the created output.txt file.");
            }
        }
    }
    public static String GetValueFromElementByTagName(String targetName, Element element) {
        String targetValue = "";
        NodeList value = element.getElementsByTagName(targetName);
        if (value.getLength() > 0)
        {
            targetValue = value.item(0).getTextContent();
        }
        return targetValue;
    }
}
