package ServerWeb.config;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
                site.setDocumentRoot(eElement.getElementsByTagName("DocumentRoot").item(0).getTextContent());
                site.setDefaultIndex(eElement.getElementsByTagName("DefaultIndex").item(0).getTextContent());
                site.setAccessLog(eElement.getElementsByTagName("AccessLog").item(0).getTextContent());
                site.setErrorLog(eElement.getElementsByTagName("ErrorLog").item(0).getTextContent());
                sites.add(site);
            }
        }
        return sites;
    }
}
