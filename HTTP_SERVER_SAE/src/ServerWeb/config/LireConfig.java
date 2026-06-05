package ServerWeb.config;


import javax.xml.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class LireConfig {

    public static ArrayList<ConfigSite> parse(String path) throws IOException, SAXException, ParserConfigurationException, SAXException {
        ArrayList<ConfigSite> sites = new ArrayList<>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(path));

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("site");

        for(int i=0; i<nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                ConfigSite site = new ConfigSite();
                site.setPort(Integer.parseInt(eElement.getElementsByTagName("port").item(0).getTextContent()));
                site.setRoot(eElement.getElementsByTagName("root").item(0).getTextContent());
                site.setIndex(eElement.getElementsByTagName("index").item(0).getTextContent());
                site.setAccessLog(eElement.getElementsByTagName("accessLog").item(0).getTextContent());
                site.setErrorLog(eElement.getElementsByTagName("errorLog").item(0).getTextContent());
                sites.add(site);
            }
        }
        return sites;
    }
}
