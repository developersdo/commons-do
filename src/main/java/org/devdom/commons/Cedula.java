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

import org.devdom.commons.exceptions.DocumentFormatException;
import org.devdom.commons.util.Utils;

/**
 * Clase para manejar validaciones y formatos de la cédula de la República Dominicana.
 * 
 * @author Carlos Vásquez Polanco
 * @since 0.0.1
 */
public final class Cedula {
    
    /**
     *
     */
    public Cedula(){
        throw new AssertionError("La clase no debe ser instanciada");
    }
    
    /**
     * 
     * Método utilizado para verificar si una cédula de la República Dominicana
     * es válida. Para verificar debe ser pasado un arreglo de 3 partes.
     * 
     * @param part documento suplido en un arreglo de 3 partes
     * @return valor boolean que dice si el formato del documento es válido
     * @throws DocumentFormatException si se pasan valores incorrectos o fuera de longitud 
     */
    public static boolean isValid(String[] part) 
            throws DocumentFormatException{
        
        int len = part.length;
        String documentId = ""; 

        if(len!=3){
            throw new DocumentFormatException("Deben pasarse 3 partes para formar la cédula");
        }

        for(int i=0;i<len;i++){
            documentId += part[i];
        }

        return isValid(documentId);
    }
    
    /**
     * 
     * Método utilizado para verificar si una cédula de la República Dominicana
     * es válida. Verifica pasando 3 parámetros.
     * 
     * @param part1 primera parte a ser suplida para formar el documento
     * @param part2 segunda parte a ser suplida para formar el documento
     * @param part3 tercera parte a ser suplida para formar el documento
     * @return valor boolean que dice si el formato del documento es válido
     * @throws DocumentFormatException si se pasan valores incorrectos o fuera de longitud 
     */
    public static boolean isValid(String part1, String part2, String part3) 
            throws DocumentFormatException{

        String documentId = part1+part2+part3;
        
        return isValid(documentId);
    } 

    /**
     * 
     * Método utilizado para verificar si el valor suplido en el parámetros,
     * es válido según el formato de cédula de la República Dominicana.
     * 
     * @param documentId numeración reference a la cédula de la Repúblic Dominicana
     * @return valor boolean que dice si el formato del documento es válido
     * @throws DocumentFormatException si se pasan valores incorrectos o fuera de longitud 
     */
    public static boolean isValid(String documentId) 
            throws DocumentFormatException{

        final char[] weight = {'1','2','1','2','1','2','1','2','1','2'};
        int mod = 0;
        int div;
        
        documentId = documentId.replace("-", "");

        int len = documentId.length();

        if(!Utils.hasOnlyDigits(documentId)){
            throw new DocumentFormatException("Documento incorrecto, solo números o guión como caracteres");
        }
        
        if(len != 11){
            throw new DocumentFormatException("Logitud de cédula incorrecta");
        }

        for(int i = 0; i < 10; i++){
            int mult = (documentId.charAt(i)-'0') * (weight[i]-'0');
            while(mult > 0) {
                mod += mult%10;
                mult /= 10;
            }
        }
        
        if( (div = (mod / 10) * 10) < mod ){
            div += 10;
        }

        return (div - mod) == documentId.charAt(10) - '0';
    }
    
    /**
     * 
     * Método utilitario para cuando se necesite retornar una cédula introducida
     * en el formato con guiones utilizado en la República Dominicana.
     * 
     * @param documentId numeración reference a la cédula de la Repúblic Dominicana
     * @return documento separado por dos guiones
     * @throws DocumentFormatException si se pasan valores incorrectos o fuera de longitud
     */
    public static String mask(String documentId) 
            throws DocumentFormatException{   
        
        final StringBuilder masked = new StringBuilder(13);
        final char[] words = documentId.toCharArray();
        final int len = words.length;

        if(len != 11){
            throw new DocumentFormatException("Longitud de cédula incorrecta");
        }
        
        for(int i=0; i<len;i++){
            if(i==3 || i==10){
                masked.append("-");
            }
            masked.append(words[i]);
        }
        return masked.toString();
    }
    
    /**
     * 
     * Método utilitario para cuando necesites retornar la cédula separada en partes.
     * 
     * @param documentId numeración reference a la cédula de la Repúblic Dominicana
     * @return returna el documento separado en un arreglo de String
     */
    public static String[] split(String documentId){
        return split(documentId,"-");
    }
    
    /**
     * 
     * Método utilitario para cuando necesites retornar la cédula separada en partes
     * espeficando el separador a utilizar.
     * 
     * @param documentId numeración reference a la cédula de la Repúblic Dominicana
     * @param sep separador a ser utilizado
     * @return returna el documento separado en un arreglo de String
     */
    public static String[] split(String documentId, String sep){
        return documentId.split(sep);
    }
}
