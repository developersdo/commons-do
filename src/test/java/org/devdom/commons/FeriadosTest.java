package org.devdom.commons;

import java.util.List;

import org.devdom.commons.dto.Feriado;
import org.junit.Assert;
import org.junit.Test;

public class FeriadosTest {
    
    @Test
    public void getAll() {
        try {
            List<Feriado> feriados = Feriados.getList();
            Assert.assertTrue("Lista de feriados null para el presente año", feriados != null);
            Assert.assertTrue("Lista de feriados vacía para el presente año", feriados.size() > 0);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    
    /**
     * TODO implement test method to test isTodayHoliday
     * 
     * probably we should parametrize this method so a system hosted in
     * china may get this right, probably using TimeZone's values.
     */
    //@Test
    public void isTodayHoliday() {
        
    }
    
}
