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
import org.devdom.commons.util.Configuration;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Carlos Vásquez Polanco
 */
public class Rnc {

    /**
     * 
     * @param documentId
     * @return
     * @throws RequesterInformationException
     * @throws MalformedJSONException 
     */
    public static RNCObject getInformation(String documentId) 
            throws RequesterInformationException, MalformedJSONException{

        String url = Configuration.DATA_RNC_URL + documentId + ".json";
        
        Request request = new Request();
        
        JSONObject json = request.getJSONObjectResponse(url);
        
        try{
            
            String id = json.getString("id");
            String document = json.getString("rnc");
            String name = json.getString("nombre");
            
            return new RNCObject(id,document,name);
            
        }catch(JSONException ex){
            throw new RequesterInformationException(ex.getMessage(),ex);
        }
    }    
}
