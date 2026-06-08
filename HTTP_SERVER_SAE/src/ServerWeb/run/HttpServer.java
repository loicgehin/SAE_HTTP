package ServerWeb.run;

import ServerWeb.lecteurConf.ConfigSite;
import ServerWeb.lecteurConf.LireConfig;

import java.util.ArrayList;

public class HttpServer {
    /**
     * main lancant le serveur http
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ArrayList<ConfigSite> sites = LireConfig.explorer("src/ServerWeb/conf/serverWeb.conf");
        //pour chaque site, on lance un thread de configuration
        for (ConfigSite site : sites) {
            SiteConf siteConf = new SiteConf(site);
            siteConf.start();
        }
        System.out.println("sites chargés");
    }

}
