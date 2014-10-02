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
     * Especiales del Ministerio de Industria y Comercio de la República Dominicana
     */
    private String gas95;
    private String gas89;
    private String gasoilp;
    private String gasoilr;
    private String kerosene;
    private String glp;
    private String gnv;

    public Feed(){
    
    }

    public Feed(String title) {
        this.title = title;
    }

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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getGas95() {
        return gas95;
    }

    public void setGas95(String gas95) {
        this.gas95 = gas95;
    }

    public String getGas89() {
        return gas89;
    }

    public void setGas89(String gas89) {
        this.gas89 = gas89;
    }

    public String getGasoilp() {
        return gasoilp;
    }

    public void setGasoilp(String gasoilp) {
        this.gasoilp = gasoilp;
    }

    public String getGasoilr() {
        return gasoilr;
    }

    public void setGasoilr(String gasoilr) {
        this.gasoilr = gasoilr;
    }

    public String getKerosene() {
        return kerosene;
    }

    public void setKerosene(String kerosene) {
        this.kerosene = kerosene;
    }

    public String getGlp() {
        return glp;
    }

    public void setGlp(String glp) {
        this.glp = glp;
    }

    public String getGnv() {
        return gnv;
    }

    public void setGnv(String gnv) {
        this.gnv = gnv;
    }

    @Override
    public String toString() {
        return "Feed{" + "title=" + title + ", description=" + description + ", pubDate=" + pubDate + ", link=" + link + ", language=" + language + ", copyright=" + copyright + ", guid=" + guid + ", gas95=" + gas95 + ", gas89=" + gas89 + ", gasoilp=" + gasoilp + ", gasoilr=" + gasoilr + ", kerosene=" + kerosene + ", glp=" + glp + ", gnv=" + gnv + '}';
    }
    
}