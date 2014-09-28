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

/**
 *
 * @author Carlos Vásquez Polanco
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
     * @param part
     * @return 
     * @throws org.devdom.commons.exceptions.DocumentFormatException 
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
     * @param part1
     * @param part2
     * @param part3
     * @return
     * @throws DocumentFormatException 
     */
    public static boolean isValid(String part1, String part2, String part3) 
            throws DocumentFormatException{

        String documentId = part1+part2+part3;
        
        return isValid(documentId);
    } 

    /**
     * 
     * Método utilizado para verificar si una cédula de la República Dominicana
     * es válida.
     * 
     * @param documentId
     * @return
     * @throws DocumentFormatException 
     */
    public static boolean isValid(String documentId) 
            throws DocumentFormatException{

        final char[] weight = {'1','2','1','2','1','2','1','2','1','2'};
        int mod = 0;
        int div = 0;
        
        documentId = documentId.replace("-", "");

        int len = documentId.length();

        if(!Utils.hasOnlyDigits(documentId)){
            throw new DocumentFormatException("Documento incorrecto, solo números o guión como caracteres");
        }
        
        if(len != 11){
            throw new DocumentFormatException("Logitud de cédula incorrecto");
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
     * @param documentId
     * @return 
     */
    public static String mask(String documentId){   
        
        final StringBuilder masked = new StringBuilder(13);
        final char[] words = documentId.toCharArray();
        final int len = words.length;

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
     * @param documentId
     * @return 
     */
    public static String[] split(String documentId){
        return split(documentId,"-");
    }
    
    /**
     * 
     * Método utilitario para cuando necesites retornar la cédula separada en partes
     * espeficando el separador a utilizar.
     * 
     * @param documentId
     * @param sep
     * @return 
     */
    public static String[] split(String documentId, String sep){
        return documentId.split(sep);
    }
}
