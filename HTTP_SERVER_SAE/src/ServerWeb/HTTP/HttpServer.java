package ServerWeb.HTTP;

import ServerWeb.lecteurConf.ConfigSite;
import ServerWeb.lecteurConf.LireConfig;

import java.util.ArrayList;

public class HttpServer {
    public static void main(String[] args) throws Exception {
        ArrayList<ConfigSite> sites = LireConfig.explorer("src/ServerWeb/conf/d/serverWeb.conf");
        for (ConfigSite site : sites) {
            SiteConf siteConf = new SiteConf(site);
            siteConf.start();
        }
        System.out.println("sites chargés");
    }

}
