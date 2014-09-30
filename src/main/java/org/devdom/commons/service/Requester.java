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

package org.devdom.commons.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.devdom.commons.exceptions.RequesterInformationException;
import org.devdom.commons.type.FormatType;

/**
 * Clase para manejar las peticiones al API de http://data.developers.do
 * 
 * @author Carlos Vásquez Polanco
 * @since 0.0.1
 */
public class Requester {

    private HttpURLConnection conn;
    private URL url;
    private final String charset;
    
    public Requester(String charset) {
        this.charset = charset;
    }

    private void setConnection(String uri, String charset, FormatType type) throws IOException {
        url = new URL(uri);
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Accept-Charset", charset);
        conn.setRequestMethod("GET");
        switch (type) {
            case XML:
                conn.setRequestProperty("Accept", "application/xml");
                break;
            case JSON:
            default:
                conn.setRequestProperty("Accept", "application/json");        
        }
        
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
    protected String getResponse(String resourceLocation) throws RequesterInformationException {
        return getResponse(resourceLocation, charset, FormatType.JSON);
    }
    
    protected String getResponse(String resourceLocation, FormatType type) throws RequesterInformationException {
        return getResponse(resourceLocation, charset, type);
    }
    
    /**
     * <p>Obtener un String a partir de la llamada al API de http://data.developers.do
     * 
     * <p>Aun así, el método puede ser utilizado para llamar recursos desde otros 
     * recursos siempre que retornen un JSON.
     * 
     * @param resourceLocation URL del recurso a ser consultado
     * @param charset Character set to use to interpret the response from server
     * @return RAW String
     * @throws RequesterInformationException si hubo error en la recepción de información
     */
    protected String getResponse(String resourceLocation, String charset, FormatType type) 
            throws RequesterInformationException {

        try {
            setConnection(resourceLocation, charset, type);
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
    


}
