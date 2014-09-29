package org.devdom.commons;

import java.util.List;
import java.util.Random;

import org.devdom.commons.dto.Municipio;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MunicipiosTest {
    Municipios municipioService;
    //Provincias provinciaService;
    
    @Before
    public void setup() {
        municipioService = new Municipios();
        //provinciaService = new Provincias();
    }
    
    @Test
    public void getAll() {
        try {
            List<Municipio> municipios = municipioService.getList();
            Assert.assertTrue("Lista de Municipios null", municipios != null);
            Assert.assertTrue("Lista de Municipios vacía", municipios.size() > 0);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    
    @Test
    public void get() {
        try {
            List<Municipio> municipios = municipioService.getList();
            
            Municipio municipio = municipios.get(new Random().nextInt(municipios.size()));
            
            Municipio external = municipioService.get(String.valueOf(municipio.getId()));
            Assert.assertEquals(municipio, external);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    
//    @Test
//    TODO to be implemented 
//    getMunicipios by city
//    public void getMunicipiosByProvincia() {
//        try {
//            List<Provincia> pronvincias = provinciaService.getList();
//            Provincia municipio = pronvincias.get(new Random().nextInt(pronvincias.size()));
//            
//            
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
//    }
    
}
