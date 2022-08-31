package edu.escuelaing.arem;

import static spark.Spark.*;

import edu.escuelaing.arem.Connection.HttpConnection;
import edu.escuelaing.arem.Cache.cacheData;

/**
 * author: Diego Gonzalez
 */
public class SparkApp 
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
            //Primer get. Sirve en caso de URLs como /IBM/TIME_SERIES_INTRADAY
            get("/:API/:value/:date", (req,res)->{
                if(cache.existsCache(req.params(":value")+" "+req.params(":date")+" "+req.params(":API"))){
                    return cache.getInfoCache(req.params(":value")+" "+req.params(":date")+" "+req.params(":API"));
                }
                else{
                    cache.insertInCache(req.params( ":value")+" "+req.params(":date")+" "+req.params(":API"), new StringBuffer(HttpConnection.getDataPerDate(req.params(":value"),req.params((":date")),req.params(":API"))));               
                    return new StringBuffer(HttpConnection.getDataPerDate(req.params(":value"),req.params((":date")),req.params(":API")));
                }
            });        
            //Segundo get. Sirve en caso de solo buscar la empresa
            get("/:API/:value", (req,res)->{ 
                if(cache.existsCache(req.params(":value")+" TIME_SERIES_DAILY "+req.params(":API"))){
                    return cache.getInfoCache(req.params(":value")+" TIME_SERIES_DAILY "+req.params(":API"));
                }
                else{
                    cache.insertInCache(req.params(":value")+" TIME_SERIES_DAILY "+req.params(":API"), new StringBuffer(HttpConnection.getData(req.params(":value"), req.params(":API"))));               
                    return new StringBuffer(HttpConnection.getData(req.params(":value"), req.params(":API")));
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
