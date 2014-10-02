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

package org.devdom.commons.dto;
/**
 *
 * @author Carlos Vásquez Polanco
 * @since 0.6.5
 */
public class Feed {
    
    /**
     * Campos generales de un RSS 
     */
    private String title;
    private String description;
    private String pubDate;
    private String link;  
    private String language;
    private String copyright;
    private String guid;
    /**
     * Especiales del MIC
     */
    private String gas95;
    private String gas89;
    private String gasoilp;
    private String gasoilr;
    private String kerosene;
    private String glp;
    private String gnv;
    
    /**
     * 
     * @param title
     * @param description
     * @param pubDate
     * @param link
     * @param language
     * @param copyright 
     */
    
    /**
     * 
     * @param title
     * @param description
     * @param pubDate
     * @param link
     * @param language
     * @param copyright 
     */
    public Feed(String title, String description, String pubDate, String link, String language, String copyright) {
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.link = link;
        this.language = language;
        this.copyright = copyright;

    }

    public Feed(String guid, String gas95, String gas89, String gasoilp, String gasoilr, String kerosene, String glp, String gnv) {
        this.guid = guid;
        this.gas95 = gas95;
        this.gas89 = gas89;
        this.gasoilp = gasoilp;
        this.gasoilr = gasoilr;
        this.kerosene = kerosene;
        this.glp = glp;
        this.gnv = gnv;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getLink() {
        return link;
    }

    public String getLanguage() {
        return language;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getGuid() {
        return guid;
    }

    public String getGas95() {
        return gas95;
    }

    public String getGas89() {
        return gas89;
    }

    public String getGasoilp() {
        return gasoilp;
    }

    public String getGasoilr() {
        return gasoilr;
    }

    public String getKerosene() {
        return kerosene;
    }

    public String getGlp() {
        return glp;
    }

    public String getGnv() {
        return gnv;
    }

    @Override
    public String toString() {
        return "Feed{" + "title=" + title + ", description=" + description + ", pubDate=" + pubDate + ", link=" + link + ", language=" + language + ", copyright=" + copyright + ", guid=" + guid + ", gas95=" + gas95 + ", gas89=" + gas89 + ", gasoilp=" + gasoilp + ", gasoilr=" + gasoilr + ", kerosene=" + kerosene + ", glp=" + glp + ", gnv=" + gnv + '}';
    }

}