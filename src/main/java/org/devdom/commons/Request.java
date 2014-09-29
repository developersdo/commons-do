/*
 * The MIT License
 *
 * Copyright 2014 Developers Dominicanos.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.devdom.commons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.devdom.commons.exceptions.MalformedJSONException;
import org.devdom.commons.exceptions.RequesterInformationException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Clase para manejar las peticiones al API de http://data.developers.do
 * 
 * @author Carlos Vásquez Polanco
 * @since 0.0.1
 */
public class Request {

    private HttpURLConnection conn;
    private URL url;
    
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
    public JSONArray getJSONArrayResponse(String resourceLocation) 
            throws RequesterInformationException, MalformedJSONException{
    
        String response = getResponse(resourceLocation);

        if(!isValidJSONArrayString(response)){
            throw new MalformedJSONException("La respuesta no es un objeto JSON válido");
        }

        return parseJSONArray(response);
    }
    
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
    public JSONObject getJSONObjectResponse(String resourceLocation) 
            throws RequesterInformationException, MalformedJSONException {
        
        String response = getResponse(resourceLocation);

        if(!isValidJSONObjectString(response)){
            throw new MalformedJSONException("La respuesta no es un objeto JSON válido");
        }

        return parseJSONObject(response);

    }
    
    private void setConnection(String uri) throws IOException {
        url = new URL(uri);
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
    }
    
    /**
     * <p>Obtener un String a partir de la llamada al API de http://data.developers.do
     * 
     * <p>Aun así, el método puede ser utilizado para llamar recursos desde otros 
     * recursos siempre que retornen un JSON.
     * 
     * @param resourceLocation URL del recurso a ser consultado 
     * @return RAW String
     * @throws RequesterInformationException si hubo error en la recepción de información
     */
    protected String getResponse(String resourceLocation) 
            throws RequesterInformationException {

        try {
            setConnection(resourceLocation);
            if (conn.getResponseCode() != 200) {
                throw new RequesterInformationException("Http Error: "+conn.getResponseCode());
            }
        } catch (IOException e) {
            throw new RequesterInformationException(e.getMessage(),e);
        }
        
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            StringBuilder output = new StringBuilder();
            String outputString;

            while ((outputString = br.readLine()) != null) {
                output.append(outputString);
            }
            return output.toString();
        }catch(IOException ex){
            throw new RequesterInformationException(ex.getMessage(),ex);
        } finally {
            conn.disconnect();
        }
    }
    
    /**
     * Convetir a objeto JSONObject un string
     * @param response
     * @return Objeto JSONObject
     * @see JSONObject
     * @throws MalformedJSONException si hubo error en el formato o validación del JSON
     */
    public JSONObject parseJSONObject(String response) 
            throws MalformedJSONException{

        try {
            return (new JSONObject(response));
        } catch (JSONException ex) {
            throw new MalformedJSONException(ex.getMessage(),ex);
        }
    }
    
    /**
     * Convertir a objeto JSONAray un string
     * @param response
     * @return objeto JSONArray
     * @see JSONArray
     * @throws MalformedJSONException 
     */
    public JSONArray parseJSONArray(String response) 
            throws MalformedJSONException{
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
    
    /**
     * Validar si el string suplido puede ser un JSONObject válido
     * 
     * @param response
     * @return boolean
     */
    public boolean isValidJSONObjectString(String response){
        return (response.startsWith("{"));
    }

}
