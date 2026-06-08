package ServerWeb.cache;

import java.io.File;

public class GererCache {

    // creation de l'ETag
    public static String genererETag(File fichier) {
        return fichier.lastModified() + "-" + fichier.length();
    }

    // vrai si le navigateur a déjà la bonne version
    public static boolean dejaAJour(String etagNavigateur, String etagFichier) {
        if (etagNavigateur == null) return false;
        return etagNavigateur.equals(etagFichier);
    }
}