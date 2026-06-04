import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCP {
    public static void main(String[] args) throws UnknownHostException, IOException {
        ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));
        System.out.println("server ouvert au port " + args[0]);
        String msgEnvoye;
        String msgRecu;

        Scanner sc = new Scanner(System.in);

        for (int i=1; i<6;i++) {
            Socket socket = server.accept();
            System.out.println("connection trouve, numero "+i);

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            PrintWriter pw = new PrintWriter(osw);

            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            pw.println("vous etes le client numero "+ i);
            pw.flush();

            while (true) {
                System.out.println("en attente de message");
                msgRecu = br.readLine();
                if (msgRecu.equals("stop")) {
                    System.out.println("connexion interrompue par l'utilisateur");
                    break;
                }
                System.out.println("message recu: " + msgRecu);

                System.out.print("message: ");
                msgEnvoye = sc.nextLine();

                pw.println(msgEnvoye);
                pw.flush();

                if (msgEnvoye.equals("stop")) {
                    System.out.println("connexion interrompue par le serveur");
                    break;
                }

            }
            br.close();
            pw.close();
            socket.close();
        }

        server.close();


    }
}