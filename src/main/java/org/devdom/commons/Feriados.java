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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.devdom.commons.dto.Feriado;
import org.devdom.commons.exceptions.MalformedJSONException;
import org.devdom.commons.exceptions.RequesterInformationException;
import org.devdom.commons.util.Configuration;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Carlos Vásquez Polanco
 */
public class Feriados {
    
    private static final Request request = new Request();

    /**
     * 
     * @return
     * @throws RequesterInformationException
     * @throws MalformedJSONException
     * @throws ParseException 
     */
    public static ArrayList<Feriado> getList() 
            throws RequesterInformationException, MalformedJSONException, ParseException{
        
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        
        return getList(year);
    }

    /**
     * 
     * @param year
     * @return
     * @throws RequesterInformationException
     * @throws MalformedJSONException 
     * @throws java.text.ParseException 
     */
    public static ArrayList<Feriado> getList(int year) 
            throws RequesterInformationException, MalformedJSONException, ParseException{

        String url = Configuration.DATA_FERIADOS_URL + "/" + year + ".json";
        ArrayList<Feriado> list = new ArrayList<Feriado>();

        String json = request.getResponse(url);
        
        //Verificar el formato de la información retornada para parsearla
        if(request.isValidJSONArrayString(json)){
            JSONArray jsonArray = request.parseJSONArray(json);
            int len = jsonArray.length();
            
            /*
            Extraer todos los objetos y convertirlos a entidades de tipo feriado
            para añadirlos a la lista.
            */
            for(int i = 0; i<len; i++){
                try{
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    list.add(getFeriadoObject(jsonObject));
                    
                }catch(JSONException ex){
                    throw new MalformedJSONException(ex.getMessage(),ex);
                }
            }

        }else{
            throw new MalformedJSONException();
        }

        return list;
    }
    
    /**
     * 
     * @return
     * @throws RequesterInformationException
     * @throws MalformedJSONException 
     * @throws java.text.ParseException 
     */
    public static boolean isTodayHoliday() 
            throws RequesterInformationException, MalformedJSONException, ParseException{
        
        Calendar calendar = Calendar.getInstance();
        String currentDateString = Utils.getDateFormatted( calendar.getTime() );
        int year = calendar.get(Calendar.YEAR);
       
        ArrayList<Feriado> list = Feriados.getList(year);
        
        for(Feriado feriado : list){
            Date holiday = feriado.getFechaMovido();
            
            if(holiday==null){
                holiday = feriado.getFechaOriginal();
            }
            
            String holidayString = Utils.getDateFormatted(holiday);
            System.out.println("currentDateString = " + currentDateString);
            System.out.println("holidayString = " + holidayString);
            return (currentDateString.equals(holidayString));

        }
        return false;

    }

    /**
     * 
     * @param json
     * @return
     * @throws MalformedJSONException 
     */
    private static Feriado getFeriadoObject(JSONObject json) 
            throws MalformedJSONException, ParseException{

        try {
            int id = json.getInt("id");
            String fechaOriginalString = json.getString("fecha_original");
            String fechaMovidoString = json.getString("fecha_movido");
            
            Date fechaOriginal = json.isNull("fecha_original") ? null : Utils.convertStringToDate(fechaOriginalString);

            Date fechaMovido = json.isNull("fecha_movido") ? null : Utils.convertStringToDate(fechaMovidoString);

            String motivo = json.getString("motivo");

            return new Feriado(id,fechaOriginal,fechaMovido, motivo);
        } catch (JSONException ex) {
            throw new MalformedJSONException(ex.getMessage(),ex);
        }
    }
    
}
