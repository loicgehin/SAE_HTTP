package ServerWeb.config;
import javax.xml.*;
import org.w3c.dom.*;

public class ConfigSite {

    DocumentBuilderFactory factory = DocumentBilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(new File("serverWeb.conf"));
    NodeList list = doc.getElementsByTagName("person");
}
