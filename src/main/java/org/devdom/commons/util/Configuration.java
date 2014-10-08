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

/**
 *
 * @author Carlos Vásquez Polanco
 * @since 0.0.1
 */
public interface Configuration {
    //URI del API de Developers Dominicanos
    String DATA_DEVDO_URI_V1 = "http://data.developers.do/api/v1/";
    
    String DATA_RNC_URL = DATA_DEVDO_URI_V1 + "empresas/";
    
    String DATA_PROVINCIAS_URL = DATA_DEVDO_URI_V1 + "provincias";
    
    String DATA_MUNICIPIOS_URL = DATA_DEVDO_URI_V1 + "municipios";

    String DATA_FERIADOS_URL = DATA_DEVDO_URI_V1 + "feriados";
    
    String DR_TIME_ZONE = "Etc/GMT+4";
}
