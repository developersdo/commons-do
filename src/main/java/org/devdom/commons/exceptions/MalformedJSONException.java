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

package org.devdom.commons.exceptions;

/**
 * <p>Clase utilizada para manejar errores atrapados referente a un mal formato del
 * de un documento o respuesta JSON.
 * </p>
 * 
 * <p>También es usado en casos de retorno de información un formato no deseado.
 * 
 * @author Carlos Vásquez Polanco
 * @since 0.3.1
 */
public class MalformedJSONException extends RuntimeException {
    
    private static final long serialVersionUID = 8252298540518426119L;

    /**
     * Mensaje por defecto al capturar una exception "<i>documento JSON mal formado</i>"
     * 
     */
    public MalformedJSONException(){
        super("documento JSON mal formado");
    }

    public MalformedJSONException(String message){
        super(message);
    }
    
    public MalformedJSONException(String message, Throwable cause){
        super(message,cause);
    }

}
