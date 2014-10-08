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

import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import org.devdom.commons.util.Utils;

/**
 * Clase utilizada para obtener los precios de los carburantes en la República Dominicana.<br>
 * Los precios de los distintos {@link Combustible.Tipos tipos} de {@link Combustible combustibles} se obtienen de la Secretaria de Estado de Industria y Comercio (SEIC):
 * <ul>
 *   <li>RSS feed: http://www.seic.gov.do/rss/combustibles.aspx</li>
 *   <li>Histórico de precios: http://www.seic.gov.do/hidrocarburos/precios-de-combustibles.aspx</li>
 * </ul>
 * 
 * @author Carlos Vásquez Polanco
 * @see #RSS_COMBUSTIBLES
 * @see #URL_HISTORICO_COMBUSTIBLES
 * @since 0.6.7
 */
public class Combustibles {
    private final String title;
    private final Date publishDate;
    private final List<Combustible> combustibles;

    /**
     * URL del RSS feed de los precios de los combustibles.
     */
    public static final String RSS_COMBUSTIBLES = "http://www.seic.gov.do/rss/combustibles.aspx";

    /**
     * URL de la pagina donde se publica el historico de los precios de los combustibles.
     */
    public static final String URL_HISTORICO_COMBUSTIBLES = "http://www.seic.gov.do/hidrocarburos/precios-de-combustibles.aspx";

    private Combustibles(String title, Date publishDate, List<Combustible> combustibles) {
        this.title = title;
        this.publishDate = publishDate;
        this.combustibles = combustibles;
    }
    
    /**
     * Obtener instancia de {@link Combustibles} con los precios actuales.
     * @throws org.devdom.commons.CommonsException
     * @see Combustible
     * @return 
     */
    public static Combustibles getCurrentPrices() throws CommonsException {
        Combustibles combustibles = null;
        List<Combustible> list = new ArrayList<Combustible>();
        InputStream input = null;
        final String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";
        String title = null;
        Date publishDate = null;
        
        try{
            input = new URL(RSS_COMBUSTIBLES).openStream();
            XMLInputFactory xif = XMLInputFactory.newFactory();
            XMLEventReader eventReader = xif.createXMLEventReader(input);
            XMLEvent event;

            while (eventReader.hasNext()) {
                event = eventReader.nextEvent();
            
                if (event.isStartElement()) {
                    String value = getCharacterData(event, eventReader);
                    String item = event.asStartElement().getName().getLocalPart();

                    if("pubDate".equals(item)){
                        publishDate = new SimpleDateFormat(dateFormat).parse(value);
                    }else if("title".equals(item)){
                        title = value;
                    }else{
                        appendCombustible(list, item, value);
                    }
                }
            }

            //TODO validate if data was received?
            combustibles = new Combustibles(title, publishDate, list);
        }catch(Exception e){
            throw new CommonsException("Error al buscar lista de precios de combusibles.", e);
        }finally{
            Utils.close(input);
        }

        return combustibles;
    }

    /**
     * Método utilizado para extraer el valor de un campo
     * @param event
     * @param eventReader
     * @return
     * @throws XMLStreamException 
     */
    private static String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();

        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

    private static void appendCombustible(List<Combustible> list, String item, String value) throws Exception {
        if("gas95".equals(item)) {
            list.add(new Combustible(Double.parseDouble(value), Combustible.Tipos.GASOLINA_PREMIUM));
        }

        if("gas89".equals(item)){
            list.add(new Combustible(Double.parseDouble(value), Combustible.Tipos.GASOLINA_REGULAR));
        }

        if("gasoilp".equals(item)){
            list.add(new Combustible(Double.parseDouble(value), Combustible.Tipos.GASOIL_PREMIUM));
        }

        if("gasoilr".equals(item)){
            list.add(new Combustible(Double.parseDouble(value), Combustible.Tipos.GASOIL_REGULAR));
        }

        if("kerosene".equals(item)){
            list.add(new Combustible(Double.parseDouble(value), Combustible.Tipos.KEROSENE));
        }

        if("glp".equals(item)){
            list.add(new Combustible(Double.parseDouble(value), Combustible.Tipos.GAS_LICUADO_PETROLEO));
        }

        if("gnv".equals(item)){
            list.add(new Combustible(Double.parseDouble(value), Combustible.Tipos.GAS_NATURAL_VEHICULAR));
        }
    }
    
    /**
     * Retorna el encabezado de la publicación de los precios de los {@link #getCombustibles() combustibles}.
     * Eg.: {@code Precios de Combustibles: 4 al 10 de octubre de 2014}
     * @return 
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retorna la fecha de publicación de los {@link #getCombustibles() combustibles}.
     * @return 
     */
    public Date getPublishDate() {
        return publishDate;
    }

    /**
     * Retorna la lista de combustibles listados en la última publicación de precios en el RSS feed de la SEIC.<br>
     * Esta lista sera una instancia {@link Collections#unmodifiableList(java.util.List) inmutable}.
     * @return la lista de {@link Combustible combustibles} publicados en el RSS feed de la SEIC, o una lista vacía en caso de no haber publicación en dicho RSS feed.
     */
    public List<Combustible> getCombustibles() {
        return Collections.unmodifiableList(combustibles);
    }

    @Override
    public String toString() {
        return "Combustibles{" + "title=" + title + ", publishDate=" + publishDate + ", combustibles=" + combustibles + '}';
    }
}