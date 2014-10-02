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
import org.devdom.commons.Gasolina;
import org.devdom.commons.dto.Feed;
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
        try{
            
            URL url = new URL(Configuration.RSS_COMBUSTIBLES);
            InputStream in = url.openStream();
            eventReader = inputFactory.createXMLEventReader(in);
            
        }catch(MalformedURLException ex){
            throw new MalformedXMLException(ex.getMessage(), ex);
        } catch (IOException ex) {
            throw new MalformedXMLException(ex.getMessage(), ex);
        } catch (XMLStreamException ex) {
            throw new MalformedXMLException(ex.getMessage(), ex);
        }
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
     * Método utilizado par retornar el valor de un campo dado el objeto Feed
     * y el campo que se desea evaluar
     * 
     * @see Feed
     * @param feed Objeto que almacena las respuestas de un RSS
     * @param item campo que se desea retornar
     * @return String del valor del campo suplido
     */
    public String getProperty(Feed feed, String item){
        
        if(item.equals(Gasolina.GASOIL_PREMIUM)){
            return feed.getGasoilp();
        }

        if(item.equals(Gasolina.GASOIL_REGULAR)){
            return feed.getGasoilr();
        }

        if(item.equals(Gasolina.GASOLINA_PREMIUM)){
            return feed.getGas95();
        }

        if(item.equals(Gasolina.GASOLINA_REGULAR)){
            return feed.getGas89();
        }

        if(item.equals(Gasolina.GAS_LICUADO_DE_PETROLEO)){
            return feed.getGlp();
        }

        if(item.equals(Gasolina.GAS_NATURAL_VEHICULAR)){
            return feed.getGnv();
        }

        if(item.equals(RSSParser.TITLE)){
            return feed.getTitle();
        }
        return "0";
    }

    /**
     *      * Método utilizado para asingar un valor dado el objeto Feed, el campo y el
     * objeto que se de sea asignar 
     * 
     * 
     * @param feed objeto Feed que será alterado
     * @param item item al cual se desea asignar el valor
     * @param value valor del campo que se desea asignar 
     * @see Feed
     */
    private void setProperty(Feed feed, String item, String value) {

        if(item.equals(Gasolina.GASOIL_PREMIUM)){
            feed.setGasoilp(value);
        }

        if(item.equals(Gasolina.GASOIL_REGULAR)){
            feed.setGasoilr(value);
        }

        if(item.equals(Gasolina.GASOLINA_PREMIUM)){
            feed.setGas95(value);
        }

        if(item.equals(Gasolina.GASOLINA_REGULAR)){
            feed.setGas89(value);
        }

        if(item.equals(Gasolina.GAS_LICUADO_DE_PETROLEO)){
            feed.setGlp(value);
        }

        if(item.equals(Gasolina.GAS_NATURAL_VEHICULAR)){
            feed.setGnv(value);
        }

        if(item.equals(RSSParser.TITLE)){
            feed.setTitle(value);
        }
    }

    /**
     * Método utilizado para obtener un Objeto feed recorriendo los resultado 
     * obtenidos al llamar un RSS
     * 
     * @return
     * @throws MalformedXMLException 
     */
    public Feed getResult() throws MalformedXMLException{
        
        Feed feed = new Feed();

        while (eventReader.hasNext()) {
            try{
                event = eventReader.nextEvent();
                
                if (event.isStartElement()) {
                    
                    String value = getCharacterData(event, eventReader);
                    String item = event.asStartElement().getName().getLocalPart();

                    setProperty(feed, item, value);
                }
            }catch(XMLStreamException ex){
                throw new MalformedXMLException(ex.getMessage(),ex);
            }
        }
        return feed;
    }

}
