package edu.escuelaing.arem.Cache;

/**
 * @author Diego Gonzalez
 */

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class cacheData {

    //Inicializacion cache HashMap
    ConcurrentHashMap<String, StringBuffer> cacheBolsa = new ConcurrentHashMap<String, StringBuffer>();

    /**
     * Funcion generada para revisar si en el arreglo de hashmap existe 
     * la llave de la empresa, la cual se define como empresa + fecha
     * ejemplo: MSFT TIME_SERIES_DAILY
     * @param llaveEmpresa String con nombre de la empresa + fecha + API
     * @return Boolean 
     */
    public  boolean existsCache(String llaveEmpresa){ 
        if(cacheBolsa.keySet().contains(llaveEmpresa)){
            return true;
        }
        return false;
    }

    /**
     * Funcion generada para retornar el valor de llave 
     * de la empresa y su fecha de acuerdo a las especificaciones
     * del usuario
     * @param llaveEmpresa String con nombre de la empresa + fecha + API
     * @return  JSON informacion de la empresa
     */
    public  StringBuffer getInfoCache(String llaveEmpresa){
        return cacheBolsa.get(llaveEmpresa);
    }

    /**
     * Funcion generada para insertar valores dentro del cache
     * de la bolsa de valores
     * @param llaveEmpresa String con nombre de la empresa + fecha + API
     * @param infoJSON StringBuffer con la info traida de la API
     */
    public void insertInCache(String llaveEmpresa, StringBuffer infoJSON){
        cacheBolsa.put(llaveEmpresa, infoJSON);
    }

    /**
     * Funcion generada para retornar todas las llaves guardadas o creadas
     * en el ConcurrentHashMap
     * @return Set llaves del hashmap
     */
    public Set<String> getKeys(){
        return cacheBolsa.keySet();
    }

}
