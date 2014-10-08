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
package org.devdom.commons.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import org.devdom.commons.exceptions.MalformedXMLException;
import org.devdom.commons.util.Configuration;

/**
 *
 * @author Carlos Vásquez Polanco
 * @since 0.6.7
 */
public class RSSParser{
    
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String PUB_DATE = "pubDate";
    public static final String LINK = "link";
    public static final String LANGUAGE = "language";
    public static final String COPYRIGHT = "copyright";
    public static final String GUID = "guid";

    private final XMLInputFactory inputFactory = XMLInputFactory.newInstance();
    private XMLEventReader eventReader;
    private XMLEvent event;

    /**
     * 
     * @throws MalformedXMLException 
     */
    public RSSParser() throws MalformedXMLException {
//        try{
//            
//            URL url = new URL(Configuration.RSS_COMBUSTIBLES);
//            InputStream in = url.openStream();
//            eventReader = inputFactory.createXMLEventReader(in);
//            
//        }catch(MalformedURLException ex){
//            throw new MalformedXMLException(ex.getMessage(), ex);
//        } catch (IOException ex) {
//            throw new MalformedXMLException(ex.getMessage(), ex);
//        } catch (XMLStreamException ex) {
//            throw new MalformedXMLException(ex.getMessage(), ex);
//        }
    }

    /**
     * Método utilizado para extraer el valor de un campo
     * @param event
     * @param eventReader
     * @return
     * @throws XMLStreamException 
     */
    private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();

        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }
    
    /**
     *      * Método utilizado para asingar un valor dado el objeto Feed, el campo y el
     * objeto que se de sea asignar 
     * 
     * 
     * @param combustible objeto Feed que será alterado
     * @param item item al cual se desea asignar el valor
     * @param value valor del campo que se desea asignar 
     * @see Feed
     */
//    private void setProperty(Combustible combustible, String item, String value) {
//
//        if(item.equals(Combustible.Tipos.GASOIL_PREMIUM_LABEL)){
//            combustible.setGasoilPremium(value);
//        }
//
//        if(item.equals(Combustibles.GASOIL_REGULAR_LABEL)){
//            combustible.setGasoilRegular(value);
//        }
//
//        if(item.equals(Combustibles.GASOLINA_PREMIUM_LABEL)){
//            combustible.setGasolinaPremium(value);
//        }
//
//        if(item.equals(Combustibles.GASOLINA_REGULAR_LABEL)){
//            combustible.setGasolinaRegular(value);
//        }
//
//        if(item.equals(Combustibles.GAS_LICUADO_DE_PETROLEO_LABEL)){
//            combustible.setGlp(value);
//        }
//
//        if(item.equals(Combustibles.GAS_NATURAL_VEHICULAR_LABEL)){
//            combustible.setGnv(value);
//        }
//
//        if(item.equals(RSSParser.TITLE)){
//            combustible.setTitle(value);
//        }
//    }

    /**
     * Método utilizado para obtener un Objeto feed recorriendo los resultado 
     * obtenidos al llamar un RSS
     * 
     * @return
     * @throws MalformedXMLException 
     */
//    public Combustible getResult() throws MalformedXMLException{
//        
//        Combustible combustible = new Combustible();
//
//        while (eventReader.hasNext()) {
//            try{
//                event = eventReader.nextEvent();
//                
//                if (event.isStartElement()) {
//                    
//                    String value = getCharacterData(event, eventReader);
//                    String item = event.asStartElement().getName().getLocalPart();
//
//                    setProperty(combustible, item, value);
//                }
//            }catch(XMLStreamException ex){
//                throw new MalformedXMLException(ex.getMessage(),ex);
//            }
//        }
//        return combustible;
//    }

}
