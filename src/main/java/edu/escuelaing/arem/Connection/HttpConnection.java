
package edu.escuelaing.arem.Connection;

/**
 *
 * @author diego
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class HttpConnection {
    private static final String USER_AGENT = "Mozilla/5.0";

    /**
     * Funcion generada para retornar informacion de la empresa por defecto en un tiempo diario
     * Esta funcion se usa para revisar que la empresa si este registrada en la API
     * @param empresa
     * @return
     * @throws IOException
     */
    public static StringBuffer getData(String empresa) throws IOException {
        String possibleURL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+empresa+"&apikey=demo";
        HttpURLConnection con = connecting(possibleURL);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            return getResponse(con);
        } 
        return null;
    }

    /**
     * Funcion generada para generar la respuesta de la API al usuario mediante
     * la terminal. De igual manera envía un response al front para que ellos 
     * obtengan el JSON
     * @param con
     * @return
     * @throws IOException
     */
    public static StringBuffer getResponse(HttpURLConnection con) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(
            con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // print result
        System.out.println(response.toString());
        return response;
    }

    /**
     * Funcion generada para abrir el canal de conexion mediante HTTP hacia
     * la URL dada por el usuario
     * @param URL
     * @return
     * @throws IOException
     */
    public static HttpURLConnection connecting(String URL) throws IOException {
        URL obj = new URL(URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        return con;
    }

    /**
     * Funcion encargada de retornar el JSON correspondiente al mercado
     * de valores de la empresa en un periodo de tiempo determinado
     * @param empresa
     * @param date
     * @return
     * @throws IOException
     */
    public static StringBuffer getDataPerDate(String empresa, String date) throws IOException{
        String possibleURL =  "https://www.alphavantage.co/query?function="+date+"&symbol="+empresa+"&apikey=demo";
        System.out.println(possibleURL);
        HttpURLConnection con = connecting(possibleURL);
        return getResponse(con);
    }
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome");
    }

}
