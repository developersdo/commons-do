package org.devdom.commons;

import java.util.List;
import java.util.Random;

import org.devdom.commons.dto.Provincia;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProvinciasTest {
    Provincias provinciaService;
    
    @Before
    public void setup() {
        provinciaService = new Provincias();
    }
    
    @Test
    public void getAll() {
        try {
            List<Provincia> provincias = provinciaService.getList();
            Assert.assertTrue("Lista de Provincias null", provincias != null);
            Assert.assertTrue("Lista de Provincias vacía", provincias.size() > 0);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    
    @Test
    public void get() {
        try {
            List<Provincia> pronvincias = provinciaService.getList();
            
            Provincia provincia = pronvincias.get(new Random().nextInt(pronvincias.size()));
            
            Provincia external = provinciaService.get(String.valueOf(provincia.getId()));
            Assert.assertEquals(provincia, external);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    
}
