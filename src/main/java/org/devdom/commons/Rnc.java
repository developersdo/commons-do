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


import org.devdom.commons.exceptions.MalformedJSONException;
import org.devdom.commons.exceptions.RequesterInformationException;
import org.devdom.commons.dto.RNCObject;
import org.devdom.commons.type.FormatType;
import org.devdom.commons.util.Configuration;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Carlos Vásquez Polanco
 */
public class Rnc extends JsonGetter<RNCObject> {

    public Rnc(String baseUri, String charset) {
        super(Configuration.DATA_RNC_URL, "UTF-8");
    }

    /**
     * Ontener información a partir de una numeración de RNC
     * 
     * @param id
     * @return RNCObject
     * @see RNCObject con información de empresa en República Dominicana
     * @throws RequesterInformationException si hubo error en la recepción de información
     * @throws MalformedJSONException si hubo error en el formato o validación del JSON
     */
    public RNCObject get(String id) 
            throws RequesterInformationException {

        JSONObject json = getJSONObjectResponse(buildURL(FormatType.JSON, id));
        
        try{
            
            String objectId = json.getString("id");
            String document = json.getString("rnc");
            String name = json.getString("nombre");
            
            return new RNCObject(objectId,document,name);
        }catch(JSONException ex){
            throw new RequesterInformationException(ex.getMessage(),ex);
        }
    }

}
