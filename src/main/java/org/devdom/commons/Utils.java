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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
     * @param date
     * @return 
     */
    public static String getDateFormatted(Date date){
        return getDateFormatted(date, "dd/MM/yyyy");
    }
    
    /**
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
     * @param date
     * @return
     * @throws ParseException 
     */
    public static Date convertStringToDate(String date) throws ParseException{
        return convertStringToDate(date,"yyyy-MM-dd");
    }
    
    /**
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

}
