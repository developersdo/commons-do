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

import org.devdom.commons.service.RSSParser;
import org.devdom.commons.dto.Feed;
import org.devdom.commons.exceptions.MalformedXMLException;

/**
 *
 * @author Carlos Vásquez Polanco
 * @since 0.6.7
 */
public class Gasolina {
    
    private final RSSParser parser;
    private final Feed feed;
    
    public static final String GASOLINA_PREMIUM = "gas95";
    public static final String GASOLINA_REGULAR = "gas89";
    public static final String GASOIL_PREMIUM = "gasoilp";
    public static final String GASOIL_REGULAR = "gasoilr";
    public static final String KEROSENE = "kerosene";
    public static final String GAS_LICUADO_DE_PETROLEO = "glp";
    public static final String GAS_NATURAL_VEHICULAR= "gnv";

    /**
     * 
     * @throws MalformedXMLException 
     */
    public Gasolina() throws MalformedXMLException {
        this.parser = new RSSParser();
        feed = parser.getResult();
    }
    
    /**
     * 
     * @return 
     */
    public Feed getCurrentPrices(){
        return feed;
    }
    
    /**
     * 
     * @param item
     * @return 
     */
    public double get(String item){
        
        if(item.equals(RSSParser.TITLE)){
            throw new NumberFormatException("title no puede ser retornado por esta vía");
        }
        
        return Double.parseDouble(parser.getProperty(feed, item));
    }
    
    public String getTitle(){
        return feed.getTitle();
    }
    
    public double getCurrentGasolinaPremiumPrice(){
        return get(Gasolina.GASOIL_PREMIUM);
    }
    
    public double getCurrentGasolinaRegularPrice(){
        return get(Gasolina.GASOIL_REGULAR);
    }
    
    public double getCurrentGasoilPremiumPrice(){
        return get(Gasolina.GASOIL_PREMIUM);
    }
    
    public double getCurrentGasoilRegularPrice(){
        return get(Gasolina.GASOIL_REGULAR);
    }
    
    public double getCurrentKerosenePrice(){
        return get(Gasolina.KEROSENE);
    }
    
    public double getCurrentGasLicuadoDePetroleo(){
        return get(Gasolina.GAS_LICUADO_DE_PETROLEO);
    }
    
    public double getCurrentGasNautralVehicular(){
        return get(Gasolina.GAS_NATURAL_VEHICULAR);
    }

}