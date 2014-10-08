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
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.devdom.commons.Combustibles;
import org.devdom.commons.Combustible;
import org.devdom.commons.exceptions.DocumentFormatException;
import org.devdom.commons.exceptions.MalformedXMLException;
import org.devdom.commons.util.Configuration;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Carlos Vásquez Polanco
 * @since 0.7.8
 */
public class XMLParser{

    private final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
    private final XPathFactory xpathFactory = XPathFactory.newInstance();
    private final XPath xpath = xpathFactory.newXPath();
    private final String FIND_START = "<div id=\"AllCombustibles\">";
    private final String FIND_END_TAG = "</ul>";
    private final String XML_DOC = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";

    private String xml;

    /**
     * 
     */
    public XMLParser() throws MalformedXMLException {
//        try{
//
//            URL url = new URL(Configuration.XML_LISTADO_COMBUSTIBLES);
//            InputStream in = url.openStream();
//
//            xml = new Scanner(in, "UTF-8").useDelimiter("\\A").next();
//
//            //Workaround por que el documento no estricto y las etiquetas abiertas dañan el formato.
//            //Lo idea hubiera sido convertir el documento completo a XML y usar xpath            
//            xml = xml.substring(xml.indexOf(FIND_START)+FIND_START.length());
//            xml = xml.substring(0, xml.indexOf(FIND_END_TAG) + FIND_END_TAG.length());
//            xml = XML_DOC + xml;
//
//        }catch(MalformedURLException ex){
//            throw new MalformedXMLException(ex.getMessage(), ex);
//        } catch (IOException ex) {
//            throw new MalformedXMLException(ex.getMessage(), ex);
//        }
    }
    
    /**
     * 
     * @return
     * @throws DocumentFormatException 
     */
    public ArrayList<Combustible> getCombustibles() throws DocumentFormatException{
        
        ArrayList<Combustible> newList = new ArrayList<Combustible>();

        try {
            Document doc = convertStringToDocument(xml);
            XPathExpression expr = xpath.compile("/ul/li");
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nodes.getLength(); i++) {
//                newList.add( getCombustible( nodes.item(i) ) );
            }
        } catch (XPathExpressionException ex) {
            throw new DocumentFormatException(ex.getMessage(), ex);
        }

        return newList;
    }
    
    /**
     * 
     * @param node
     * @return 
     */
//    private Combustible getCombustible(Node node){
//
//        // xpath equivalente: /div/div
//        NodeList divs = node.getChildNodes().item(0).getChildNodes();
//
//        // xpath equivalente: [1]/text()
//        String title = divs.item(0).getTextContent();
//
//        // xpath equivalente: [2]/strong/text()
//        String pubDate = divs.item(1).getFirstChild().getTextContent();
//        
//        // xpath equivalente: [3]/div/div/
//        NodeList combustiblePrices = (NodeList) divs.item(2).getFirstChild().getChildNodes();
//        
//        String gasolinaPremium = combustiblePrices.item(Combustibles.GASOLINA_PREMIUM)
//                                    .getChildNodes().item(1).getTextContent().replace("RD$","");
//        
//        String gasolinaRegular = combustiblePrices.item(Combustibles.GASOLINA_REGULAR)
//                                    .getChildNodes().item(1).getTextContent().replace("RD$","");
//        
//        String gasoilPremium = combustiblePrices.item(Combustibles.GASOIL_PREMIUM)
//                                    .getChildNodes().item(1).getTextContent().replace("RD$","");
//        
//        String gasoilRegular = combustiblePrices.item(Combustibles.GASOIL_REGULAR)
//                                    .getChildNodes().item(1).getTextContent().replace("RD$","");
//        
//        String kerosene = combustiblePrices.item(Combustibles.KEROSENE)
//                                    .getChildNodes().item(1).getTextContent().replace("RD$","");
//        
//        String gasLicuadoDePetroleo = combustiblePrices.item(Combustibles.GAS_LICUADO_DE_PETROLEO)
//                                    .getChildNodes().item(1).getTextContent().replace("RD$","");
//        
//        String gasNaturalVehicular = combustiblePrices.item(Combustibles.GAS_NATURAL_VEHICULAR)
//                                    .getChildNodes().item(1).getTextContent().replace("RD$","");
//        
//        Combustible newCombustible = new Combustible();
//        newCombustible.setTitle(title);
//        newCombustible.setPubDate(pubDate);
//        newCombustible.setGasolinaPremium(gasolinaPremium);
//        newCombustible.setGasolinaRegular(gasolinaRegular);
//        newCombustible.setGasoilPremium(gasoilPremium);
//        newCombustible.setGasoilRegular(gasoilRegular);
//        newCombustible.setKerosene(kerosene);
//        newCombustible.setGlp(gasLicuadoDePetroleo);
//        newCombustible.setGnv(gasNaturalVehicular);
//        
//        return newCombustible;
//
//    }
    
    /**
     * 
     * @param xml
     * @return
     * @throws DocumentFormatException 
     */
    private Document convertStringToDocument(String xml) throws DocumentFormatException {

        try {
            
            DocumentBuilder builder = factory.newDocumentBuilder(); 
            return builder.parse( new InputSource( new StringReader( xml ) ) );
            
        } catch (IOException ex) { 
            throw new DocumentFormatException(ex.getMessage(), ex);
        } catch (ParserConfigurationException ex) {
            throw new DocumentFormatException(ex.getMessage(), ex);
        } catch (SAXException ex) {
            throw new DocumentFormatException(ex.getMessage(), ex);
        }

    }

}