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
import java.util.List;
import java.util.TimeZone;

import org.devdom.commons.dto.Feriado;
import org.devdom.commons.exceptions.MalformedJSONException;
import org.devdom.commons.exceptions.RequesterInformationException;
import org.devdom.commons.type.FormatType;
import org.devdom.commons.util.Configuration;
import org.devdom.commons.util.Utils;
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
public class Feriados extends Listable<Feriado> {
    
    public Feriados() {
        super(Configuration.DATA_FERIADOS_URL, "UTF-8");
    }
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
    @Override
    public List<Feriado> getList() throws RequesterInformationException, ParseException{
        
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
    public List<Feriado> getList(int year) 
            throws RequesterInformationException, ParseException{

        String json = getResponse(buildURL(FormatType.JSON, String.valueOf(year)));
        List<Feriado> list = new ArrayList<Feriado>();
        
        //Verificar el formato de la información retornada para parsearla
        JSONArray jsonArray = parseJSONArray(json);
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

        return list;
    }
    
    /**
     * 
     * <p>Método utilizado para saber si el día en curso es feriado.
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
    public boolean isTodayHoliday() 
            throws RequesterInformationException, ParseException{
        
        TimeZone timeZone = TimeZone.getTimeZone(Configuration.DR_TIME_ZONE);
        Calendar calendar = Calendar.getInstance(timeZone);
        int currentDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        int year = calendar.get(Calendar.YEAR);

        List<Feriado> list = getList(year);
        
        for(Feriado feriado : list){
            Date holiday = feriado.getFechaMovido();
            
            if(holiday==null){
                holiday = feriado.getFechaOriginal();
            }

            Calendar holidayCalendar = Calendar.getInstance();
            holidayCalendar.setTime(holiday);
            int holidayDayOfYear = holidayCalendar.get(Calendar.DAY_OF_YEAR);

            if(currentDayOfYear == holidayDayOfYear){
                return true;
            }

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
            Date fechaOriginal = json.isNull("fecha_original") ? null : Utils.convertStringToDate(json.getString("fecha_original"));
            Date fechaMovido = json.isNull("fecha_movido") ? null : Utils.convertStringToDate(json.getString("fecha_movido"));
            String motivo = json.getString("motivo");

            return new Feriado(id,fechaOriginal,fechaMovido, motivo);
        } catch (JSONException ex) {
            throw new MalformedJSONException(ex.getMessage(),ex);
        }
    }
    
    /**
     * Obtener objeto Feriado según la posición solicitada
     * 
     * @param id posición de día feriado
     * @return Objeto Feriado
     * @see Feriado
     * @throws RequesterInformationException si hubo error en la recepción de información
     * @throws ParseException si hubo error de parseo
     */
    public Feriado get(int id) 
            throws RequesterInformationException, ParseException {
        
        Calendar calendar = Calendar.getInstance();
        
        return this.get(id, calendar.get(Calendar.YEAR));
    }
    
    /**
     * Obtener objeto Feriado según la posición solicitada
     * 
     * @param id posición de día feriado
     * @param year año deseado
     * @return Objeto Feriado
     * @see Feriado
     * @throws RequesterInformationException si hubo error en la recepción de información
     * @throws ParseException si hubo error de parseo
     */
    public Feriado get(int id, int year) 
            throws RequesterInformationException, ParseException{
        
        for(Feriado feriado : this.getList(year)){
            if(feriado.getId()==id){
                return feriado;
            }
        }
        return null;
    }

    /**
     * Obtener objeto Feriado según la posición solicitada
     * 
     * @param id posición de día feriado
     * @return Objeto Feriado
     * @see Feriado
     * @throws RequesterInformationException si hubo error en la recepción de información
     * @throws ParseException si hubo error de parseo
     * @throws NumberFormatException si hubo un intento de pasar caracteres por valores numéricos
     */
    @Override
    public Feriado get(String id) 
            throws RequesterInformationException, ParseException, NumberFormatException {
        
        if(!Utils.hasOnlyDigits(id)){
            throw new NumberFormatException("Solo se permiten dígitos");
        }
        
        return get(Integer.parseInt(id));
    }
    
}
