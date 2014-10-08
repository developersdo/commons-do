package org.devdom.commons;

import java.util.Date;

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
    private final double price;
    private final Tipos type;

    public enum Tipos {
      GASOLINA_PREMIUM,
      GASOLINA_REGULAR,
      GASOIL_PREMIUM,
      GASOIL_REGULAR,
      KEROSENE,
      GAS_LICUADO_PETROLEO,
      GAS_NATURAL_VEHICULAR
    }

  Combustible(double price, Tipos type) {
    this.price = price;
    this.type = type;
  }

  public double getPrice() {
    return price;
  }

  public Tipos getType() {
    return type;
  }

  @Override
  public String toString() {
    return "Combustible{" + "price=" + price + ", type=" + type + '}';
  }
}
