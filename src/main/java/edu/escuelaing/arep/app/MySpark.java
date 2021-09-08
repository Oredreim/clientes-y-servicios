package edu.escuelaing.arep.app;
import java.util.HashMap;
import java.util.Map;
/**
 * Clase encargada de realizar una implementacion muy similar al funcionamiento del framework Spark, el cual se ofrecen metodos get y post de los endpoints que se encuentran disponibles, los cuales se encuentran almacenados en un Hashmap.
 * @author Cristian Piñeros
 * @version 1.0.  (07 de Septiembre del 2021)
 */
public class MySpark {
    private static Map<String, String> endpoints = new HashMap<>();
    /**
     * Metodo get que se encarga de obtener todos los endpoints del aplicativo web.
     * @param key Parametro que indica la llave de los endpoints respectivamente.
     * @return Retorna todos los endpoints con sus respectivas llaves.
     */
    public static String get(String key){
        if(endpoints.containsKey(key)){
            return endpoints.get(key);
        }
        return null;
    }
    /**
     * Metodo post que se encarga de realizar los respectivos posts de cada endpoint.
     * @param key Parametro que indica la llave de los endpoints respectivamente.
     * @param value Parametro que indica el valor a colocar a su respectivo endpoint.
     */
    public static void post(String key, String value){
        endpoints.put(key,value);
    }
}