package ServerWeb.status;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class GererStatus {

    public static String getStatusPage() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        Runtime runtime = Runtime.getRuntime();

        // Mémoire
        long memoryFree = runtime.freeMemory();
        long memoryTotal = runtime.totalMemory();
        long memoryUsed = memoryTotal - memoryFree;

        // Disque
        File rootDisk = new File("/");
        long diskFree = rootDisk.getFreeSpace();
        long diskUsed = rootDisk.getTotalSpace() - diskFree;

        // Processus
        int threads = ManagementFactory.getThreadMXBean().getThreadCount();

        // Conversion brute en MB (Division par 1024 * 1024 = 1048576)
        String response = "=== ÉTAT DU SERVEUR ===\n\n" +
                "Mémoire RAM:\n" +
                "  Disponible: " + (memoryFree / 1048576) + " MB\n" +
                "  Utilisée: " + (memoryUsed / 1048576) + " MB\n" +
                "  Total: " + (memoryTotal / 1048576) + " MB\n\n" +
                "Disque Dur:\n" +
                "  Disponible: " + (diskFree / 1048576) + " MB\n" +
                "  Utilisé: " + (diskUsed / 1048576) + " MB\n\n" +
                "Processus:\n" +
                "  Threads actifs: " + threads + "\n";

        return response;
    }
}