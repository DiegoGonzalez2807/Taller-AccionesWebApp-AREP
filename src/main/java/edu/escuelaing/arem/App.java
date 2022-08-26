package edu.escuelaing.arem;

import static spark.Spark.*;

import edu.escuelaing.arem.Connection.HttpConnection;
import edu.escuelaing.arem.Cache.cacheData;

/**
 * author: Diego Gonzalez
 */
public class App 
{
    public static void main( String[] args ){
        cacheData cache = new cacheData();
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
            //Primer get. Sirve en caso de URLs como /IBM/TIME_SERIES_INTRADAY
            get("/:value/:date", (req,res)->{
                if(cache.existsCache(req.params(":value")+" "+req.params(":date"))){
                    return cache.getInfoCache(req.params(":value")+" "+req.params(":date"));
                }
                else{
                    cache.insertInCache(req.params(":value")+" "+req.params(":date"), new StringBuffer(HttpConnection.getDataPerDate(req.params(":value"),req.params((":date")))));               
                    return new StringBuffer(HttpConnection.getDataPerDate(req.params(":value"),req.params((":date"))));
                }
            });        
            //Segundo get. Sirve en caso de solo buscar la empresa
            get("/:value", (req,res)->{ 
                if(cache.existsCache(req.params(":value")+" TIME_SERIES_DAILY")){
                    return cache.getInfoCache(req.params(":value")+" TIME_SERIES_DAILY");
                }
                else{
                    cache.insertInCache(req.params(":value")+" TIME_SERIES_DAILY", new StringBuffer(HttpConnection.getData(req.params(":value"))));               
                    return new StringBuffer(HttpConnection.getData(req.params(":value")));
                }
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
