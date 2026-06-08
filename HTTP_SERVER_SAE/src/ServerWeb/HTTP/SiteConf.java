package ServerWeb.HTTP;

import ServerWeb.cache.GererCache;
import ServerWeb.lecteurConf.ConfigSite;
import ServerWeb.log.myweb.EcrireLog;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SiteConf extends Thread{
    /**
     * site a configurer
     * log des acces et des erreurs
     */
    ConfigSite site;
    EcrireLog log;

    /**
     * constructeur de la classe SiteConf
     * @param site site a configurer
     */
    public SiteConf(ConfigSite site) {
        this.site = site;
        String AccessLog = site.getAccessLog();
        String ErrorLog = site.getErrorLog();
        //creation/acces au dossier logs
        new File(AccessLog).getParentFile().mkdirs();
        new File(ErrorLog).getParentFile().mkdirs();
        this.log = new EcrireLog(AccessLog, ErrorLog);

    }

    /**
     * lance le site
     */
    public void run(){
        try {
            ServerSocket server = new ServerSocket(site.getPort());
            System.out.println("server ouvert au port " + site.getPort());
            String msgRecu;
            String url = "";
            while (true) {
                Socket socket = server.accept();

                OutputStream os = socket.getOutputStream();

                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                msgRecu = br.readLine();
                String[] msg = msgRecu.split(" ");
                if (msg[0].equals("GET")) {
                    url = msg[1];
                    System.out.println(msg[0] + " " + url);

                }
                //crée le bon chemin pour acceder au fichier
                if(url.equals("/")){
                    url = "/" + site.getDefaultIndex();
                }
                Path path = Paths.get("web"+site.getDocumentRoot() +"/" + url);
                File fichier = new File(path.toString());
                System.out.println("chemin : " + path.toString());
                System.out.println("chemin absolu : " + fichier.getAbsolutePath());

                String etagNavigateur = null;
                //dans le cas ou le fichier existe
                if (fichier.exists()) {
                    System.out.println("trouvé, envoi...");
                    String etag = GererCache.genererETag(fichier);

                    if (GererCache.dejaAJour(etagNavigateur, etag)) {
                        // fichier pas changé → 304
                        os.write("HTTP/1.1 304 Not Modified\r\n".getBytes());
                        os.write(("ETag: " + etag + "\r\n").getBytes());
                        os.write("\r\n".getBytes());
                        os.flush();

                    } else {
                        // fichier changer, on envoie normalement
                        byte[] envoi = Files.readAllBytes(fichier.toPath());
                        os.write("HTTP/1.1 200 OK\r\n".getBytes());
                        os.write(("Content-Length: " + envoi.length + "\r\n").getBytes());
                        os.write("Connection: keep-Alive\r\n".getBytes());
                        os.write(("Content-Type: " + Files.probeContentType(fichier.toPath()) + "\r\n").getBytes());
                        os.write(("ETag: " + etag + "\r\n").getBytes());
                        os.write("\r\n".getBytes());
                        os.flush();
                        os.write(envoi);
                        os.flush();
                    }
                } else { //si le fichier n'existe pas, erreur 404
                    System.out.println("fichier non trouvable");
                    //ecriture du log error
                    log.AccesError(site.getPort(), url);
                    os.write("HTTP/1.1 404 Not Found\r\n".getBytes());
                    os.write("Content-Type: text/html\r\n".getBytes());
                    os.write("Connection: close\r\n".getBytes());
                    os.write("\r\n".getBytes());
                    os.write("404 Not Found".getBytes());
                    os.flush();
                }
                br.close();
                os.close();
                socket.close();

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
