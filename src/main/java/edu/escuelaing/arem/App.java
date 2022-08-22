package edu.escuelaing.arem;

import static spark.Spark.*;
import edu.escuelaing.arem.Connection.HttpConnection;

/**
 * author: Diego Gonzalez
 */
public class App 
{    
    
    public static void main( String[] args ){
        port(getPort());
        staticFiles.location("/public");

        //primer path. Se encarga de redireccionar hacia
        //la pagina index.html
        get("/inicio", (req, res) -> {
            res.redirect("/index.html");

            return null;
        });

        //segundo path. Se encarga de enviar los parametros 
        //para la busqueda de empresa mediante API de bolsa de valores
        path("/search", ()->{
            System.out.println("ENTRA A SEARCH");
            get("/:value/:date", (req,res)->{
                System.out.println("ENTRA A SEGUNDO CON /VALUE/DATE "+req.params(":date"));
                return null;
                //return new StringBuffer(HttpConnection.getData(req.params(":date")));
            });        
            get("/:value", (req,res)->{
                return new StringBuffer(HttpConnection.getData(req.params(":value")));
            });
        });

    }
    
    /**
     * Funcion generada para generar un puerto por defecto
     * para conectarse a la aplicacion web
     * @return
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
