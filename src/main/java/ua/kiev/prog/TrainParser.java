package ua.kiev.prog;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class TrainParser {

//  *********  reading data from XML file *********

    public static void fromXML(File file, Timetable timetable) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);

            document.getDocumentElement().normalize();

            Element root = document.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
//            NodeList nodeList = document.getElementsByTagName("train");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;

                    Train train = new Train();
                    train.setId(Integer.parseInt(element.getAttribute("id")));
                    train.setFrom(element.getElementsByTagName("from")
                            .item(0).getTextContent());
                    train.setTo(element.getElementsByTagName("to")
                            .item(0).getTextContent());
                    train.setDate(element.getElementsByTagName("date")
                            .item(0).getTextContent());
                    train.setDeparture(element.getElementsByTagName("departure")
                            .item(0).getTextContent());

                    timetable.addTrain(train);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

//  *********  writing to XML file data from timetable *********

    public static void toXML(File file, Timetable timetable) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();

            Element rootElement = document.createElement("trains");
            document.appendChild(rootElement);

            for (Train currentTrain : timetable.getTrains()) {

                Element newTrain = document.createElement("train");
                newTrain.setAttribute("id", currentTrain.getId().toString());
                rootElement.appendChild(newTrain);

                Element from = document.createElement("from");
                from.setTextContent(currentTrain.getFrom());
                newTrain.appendChild(from);

                Element to = document.createElement("to");
                to.setTextContent(currentTrain.getTo());
                newTrain.appendChild(to);

                Element date = document.createElement("date");
                date.setTextContent(currentTrain.getDate());
                newTrain.appendChild(date);

                Element departure = document.createElement("departure");
                departure.setTextContent(currentTrain.getDeparture());
                newTrain.appendChild(departure);
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

//  *********  adding new train to XML file without timetable usage *********

    public static void addTrainAsXML(File file, Train train) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);

            Element rootElement = document.getDocumentElement();

            Element newTrain = document.createElement("train");
            newTrain.setAttribute("id", train.getId().toString());
            rootElement.appendChild(newTrain);

            Element from = document.createElement("from");
            from.setTextContent(train.getFrom());
            newTrain.appendChild(from);

            Element to = document.createElement("to");
            to.setTextContent(train.getTo());
            newTrain.appendChild(to);

            Element date = document.createElement("date");
            date.setTextContent(train.getDate());
            newTrain.appendChild(date);

            Element departure = document.createElement("departure");
            departure.setTextContent(train.getDeparture());
            newTrain.appendChild(departure);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e){
            e.printStackTrace();
        }
    }
}
