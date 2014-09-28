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
import org.devdom.commons.exceptions.MalformedJSONException;
import org.devdom.commons.exceptions.RequesterInformationException;
import org.devdom.commons.dto.Municipio;
import org.devdom.commons.dto.Provincia;
import org.devdom.commons.util.Configuration;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Clase utilizada para manejar la información referente a Municipios de la 
 * República Dominicana.
 * 
 * @see Municipio
 * @see Provincia
 * @author Carlos Vásquez Polanco
 * @since 0.4.0
 */
public class Municipios {
    
    private static final Request request = new Request();

    /**
     * 
     * Retorna una lista de objetos de tipo Municipio con la referencia al 
     * objeto Provincia a la que pertenece.
     * 
     * @return ArrayList de objetos Municipio
     * @throws RequesterInformationException si hubo error en la recepción de información
     * @throws MalformedJSONException si hubo error en el formato o validación del JSON
     * @see Municipio
     * @see Provincia
     */
    public static ArrayList<Municipio> getList() 
            throws RequesterInformationException, MalformedJSONException{

        String url = Configuration.DATA_MUNICIPIOS_URL + ".json";
        ArrayList<Municipio> list = new ArrayList<Municipio>();
        
        
        String json = request.getResponse(url);
        
        //Verificar el formato de la información retornada para parsearla
        if(request.isValidJSONArrayString(json)){
            JSONArray jsonArray = request.parseJSONArray(json);
            int len = jsonArray.length();
            
            /*
            Extraer todos los objetos y convertirlos a entidades de municipios
            para añadirlos a la lista.
            */
            for(int i = 0; i<len; i++){
                try{
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    list.add(getMunicipioObject(jsonObject));
                    
                }catch(JSONException ex){
                    throw new MalformedJSONException(ex.getMessage(),ex);
                }
            }

        }else if(request.isValidJSONObjectString(json)){
            JSONObject jsonObject = request.parseJSONObject(json);
            list.add(getMunicipioObject(jsonObject));
        }

        return list;
    }
    
    /**
     * Obtener la información de un municipio
     * 
     * @param id id del objeto del Municipio que se desea obtener
     * @return objeto Municipio
     * @see Municipio
     * @throws RequesterInformationException si hubo error en la recepción de información
     * @throws MalformedJSONException si hubo error en el formato o validación del JSON
     */
    public static Municipio get(int id) 
            throws RequesterInformationException, MalformedJSONException{
        
        String url = Configuration.DATA_MUNICIPIOS_URL +"/"+ id + ".json";
        
        String json = request.getResponse(url);
        
        if(request.isValidJSONArrayString(json)){
            // si la llamada retorna más de un elemento, da un error de documento mal formado
            throw new MalformedJSONException();
        }
        
        return getMunicipioObject( request.parseJSONObject(json) );

    }
    
    /**
     * 
     * @param json RAW del JSON recibido para ser formateado
     * @return objeto Municipio
     * @see Municipio
     * @see Provincia
     * @throws MalformedJSONException si hubo error en el formato o validación del JSON
     */
    private static Municipio getMunicipioObject(JSONObject json) 
            throws MalformedJSONException{
        
        try {
            Municipio municipio = new Municipio(json.getInt("id"));
            municipio.setName(json.getString("nombre"));
             
            JSONObject jsonProvincia = json.getJSONObject("provincia");
             
            Provincia provincia = new Provincia(jsonProvincia.getInt("id"));
            provincia.setNombre(jsonProvincia.getString("nombre"));
            
            municipio.setProvincia(provincia);

            return municipio;
        } catch (JSONException ex) {
            throw new MalformedJSONException(ex.getMessage(),ex);
        }
    }
    
}
