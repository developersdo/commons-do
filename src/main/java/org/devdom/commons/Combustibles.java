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
import org.devdom.commons.service.RSSParser;
import org.devdom.commons.dto.Feed;
import org.devdom.commons.exceptions.MalformedXMLException;

/**
 * Clase utilizada para obtener los precios de los carburantes en la República Dominicana
 * 
 * @author Carlos Vásquez Polanco
 * @since 0.6.7
 */
public class Combustibles {
    
    public static final String GASOLINA_PREMIUM_LABEL = "gas95";
    public static final String GASOLINA_REGULAR_LABEL = "gas89";
    public static final String GASOIL_PREMIUM_LABEL = "gasoilp";
    public static final String GASOIL_REGULAR_LABEL = "gasoilr";
    public static final String KEROSENE_LABEL = "kerosene";
    public static final String GAS_LICUADO_DE_PETROLEO_LABEL = "glp";
    public static final String GAS_NATURAL_VEHICULAR_LABEL = "gnv";

    public static final int GASOLINA_PREMIUM = 0;
    public static final int GASOLINA_REGULAR = 1;
    public static final int GASOIL_PREMIUM = 2;
    public static final int GASOIL_REGULAR = 3;
    public static final int KEROSENE = 4;
    public static final int GAS_LICUADO_DE_PETROLEO = 5;
    public static final int GAS_NATURAL_VEHICULAR = 6;

    private final RSSParser parser = new RSSParser();
    private final Combustible combustible = parser.getResult();

    /**
     * 
     * @throws MalformedXMLException 
     */
    public Combustibles() {
        
    }

    /**
     * Obtener objeto Feed con los precios actuales
     * @see Feed
     * @return 
     */
    public Combustible getCurrentPrices(){
        return combustible;
    }
    
    /**
     * Obtener el precio de un carburante dado el campo que desea ser evaluado
     * 
     * @param item
     * @return 
     */
    public double get(String item){
        
        if(item.equals(RSSParser.TITLE)){
            throw new NumberFormatException("title no puede ser retornado por esta vía");
        }

        return Double.parseDouble(parser.getProperty(combustible, item));
    }
    
    /**
     * Obtener el títuto usado en el RSS por el Ministerio de Industria y Comercio
     * @return 
     */
    public String getTitle(){
        return combustible.getTitle();
    }
    
    /**
     * Obtener el precio de la Combustibles Premium en la República Dominicana
     * @return 
     */
    public double getCurrentGasolinaPremiumPrice(){
        return get(Combustibles.GASOIL_PREMIUM_LABEL);
    }
    
    /**
     * Obtener el precio de la Combustibles Regular en la República Dominicana
     * @return 
     */
    public double getCurrentGasolinaRegularPrice(){
        return get(Combustibles.GASOIL_REGULAR_LABEL);
    }
    
    /**
     * Obtener el precio del Gasoil Premium en la República Dominicana
     * @return 
     */
    public double getCurrentGasoilPremiumPrice(){
        return get(Combustibles.GASOIL_PREMIUM_LABEL);
    }
    
    /**
     * Obtener el precio del Gasoil Regular en la República Dominicana
     * @return 
     */
    public double getCurrentGasoilRegularPrice(){
        return get(Combustibles.GASOIL_REGULAR_LABEL);
    }
    
    /**
     * Obtener el precio del Kerosene en la República Dominicana
     * @return 
     */
    public double getCurrentKerosenePrice(){
        return get(Combustibles.KEROSENE_LABEL);
    }
    
    /**
     * Obtener el precio del Gas Licuado De Petroleo en la República Dominicana
     * @return 
     */
    public double getCurrentGasLicuadoDePetroleo(){
        return get(Combustibles.GAS_LICUADO_DE_PETROLEO_LABEL);
    }
    
    /**
     * Obtener el precio del Gas Natural Vehicular en la República Dominicana
     * @return 
     */
    public double getCurrentGasNautralVehicular(){
        return get(Combustibles.GAS_NATURAL_VEHICULAR_LABEL);
    }

}