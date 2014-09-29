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

import java.util.ArrayList;
import java.util.List;

import org.devdom.commons.exceptions.MalformedJSONException;
import org.devdom.commons.exceptions.RequesterInformationException;
import org.devdom.commons.dto.Provincia;
import org.devdom.commons.type.FormatType;
import org.devdom.commons.util.Configuration;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Clase Utilizada para manejar la información referente a provincias de la 
 * República Dominicana.
 * 
 * @author Carlos Vásquez Polanco
 * @since 0.3.0
 */
public class Provincias extends Listable<Provincia> {
    
    public Provincias() {
        super(Configuration.DATA_PROVINCIAS_URL, "UTF-8");
    }

    /**
     * Listado de provincias de la República Dominiciana
     * 
     * @return ArrayList de objetos Provincia 
     * @see Provincia
     * @throws RequesterInformationException si hubo error en la recepción de información
     * @throws MalformedJSONException si hubo error en el formato o validación del JSON
     */
    public List<Provincia> getList() 
            throws RequesterInformationException {

        ArrayList<Provincia> list = new ArrayList<Provincia>();
        String json = getResponse(buildURL(FormatType.JSON));
        
        //Verificar el formato de la información retornada para parsearla
        if(isValidJSONArrayString(json)){
            JSONArray jsonArray = parseJSONArray(json);
            int len = jsonArray.length();
            
            /*
            Extraer todos los objetos y convertirlos a entidades de provincias
            para añadirlos a la lista.
            */
            for(int i = 0; i<len; i++){
                try{
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    list.add(getProvinciaObject(jsonObject));
                    
                }catch(JSONException ex){
                    throw new MalformedJSONException(ex.getMessage(),ex);
                }
            }

        }else if(isValidJSONObjectString(json)){
            JSONObject jsonObject = parseJSONObject(json);
            list.add(getProvinciaObject(jsonObject));
        }

        return list;
    }
    
    /**
     * Obtener la información de una provincia
     * 
     * @param id id del objeto de provincia que se desea obtener
     * @return objeto Provincia
     * @see Provincia
     * @throws RequesterInformationException si hubo error en la recepción de información
     * @throws MalformedJSONException si hubo error en el formato o validación del JSON
     */
    public Provincia get(String id) 
            throws RequesterInformationException, MalformedJSONException{
        
        String json = getResponse(buildURL(FormatType.JSON, id));
        
        if(isValidJSONArrayString(json)){
            // si la llamada retorna más de un elemento, da un error de documento mal formado
            throw new MalformedJSONException();
        }
        
        return getProvinciaObject(parseJSONObject(json) );

    }
    
    /**
     * 
     * @param json RAW del JSON recibido para ser formateado
     * @return objeto Provincia
     * @see Provincia
     * @throws MalformedJSONException si hubo error en el formato o validación del JSON
     */
    private static Provincia getProvinciaObject(JSONObject json) 
            throws MalformedJSONException{

        try {
            return new Provincia(json.getInt("id"), json.getString("nombre"));
        } catch (JSONException ex) {
            throw new MalformedJSONException(ex.getMessage(),ex);
        }
    }
}
