/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author keb03_000
 */
public class FlooringMasteryTaxesDaoTest {
    
    private FlooringMasteryTaxesDao dao = new FlooringMasteryTaxesDaoFileImpl();
    
    public Tax tax;
    
    
    public FlooringMasteryTaxesDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<Tax> taxesList = dao.getAllTaxes();
        taxesList.forEach((tax) -> {
            dao.removeTax(tax);
        });
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addGetTax method, of class FlooringMasteryTaxesDao.
     */
    @Test
    public void testAddGetTax() {
        tax = new Tax();
        
        tax.setState("OH");
        tax.setTaxRate(new BigDecimal("2.00"));
        
        dao.addTax(tax);
                
        Tax fromDao = dao.getTax(tax.getState());
        
        assertNotNull(fromDao);
        assertEquals("OH", tax.getState());
        assertEquals(fromDao, tax);

    }

    /**
     * Test of removeTax method, of class FlooringMasteryTaxesDao.
     */
    @Test
    public void testRemoveTax() {
        tax = new Tax();
        
        Tax newTax = new Tax();
        
        tax.setState("OH");
        tax.setTaxRate(new BigDecimal("2.00"));
        
        newTax.setState("IN");
        newTax.setTaxRate(new BigDecimal("3.00"));
        
        dao.addTax(tax);
        dao.addTax(newTax);
        
        assertEquals(2, dao.getAllTaxes().size());
        assertEquals("OH", tax.getState());
        assertEquals("IN", newTax.getState());
        
        dao.removeTax(newTax);
        
        assertEquals(1, dao.getAllTaxes().size());
        assertNull(dao.getTax(newTax.getState()));
        assertNotNull(dao.getTax(tax.getState()));
        
        dao.addTax(newTax);
        
        assertEquals(2, dao.getAllTaxes().size());
        assertNotNull(dao.getTax(newTax.getState()));
        assertNotNull(dao.getTax(tax.getState()));
        
        
        
    }

    /**
     * Test of getAllTaxes method, of class FlooringMasteryTaxesDao.
     */
    @Test
    public void testGetAllTaxes() {
        
        assertEquals(0, dao.getAllTaxes().size());
        assertTrue(dao.getAllTaxes().isEmpty());
        assertNotNull(dao.getAllTaxes());
        
        tax = new Tax();
        
        Tax newTax = new Tax();
        
        tax.setState("OH");
        tax.setTaxRate(new BigDecimal("2.00"));
        
        newTax.setState("IN");
        newTax.setTaxRate(new BigDecimal("3.00"));
        
        dao.addTax(tax);
        
        assertEquals(1, dao.getAllTaxes().size());
        
        dao.addTax(newTax);
        
        assertEquals(2, dao.getAllTaxes().size());       
        assertFalse(dao.getAllTaxes().isEmpty());
        assertNotNull(dao.getAllTaxes());
        
    }

}
