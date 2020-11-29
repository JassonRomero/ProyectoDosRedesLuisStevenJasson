package servidorwebluisstevenjasson;

import ServidorWeb.ServidorWeb;
import ServidorWeb.ServidorWebHttps;
import java.io.IOException;
import java.net.ServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.swing.JOptionPane;

public class ServidorWebLuisStevenJasson {

    public static void main(String[] args) throws IOException {
        ServidorWeb server = new ServidorWeb();

        String entrada = JOptionPane.showInputDialog(null, "Ingrese un 1 para HTTP o un 2 para HTTPS");
        if (entrada.equals("1")) {
            server.iniciarServer();
        } else if (entrada.equals("2")) {
            try {

                System.setProperty("javax.net.ssl.keyStore", "certs\\serverKey.jks");//Certificado
                System.setProperty("javax.net.ssl.keyStorePassword", "112358");//Contrasena certificado
                System.setProperty("javax.net.ssl.trustStore", "certs\\clientTrustedCerts.jks");//Certificado
                System.setProperty("javax.net.ssl.trustStorePassword", "112358");//Contrasena certificado

                SSLServerSocketFactory serverFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
                ServerSocket serverSocket = serverFactory.createServerSocket(9009);
                System.out.println("---ESPERANDO REQUEST DE CLIENTE HTTPS---");
                while (true) {

                    ServidorWebHttps myServer = new ServidorWebHttps(serverSocket.accept());
                    Thread thread = new Thread(myServer);
                    thread.start();
                }

            } catch (IOException e) {
                System.err.println("Server Connection error : " + e.getMessage());
            }
        }

    }
}
