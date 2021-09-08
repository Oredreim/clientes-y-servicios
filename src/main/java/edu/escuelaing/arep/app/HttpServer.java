package edu.escuelaing.arep.app;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.*;
import java.io.*;
import java.io.File;
import java.util.ArrayList;
/**
 * Clase encargada de la implementacion propia de un servidor el cual es capaz de recibir peticiones HTTP, y asimismo retornar el recurso pedido.
 * @author Cristian Piñeros
 * @version 1.0.  (07 de Septiembre del 2021)
 */
public class HttpServer {
    private String root = "src/main/resources";
    private PrintWriter out = null;
    private DBConnection connection = null;
    /**
     * Metodo encargado de realizar la respectiva conexion con el cliente y con el servidor.
     * @throws IOException Arroja una excepcion en caso de que no pueda escuchar por el puerto configurado por defecto.
     */
    public void start() throws IOException {
        int port = getPort();
        connection = new DBConnection();
        while(true) {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(port);
            } catch (IOException e) {
                System.err.println("Could not listen on port: 35000.");
                System.exit(1);
            }
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            processRequest(clientSocket);

            out.close();
            clientSocket.close();
            serverSocket.close();
        }
    }
    /**
     * Metodo encargado de procesar todos los Requests del cliente, para asi desplegar la interfaz de usuario en el recurso /Apps/index.html.
     * @param clientSocket Parametro que indica el socket del cliente que intenta conectarse.
     */
    private void processRequest(Socket clientSocket) throws IOException {
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, file = "";
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.contains("GET")) {
                file = inputLine.split(" ")[1];
                if (file.startsWith("/Apps")) {
                    String appuri = file.substring(5);
                    out.println(invoke(appuri));
                } else {
                    if (file.equals("/")) {
                        file = "/index.html";
                    }
                    getResource(file,clientSocket);
                }
            }
            if (!in.ready()) {
                break;
            }
        }
        in.close();
    }
    /**
     * Metodo encargado de obtener el Request del cliente, para poder desplegar los recursos como lo son la pagina html y el json.
     * @param file Parametro que indica el archivo ya sea html o json de la pagina web.
     * @param clientSocket Parametro que indica el socket del cliente que intenta conectarse.
     */
    private void getResource(String file,Socket clientSocket) throws IOException{
        String outputLine;
        int type = getType(file);
        if (type == 0) {
            outputLine = getFile(file,"html");
            out.println(outputLine);
        }else if(type == 1){
            outputLine = getFile(file,"json");
            out.println(outputLine);
        }else if(type == 2) {
            getImage(file, clientSocket.getOutputStream());
        }
    }
    /**
     * Metodo encargado de invocar las bases de datos en el recurso /informationDB, para su posterior despliegue de manera ordenada, separando cada individuo por nombre, apellido y direccion.
     * @param type Parametro que indica el tipo del MySpark, para obtener en las bases de datos los valores deseados.
     * @return Retorna toda la informacion proporcionada por la base de datos, separada por nombres y apellidos y la direccion de cada ciudadano.
     */
    public String invoke(String type){
        String outputLine = getHeader("html"),file =  MySpark.get(type);
        if(type.equals("/informationDB")){
            file = "";
            ArrayList<String[]> information = connection.getInformation();
            for(String[] temp : information){
                file+=" Nombres y Apellidos: " + temp[0] + "     -     " + " Direccion: " + temp[1];
            }
            return outputLine + file;
        }
        if(file != null){
            return outputLine + file;
        }
        return errorResponse(type);
    }
    /**
     * Metodo encargado de obtener el encabezado HTTP de la pagina web.
     * @param type Parametro que indica el tipo del encabezado de la pagina web. 
     * @return Retorna el encabezado de la pagina web.
     */
    public String getHeader(String type){
        return "HTTP/1.1 200 OK\r\n" + "Content-Type: text/"+type+"\r\n" + "\r\n";
    }
    /**
     * Metodo encargado de obtener el archivo root o ruta de la aplicacion web.
     * @param ruta Parametro que indica la ruta de la aplicacion web.
     * @param type Parametro que indica el tipo del encabezado de la pagina web.
     * @return Retorna el archivo ruta de la plicacion web.
     */
    public String getFile(String ruta,String type){
        String outputLine = getHeader(type),path = root + ruta;
        File file = new File(path);
        if(file.exists()){
            String content;
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                while ((content = br.readLine()) != null) {
                    outputLine += content;
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            outputLine = errorResponse(file.getName());
        }
        return outputLine;
    }
    /**
     * Metodo encargado de mostrar error en el encabezado de la pagina web en caso de que halla un error de sintaxis al escribir mal la pagina web.
     * @param file Parametro que indica el archivo que contiene el error en caso de que no se encuentre la pagina web.
     */
    private String errorResponse(String file){
        String outputLine = "HTTP/1.1 404 Not Found \r\nContent-Type: text/html \r\n\r\n <!DOCTYPE html> <html>"
                + "<head><title>404</title></head>" + "<body> <h1>404 Not Found " + file
                + "</h1></body></html>";
        return outputLine;
    }
    /**
     * Metodo encargado de obtener el archivo que se encuentra en el directorioresources del codigo en el que se encuentra la imagen, para asi desplegarla en la pagina web.
     * @param type Parametro que indica el tipo del encabezado de la pagina web. 
     * @param outClient Parametro que indica el despliegue de la imagen al cliente. 
     */
    public void getImage(String type, OutputStream outClient){
        String path = root + type;
        File file = new File(path);
        if (file.exists()) {
            try {
                BufferedImage image = ImageIO.read(file);
                ByteArrayOutputStream ArrBytes = new ByteArrayOutputStream();
                DataOutputStream writeimg = new DataOutputStream(outClient);
                ImageIO.write(image, "PNG", ArrBytes);
                writeimg.writeBytes("HTTP/1.1 200 OK \r\n" + "Content-Type: image/png \r\n" + "\r\n");
                writeimg.write(ArrBytes.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            out.println(errorResponse(file.getName()));
        }
    }
    /**
     * Metodo encargado de desplegar los archivos html y js que se encuentran en el directorio resources del codigo que contienen el mensaje de bienvenida y la interfaz de la Registraduria Nacional del Estado Civil.
     * @param type Parametro que indica el tipo archivo ya sea html o json de la pagina web.
     * @return Retorna tanto el archivo html que contiene la interfaz como el js que contiene el mensaje de bienvenida.
     */
    public int getType(String type){
        if(type.contains("html")){
            return 0;
        }else if(type.contains("js")){
            return 1;
        }else{
            return 2;
        }
    }
    /**
     * Este metodo lee el puerto predeterminado segun lo especificado por la variable PORT en el entorno.
     * @return returns Retorna el puerto predeterminado si el heroku-port no esta configurado (es decir, en localhost).
     */
    public int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }
}