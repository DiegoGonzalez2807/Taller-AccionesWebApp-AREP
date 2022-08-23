package edu.escuelaing.arem.Cache;

/**
 * @author Diego Gonzalez
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class cacheData {

    //Inicializacion cache HashMap
    Map<String, StringBuffer> cacheBolsa = new HashMap<String, StringBuffer>();

    /**
     * Funcion generada para revisar si en el arreglo de hashmap existe 
     * la llave de la empresa, la cual se define como empresa + fecha
     * ejemplo: MSFT TIME_SERIES_DAILY
     * @param llaveEmpresa
     * @return -> Boolean
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
     * @param llaveEmpresa
     * @return -> JSON informacion de la empresa
     */
    public  StringBuffer getInfoCache(String llaveEmpresa){
        return cacheBolsa.get(llaveEmpresa);
    }

    /**
     * Funcion generada para insertar valores dentro del cache
     * de la bolsa de valores
     * @param llaveEmpresa
     * @param infoJSON
     */
    public void insertInCache(String llaveEmpresa, StringBuffer infoJSON){
        cacheBolsa.put(llaveEmpresa, infoJSON);
    }

    public Set<String> getKeys(){
        return cacheBolsa.keySet();
    }

}
