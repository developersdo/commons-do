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
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Ronny Placencia
 */
public class CedulaTest {
    
    private final String documentId = "XXX-XXXXXXX-X";
    
    public CedulaTest() {
    }

    /**
     * Verificación de si el método isValid method de la clase Cedula retorna true ante
     * un documento válido.
     * 
     * @throws org.devdom.commons.exceptions.DocumentFormatException
     */
    @Test
    public void testIsValid_StringArr() throws DocumentFormatException {
        assertTrue("La cédula inválida",Cedula.isValid(documentId));
    }

    /**
     * Verificación de si el método isValid method de la clase Cedula retorna true ante
     * un documento válido pasado en 3 partes
     * 
     * @throws org.devdom.commons.exceptions.DocumentFormatException
     */
    @Test
    public void testIsValid_3args() throws DocumentFormatException {
        String[] partes = documentId.split("-");
        assertTrue("La cédula inválida",Cedula.isValid(partes));
    }
    
}
