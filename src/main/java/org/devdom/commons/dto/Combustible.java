package org.devdom.commons.dto;

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
/**
 * Entidad para expresar los precios variantes de los combustibles expresados por 
 * semana.
 * 
 * @author Carlos Vásquez Polanco
 * @since 0.7.8
 */
public class Combustible {

    private String title;
    private String pubDate;
    private String gasolinaPremium;
    private String gasolinaRegular;
    private String gasoilPremium;
    private String gasoilRegular;
    private String kerosene;
    private String glp;
    private String gnv;

    /**
     *
     */
    public Combustible() {
    }

    /**
     *
     * @param title
     * @param pubDate
     * @param gasolinaPremium
     * @param gasolinaRegular
     * @param gasoilPremium
     * @param gasoilRegular
     * @param kerosene
     * @param glp
     * @param gnv
     */
    public Combustible(String title, String pubDate, String gasolinaPremium, String gasolinaRegular, String gasoilPremium, String gasoilRegular, String kerosene, String glp, String gnv) {
        this.title = title;
        this.pubDate = pubDate;
        this.gasolinaPremium = gasolinaPremium;
        this.gasolinaRegular = gasolinaRegular;
        this.gasoilPremium = gasoilPremium;
        this.gasoilRegular = gasoilRegular;
        this.kerosene = kerosene;
        this.glp = glp;
        this.gnv = gnv;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     *
     * @param pubDate
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    /**
     *
     * @return
     */
    public String getGasolinaPremium() {
        return gasolinaPremium;
    }

    /**
     *
     * @param gasolinaPremium
     */
    public void setGasolinaPremium(String gasolinaPremium) {
        this.gasolinaPremium = gasolinaPremium;
    }

    /**
     *
     * @return
     */
    public String getGasolinaRegular() {
        return gasolinaRegular;
    }

    /**
     *
     * @param gasolinaRegular
     */
    public void setGasolinaRegular(String gasolinaRegular) {
        this.gasolinaRegular = gasolinaRegular;
    }

    /**
     *
     * @return
     */
    public String getGasoilPremium() {
        return gasoilPremium;
    }

    /**
     *
     * @param gasoilPremium
     */
    public void setGasoilPremium(String gasoilPremium) {
        this.gasoilPremium = gasoilPremium;
    }

    /**
     *
     * @return
     */
    public String getGasoilRegular() {
        return gasoilRegular;
    }

    /**
     *
     * @param gasoilRegular
     */
    public void setGasoilRegular(String gasoilRegular) {
        this.gasoilRegular = gasoilRegular;
    }

    /**
     *
     * @return
     */
    public String getKerosene() {
        return kerosene;
    }

    /**
     *
     * @param kerosene
     */
    public void setKerosene(String kerosene) {
        this.kerosene = kerosene;
    }

    /**
     *
     * @return
     */
    public String getGlp() {
        return glp;
    }

    /**
     *
     * @param glp
     */
    public void setGlp(String glp) {
        this.glp = glp;
    }

    /**
     *
     * @return
     */
    public String getGnv() {
        return gnv;
    }

    /**
     *
     * @param gnv
     */
    public void setGnv(String gnv) {
        this.gnv = gnv;
    }

    @Override
    public String toString() {
        return "Combustible{" + "title=" + title + ", pubDate=" + pubDate + ", gasolinaPremium=" + gasolinaPremium + ", gasolinaRegular=" + gasolinaRegular + ", gasoilPremium=" + gasoilPremium + ", gasoilRegular=" + gasoilRegular + ", kerosene=" + kerosene + ", glp=" + glp + ", gnv=" + gnv + '}';
    }

}
