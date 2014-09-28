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

import java.util.Date;

/**
 *
 * @author Carlos Vásquez Polanco
 */
public class Feriado {
    
    private int id;
    private Date fechaOriginal;
    private Date fechaMovido;
    private String motivo;

    public Feriado(int id) {
        this.id = id;
    }

    public Feriado(int id, Date fechaOriginal, Date fechaMovido, String motivo) {
        this.id = id;
        this.fechaOriginal = fechaOriginal;
        this.fechaMovido = fechaMovido;
        this.motivo = motivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaOriginal() {
        return fechaOriginal;
    }

    public void setFechaOriginal(Date fechaOriginal) {
        this.fechaOriginal = fechaOriginal;
    }

    public Date getFechaMovido() {
        return fechaMovido;
    }

    public void setFechaMovido(Date fechaMovido) {
        this.fechaMovido = fechaMovido;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "Feriado{" + "id=" + id + ", fechaOriginal=" + fechaOriginal + ", fechaMovido=" + fechaMovido + ", motivo=" + motivo + '}';
    }

}
