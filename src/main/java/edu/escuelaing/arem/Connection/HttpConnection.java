
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

    public static StringBuffer getData(String empresa) throws IOException {
        String possibleURL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+empresa+"&apikey=demo";
        URL obj = new URL(possibleURL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        
        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
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
        return null;
    }
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome");
    }

}
