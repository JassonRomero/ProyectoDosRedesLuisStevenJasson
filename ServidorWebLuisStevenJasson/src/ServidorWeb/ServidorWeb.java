package ServidorWeb;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class ServidorWeb {

    static final File RutaWeb = new File("ArchivosServidorWeb\\" + ".");
    static final String PaginaPorDefecto = "index.html";
    static final String ArchivoNoEncontrado = "Error.html";

    public ServidorWeb() {

    }

    public void iniciarServer() throws IOException {
        final int PuertoConeccion = 9009;

        HttpServer httpd = HttpServer.create(new InetSocketAddress(PuertoConeccion), 0);
        HttpContext ctx = httpd.createContext("/");
        ctx.setHandler(ServidorWeb::gestionarSolicitud);
        httpd.start();
        System.out.println("---ESPERANDO REQUEST DE CLIENTE HTTP---");

    }

    public static void gestionarSolicitud(HttpExchange exchange) throws IOException {
        
        System.out.println("Cliente solicita = " + exchange.getRequestURI() );
        try {
            String fileRequested = "" + exchange.getRequestURI();
            if (fileRequested.endsWith("/")) {

                fileRequested += PaginaPorDefecto;
            }

            File file = new File(RutaWeb, fileRequested);
            int fileLength = (int) file.length();

            byte[] fileData = leerArchivos(file, fileLength);

            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write(fileData, 0, fileLength);
            os.flush();
            os.close();
        } catch (FileNotFoundException fnfe) {
            try {                
                archivoNoEncontrado(exchange);
            } catch (IOException ioe) {
                System.err.println("Error with file not found exception : " + ioe.getMessage());
            }

        }
    }

    public static void archivoNoEncontrado(HttpExchange exchange) throws IOException {           
            File file = new File(RutaWeb, ArchivoNoEncontrado);
            int fileLength = (int) file.length();

            byte[] fileData = leerArchivos(file, fileLength);

            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write(fileData, 0, fileLength);
            os.flush();
            os.close();
    }

    private static byte[] leerArchivos(File file, int fileLength) throws IOException {
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

}