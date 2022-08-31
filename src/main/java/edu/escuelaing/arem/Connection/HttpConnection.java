
package edu.escuelaing.arem.Connection;

/**
 *
 * @author Diego Gonzalez
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class HttpConnection {
    private static final String USER_AGENT = "Mozilla/5.0";
    /**
     * Funcion generada para retornar informacion de la empresa por defecto en un tiempo diario
     * Esta funcion se usa para revisar que la empresa si este registrada en la API
     * @param empresa String con nombre de la empresa
     * @param API String con nombre de la API
     * @return StringBuffer con los datos de la empresa traidos del API
     * @throws IOException Exception
     */
    public static StringBuffer getData(String empresa, String API) throws IOException {
        String possibleURL = ""; 
        if(API.equals("polygon")){
             possibleURL = "https://api.polygon.io/v2/aggs/ticker/"+empresa+"/range/1/day/2021-07-22/2021-07-22?adjusted=true&sort=asc&limit=120&apiKey=H5r6suozuTEfd7ZVFGh7HIEcetpLyIKf";
        } 
        else{
             possibleURL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+empresa+"&apikey=demo";
        }      
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
     * @param con HttpConnection, canal de conexion entre el usuario y el API
     * @return StringBuffer con la informacion traida de la URL
     * @throws IOException Exception
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
        //System.out.println(response.toString());
        return response;
    }

    /**
     * Funcion generada para abrir el canal de conexion mediante HTTP hacia
     * la URL dada por el usuario
     * @param URL String URL 
     * @return HttpURLConnection canal de conexion entre usuario y API
     * @throws IOException Exception
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
     * @param empresa String del nombre de la empresa 
     * @param date String con la fecha que se quiere revisar en el API
     * @return StringBuffer con los datos especificos de la empresa 
     * @throws IOException Exception
     */
    public static StringBuffer getDataPerDate(String empresa, String date, String API) throws IOException{
        String possibleUrl = "";
        String realDate = realValueDate(date);
        if(API.equals("alpha")){
            if(date.equals("minute")){
                possibleUrl = "https://www.alphavantage.co/query?function="+realDate+"&symbol="+empresa+"&interval=5min&apikey=demo";
            }
            else{
                possibleUrl =  "https://www.alphavantage.co/query?function="+realDate+"&symbol="+empresa+"&apikey=demo";
            }
        }
        else if(API.equals("polygon")){
            possibleUrl = "https://api.polygon.io/v2/aggs/ticker/"+empresa+"/range/1/"+date+"/2021-07-22/2021-07-22?adjusted=true&sort=asc&limit=120&apiKey=H5r6suozuTEfd7ZVFGh7HIEcetpLyIKf";
        }  
        HttpURLConnection con = connecting(possibleUrl);
        return getResponse(con);
    }

    /**
     * Funcion generada para retornar el valor correcto del tiempo para la URL
     * en caso de usar la API de Alphavantage
     * @param date String con la fecha 
     * @return String valor real de la fecha para la API alphavantage
     */
    public static String realValueDate(String date){
        switch(date){
            case "day":
                date = "TIME_SERIES_DAILY";
                break;
            case "week":
                date = "TIME_SERIES_WEEKLY";
                break;
            case "month":
                date = "TIME_SERIES_MONTHLY";
                break;
            case "minute":
                date = "TIME_SERIES_INTRADAY";
                break;
        }
        return date;
    }

}
