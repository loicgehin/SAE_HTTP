package ServerWeb.log.myweb;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EcrireLog {
    String cheminLog, cheminError;

    /**
     * constructeur de la classe logger
     * @param cheminLog
     * @param cheminError
     */
    public EcrireLog(String cheminLog, String cheminError) {
        this.cheminLog = cheminLog;
        this.cheminError = cheminError;
    }


    /**
     * gere la ligne d'acces du xml
     * @param port port donné dans le xml
     * @param url url donnée dans le xml
     */
    public void AccesLog(int port, String url)  {
        String msg = "Acces au site " + url + " sur le port " + port;
        ecrire(cheminLog, msg);
    }

    /**
     * methode ecrivant dans un fichier
     * @param cheminFichier la ou on mes le fichier, ici juste le nom est donné en l'occurrence
     * @param message le message a ecrire
     */
    private void ecrire(String cheminFichier, String message) {
        //true permet de reecrire dans le fichier
        try (PrintWriter pw = new PrintWriter(new FileWriter(cheminFichier, true))) {
            pw.println(message);
        } catch (IOException e) {
            System.err.println("Erreur " + e.getMessage());
        }
    }

    /**
     * gere l'erreur d'acces du xml
     * @param port port donné dans le xml
     * @param url url donnée dans le xml
     */
    public void AccesError(int port, String url) {
        String msg = "Erreur lors de l'accès au site " + url + " sur le port " + port;
        ecrire(cheminError, msg);
    }


}
