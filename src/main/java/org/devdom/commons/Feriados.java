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
 * Clase utilizada para manejar los días feriados en la República Dominicana
 * para el año en curso.
 * 
 * @author Carlos Vásquez Polanco
 * @see Feriado
 * @since 0.5.1
 */
public class Feriados {
    
    private static final Request request = new Request();

    /**
     * 
     * Listado de días feriados según el Ministerio de Trabajo de la República Dominicana
     * 
     * @return ArrayList de objetos Feriado
     * @see Feriado
     * @throws RequesterInformationException si hubo error en la recepción de información
     * @throws MalformedJSONException si hubo error en el formato o validación del JSON
     * @throws ParseException si hubo error de parseo
     */
    public static ArrayList<Feriado> getList() 
            throws RequesterInformationException, MalformedJSONException, ParseException{
        
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        
        return getList(year);
    }

    /**
     * 
     * <p>Listado de días feriados según el Ministerio de Trabajo de la República Dominicana.
     * El método recibe el año que se desea evaluar.
     * 
     * <p>Este método aun funciona con el año en curso, de intentar ver un año pasado será 
     * lanzada una excepción. El método fue pensado para cuando esta restricción del 
     * servicio sea liberada.
     * 
     * @param year año a ser evaluado
     * @return ArrayList de objetos Feriado
     * @see Feriado
     * @throws RequesterInformationException si hubo error en la recepción de información
     * @throws MalformedJSONException si hubo error en el formato o validación del JSON
     * @throws ParseException si hubo error de parseo 
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
     * <p>Método utilizado para saber si día en curso es feriado.
     * 
     * <p>Primero es verificada la fecha a la que fue movida por disposición
     * de la ley Dominicana. Si la fecha festiva no es movida se revisa su fecha
     * original.
     * 
     * @return boolean que determina si el día actual es feriado
     * @throws RequesterInformationException si hubo error en la recepción de información
     * @throws MalformedJSONException si hubo error en el formato o validación del JSON
     * @throws ParseException si hubo error de parseo
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

            return (currentDateString.equals(holidayString));

        }
        return false;

    }

    /**
     * 
     * Obtener la definición de un día feriado según el Objeto JSON suplido
     * 
     * @param json RAW del JSON recibido para ser formateado
     * @return Objeto Feriado
     * @see Feriado
     * @throws MalformedJSONException si hubo error en el formato o validación del JSON
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
