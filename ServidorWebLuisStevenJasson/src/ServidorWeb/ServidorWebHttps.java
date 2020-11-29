package ServidorWeb;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import javax.net.ssl.SSLServerSocketFactory;

public class ServidorWebHttps implements Runnable {

    static final File RutaWeb = new File("ArchivosServidorWeb\\");
    static final String PaginaPorDefecto = "Pagina1.html";
    static final String ArchivoNoEncontrado = " Error.html";

    private Socket connect;

    public ServidorWebHttps(Socket c) {
        connect = c;
    }

//    public static void main(String[] args) {
//        try {
//
//            System.setProperty("javax.net.ssl.keyStore", "certs\\serverKey.jks");//Certificado
//            System.setProperty("javax.net.ssl.keyStorePassword", "112358");//Contrasena certificado
//            System.setProperty("javax.net.ssl.trustStore", "certs\\clientTrustedCerts.jks");//Certificado
//            System.setProperty("javax.net.ssl.trustStorePassword", "112358");//Contrasena certificado
//
//            SSLServerSocketFactory serverFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
//            ServerSocket serverSocket = serverFactory.createServerSocket(9009);
//            System.out.println("---ESPERANDO REQUEST DE CLIENTE HTTPS---");
//            while (true) {
//
//                ServidorWebHttps myServer = new ServidorWebHttps(serverSocket.accept());
//                Thread thread = new Thread(myServer);
//                thread.start();
//            }
//
//        } catch (IOException e) {
//            System.err.println("Server Connection error : " + e.getMessage());
//        }
//    }

    @Override
    public void run() {

        BufferedReader in = null;
        PrintWriter out = null;
        BufferedOutputStream dataOut = null;
        String fileRequested = null;

        try {

            in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            out = new PrintWriter(connect.getOutputStream());
            dataOut = new BufferedOutputStream(connect.getOutputStream());

            String input = in.readLine();
            StringTokenizer parse = new StringTokenizer(input);
            String method = parse.nextToken().toUpperCase();

            fileRequested = parse.nextToken().toLowerCase();

            System.out.println("Cliente solicita = " +fileRequested );
            if (fileRequested.endsWith("/")) {
                fileRequested += PaginaPorDefecto;
            }

            File file = new File(RutaWeb, fileRequested);

            int fileLength = (int) file.length();

            if (method.equals("GET")) {

                byte[] fileData = leerArchivos(file, fileLength);

                out.println("HTTP/1.1 200 OK");
                out.println();
                out.flush();

                dataOut.write(fileData, 0, fileLength);
                dataOut.flush();

            }

        } catch (FileNotFoundException fnfe) {
            try {
                archivoNoEncontrado(out, dataOut, fileRequested);
            } catch (IOException ioe) {
                System.err.println("Error with file not found exception : " + ioe.getMessage());
            }

        } catch (IOException ioe) {

        } finally {
            try {
                in.close();
                out.close();
                dataOut.close();
                connect.close(); 
            } catch (Exception e) {

                System.err.println("Error closing stream : " + e.getMessage());
            }

        }

    }

    private byte[] leerArchivos(File file, int fileLength) throws IOException {
        FileInputStream fileIn = null;
        byte[] fileData = new byte[fileLength];

        try {
            fileIn = new FileInputStream(file);
            fileIn.read(fileData);
        } finally {
            if (fileIn != null) {
                fileIn.close();
            }
        }

        return fileData;
    }

    private void archivoNoEncontrado(PrintWriter out, OutputStream dataOut, String fileRequested) throws IOException {
        File file = new File("ArchivosServidorWeb\\", "Error.html");
        int fileLength = (int) file.length();

        byte[] fileData = leerArchivos(file, fileLength);

        out.println("HTTP/1.1 404 File Not Found");
        out.println();
        out.flush();

        dataOut.write(fileData, 0, fileLength);
        dataOut.flush();

    }

}
