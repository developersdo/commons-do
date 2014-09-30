package org.devdom.commons;

import java.text.ParseException;
import java.util.List;

import org.devdom.commons.exceptions.MalformedJSONException;
import org.devdom.commons.exceptions.RequesterInformationException;
import org.json.JSONArray;
import org.json.JSONException;

public abstract class Listable<T> extends JsonGetter<T> {

    public Listable(String baseUri, String charset) {
        super(baseUri, charset);
    }
    
    public abstract List<T> getList() throws RequesterInformationException, ParseException;
    
    /**
     * <p>Obtener un JSONArray a partir de la llamada al API de http://data.developers.do
     * 
     * <p>Aun así, el método puede ser utilizado para llamar recursos desde otros 
     * recursos siempre que retornen un JSON.
     * 
     * @param resourceLocation URL del recurso a ser consultado 
     * @return Objeto JSONArray
     * @throws RequesterInformationException si hubo error en la recepción de información
     * @throws MalformedJSONException si hubo error en el formato o validación del JSON
     */
    public JSONArray getJSONArrayResponse(String response) 
            throws RequesterInformationException{
    
        if(!isValidJSONArrayString(response)){
            throw new MalformedJSONException("La respuesta no es un objeto JSON válido");
        }

        return parseJSONArray(response);
    }
    
    
    /**
     * Convertir a objeto JSONAray un string
     * @param response
     * @return objeto JSONArray
     * @see JSONArray
     * @throws MalformedJSONException 
     */
    public JSONArray parseJSONArray(String response) {
        try {
            return (new JSONArray(response));
        } catch (JSONException ex) {
            throw new MalformedJSONException(ex.getMessage(),ex);
        }
    }
    /**
     * Validar si el string suplido puede ser un JSONArray válido
     * 
     * @param response
     * @return boolean
     */
    public boolean isValidJSONArrayString(String response){
        return (response.startsWith("[{"));
    }
    

}
