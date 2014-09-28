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
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import org.devdom.commons.exceptions.MalformedJSONException;
import org.devdom.commons.exceptions.RequesterInformationException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Carlos Vásquez Polanco
 */
public class Request {

    private HttpURLConnection conn;
    private URL url;
    
    /**
     * 
     * @param resourceLocation
     * @return
     * @throws RequesterInformationException
     * @throws MalformedJSONException 
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
     * 
     * @param resourceLocation
     * @return
     * @throws RequesterInformationException
     * @throws MalformedJSONException 
     */
    public JSONObject getJSONObjectResponse(String resourceLocation) 
            throws RequesterInformationException, MalformedJSONException {
        
        String response = getResponse(resourceLocation);

        if(!isValidJSONObjectString(response)){
            throw new MalformedJSONException("La respuesta no es un objeto JSON válido");
        }

        return parseJSONObject(response);

    }
    
    /**
     * 
     * @param resourceLocation
     * @return
     * @throws RequesterInformationException 
     */
    protected String getResponse(String resourceLocation) 
            throws RequesterInformationException {

        try {
            url = new URL(resourceLocation);
        } catch (MalformedURLException ex) {
            throw new RequesterInformationException(ex.getMessage(),ex);
        }

        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException ex) {
            throw new RequesterInformationException(ex.getMessage(),ex);
        }

        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException ex) {
            throw new RequesterInformationException(ex.getMessage(),ex);
        }

        conn.setRequestProperty("Accept", "application/json");

        try{
            if (conn.getResponseCode() != 200) {
                throw new RequesterInformationException("Http Error: "+conn.getResponseCode());
            }
        }catch(IOException ex){
            throw new RequesterInformationException(ex.getMessage(),ex);
        }

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String outputString;

            JSONArray ar;
            while ((outputString = br.readLine()) != null) {
                return outputString;
            }
        }catch(IOException ex){
            throw new RequesterInformationException(ex.getMessage(),ex);
        }

        conn.disconnect();

        return null;
    }
    
    /**
     * 
     * @param response
     * @return 
     * @throws org.devdom.commons.exceptions.MalformedJSONException 
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
     * 
     * @param response
     * @return
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
     * 
     * @param response
     * @return 
     */
    public boolean isValidJSONArrayString(String response){
        return (response.startsWith("[{"));
    }
    
    /**
     * 
     * @param response
     * @return 
     */
    public boolean isValidJSONObjectString(String response){
        return (response.startsWith("{"));
    }

}
