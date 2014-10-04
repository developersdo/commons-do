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

import org.devdom.commons.dto.Combustible;
import org.devdom.commons.exceptions.MalformedXMLException;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Carlos Vásquez Polanco
 */
public class CombustiblesTest {
    
    private Combustibles gasolina;
    private Combustible combustible;
    
    /**
     *
     */
    public CombustiblesTest() {
    }
    
    /**
     *
     * @throws MalformedXMLException
     */
    @Before
    public void setUp() throws MalformedXMLException {
        gasolina = new Combustibles();
    }

    /**
     * Test of getCurrentPrices method, of class Combustibles.
     */
    @Test
    public void testGetCurrentPrices() {
        combustible = gasolina.getCurrentPrices();

        assertNotNull("El objeto retornó nulo", combustible);
        assertTrue("No retornó precios válidos", ( Double.parseDouble(combustible.getGasolinaRegular()) > 0) );
    }

    /**
     * Test of getCurrentGasolinaPremiumPrice method, of class Combustibles.
     */
    @Test
    public void testGetCurrentGasolinaPremiumPrice() {
        assertTrue("No retornó precio para la gasolina premium", gasolina.get(Combustibles.GASOLINA_PREMIUM_LABEL)>0);
    }

    /**
     * Test of getCurrentGasolinaRegularPrice method, of class Combustibles.
     */
    @Test
    public void testGetCurrentGasolinaRegularPrice() {
        assertTrue("No retornó precio para la gasolina regular", gasolina.get(Combustibles.GASOLINA_REGULAR_LABEL)>0);
    }
    
}
