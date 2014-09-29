package org.devdom.commons;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;

import org.devdom.commons.exceptions.MalformedJSONException;
import org.devdom.commons.exceptions.RequesterInformationException;
import org.devdom.commons.service.Requester;
import org.devdom.commons.type.FormatType;
import org.json.JSONException;
import org.json.JSONObject;



public abstract class JsonGetter<T> extends Requester {

    private final String BASE_URL;
    
    public JsonGetter(String baseUri, String charset) {
        super(charset);
        this.BASE_URL = baseUri;
    }
    
    public abstract T get(String id) throws RequesterInformationException, ParseException;
    
    /**
     * <p>Obtener un JSONObject a partir de la llamada al API de http://data.developers.do
     * 
     * <p>Aun así, el método puede ser utilizado para llamar recursos desde otros 
     * recursos siempre que retornen un JSON.
     * 
     * @param resourceLocation URL del recurso a ser consultado 
     * @return Objeto JSONObject
     * @throws RequesterInformationException si hubo error en la recepción de información
     * @throws MalformedJSONException si hubo error en el formato o validación del JSON
     */
    public JSONObject getJSONObjectResponse(String response) 
            throws RequesterInformationException {
        
        if(!isValidJSONObjectString(response)){
            throw new MalformedJSONException("La respuesta no es un objeto JSON válido");
        }

        return parseJSONObject(response);

    }

    
    /**
     * Convetir a objeto JSONObject un string
     * @param response
     * @return Objeto JSONObject
     * @see JSONObject
     * @throws MalformedJSONException si hubo error en el formato o validación del JSON
     */
    public JSONObject parseJSONObject(String response) {

        try {
            return (new JSONObject(response));
        } catch (JSONException ex) {
            throw new MalformedJSONException(ex.getMessage(),ex);
        }
    }

    /**
     * Validar si el string suplido puede ser un JSONObject válido
     * 
     * @param response
     * @return boolean
     */
    public boolean isValidJSONObjectString(String response){
        return (response.startsWith("{"));
    }
    
    /**
     * Retorna la url con las partes agregadas. 
     * @param format
     *          Formato de la respuesta esperada. 
     * @param parts
     *          Las partes de la dirección http a ser agregadas.
     * @return
     *      URL bien formada http://baseurl.com/part1/part2.format
     */
    public String buildURL(FormatType format, String... parts) {
        URI uri;
        StringBuilder uriString = new StringBuilder(BASE_URL);
        for (String part : parts) {
            uriString.append("/").append(part);
        }
        try {
            uri = new URI(uriString.toString() + format.getExtension());
        } catch (URISyntaxException e) {
            throw new MalformedJSONException(e.getMessage());
        }
        return uri.toString();
    }
}
