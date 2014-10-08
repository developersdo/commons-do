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

package org.devdom.commons.util;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author Carlos Vásquez Polanco
 */
public class Utils {
    
    /**
     * 
     * @param value
     * @return 
     */
    public static boolean hasOnlyDigits(String value){
        return value.matches("[0-9]+");
    }
    
    /**
     * 
     * Extraer fecha formateada
     * 
     * @param date
     * @return 
     */
    public static String getDateFormatted(Date date){
        return getDateFormatted(date, "dd/MM/yyyy");
    }
    
    /**
     * 
     * Extraer fecha formateada, indicando cual es el formato 
     * 
     * @param date
     * @param format
     * @return 
     */
    public static String getDateFormatted(Date date, String format){
        DateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    /**
     * 
     * Método utilizado para convertir un objeto Strign en un objeto fecha. 
     * 
     * @param date
     * @return
     * @throws ParseException 
     */
    public static Date convertStringToDate(String date) throws ParseException{
        return convertStringToDate(date,"yyyy-MM-dd");
    }
    
    /**
     * 
     * Método utilizado para convertir un objeto Strign en un objeto fecha 
     * indicando el formato en el segundo parámetro.
     * 
     * @param date
     * @param format
     * @return
     * @throws ParseException 
     */
    public static Date convertStringToDate(String date, String format) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(date);
    }


    public static InputStream httpGet(String url, Map<String, String> headers) throws Exception {
        InputStream result = null;
        HttpGet get = new HttpGet(url);

        if(headers != null){
            for(Entry<String, String> header : headers.entrySet()){
                get.addHeader(header.getKey(), header.getValue());
            }
        }

        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse httpResponse = client.execute(get);

        if(httpResponse.getEntity() != null){
            result = httpResponse.getEntity().getContent();
        }

        return result;
    }

    public static void close(InputStream... inputStreams){
        for(InputStream input : inputStreams){
            try{
                input.close();
            }catch(Exception e){
                //ignore
            }
        }
    }
}
