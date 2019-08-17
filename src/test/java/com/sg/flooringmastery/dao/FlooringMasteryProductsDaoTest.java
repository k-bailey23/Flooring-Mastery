/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
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
public class FlooringMasteryProductsDaoTest {
    
    private FlooringMasteryProductsDao dao = new FlooringMasteryProductsDaoFileImpl();
    
    public Product product;
    
    public FlooringMasteryProductsDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<Product> productsList = dao.getAllProducts();
        productsList.forEach((product) -> {
            dao.removeProduct(product);
        });
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getProduct method, of class FlooringMasteryProductsDao.
     */
    @Test
    public void testAddGetProduct() {
        product = new Product();
        product.setProductType("Tile");
        product.setLaborCostPerSqFt(new BigDecimal(1.00));
        product.setCostPerSqFt(new BigDecimal(2.00));
        
        dao.addProduct(product);
        
        Product fromDao = dao.getProduct(product.getProductType());
        assertNotNull(fromDao);
        assertEquals("Tile", product.getProductType());
        assertEquals("Tile", fromDao.getProductType());
        assertEquals(fromDao, product);
        
        
        
        
        
       
    }

    /**
     * Test of removeProduct method, of class FlooringMasteryProductsDao.
     */
    @Test
    public void testRemoveProduct() {
        product = new Product();
        Product product2 = new Product();
        
        product.setProductType("Tile");
        product.setLaborCostPerSqFt(new BigDecimal(1.00));
        product.setCostPerSqFt(new BigDecimal(2.00));
        
        product2.setProductType("Wood");
        product2.setLaborCostPerSqFt(new BigDecimal(2.00));
        product2.setCostPerSqFt(new BigDecimal(3.00));
        
        assertEquals(0, dao.getAllProducts().size());
        
        dao.addProduct(product);
        
        assertEquals(1, dao.getAllProducts().size());
        assertEquals("Tile", product.getProductType());
        
        dao.addProduct(product2);
        
        assertEquals(2, dao.getAllProducts().size());
        assertEquals("Wood", product2.getProductType());

        dao.removeProduct(product);
        
        assertEquals(1, dao.getAllProducts().size());
        assertNull(dao.getProduct(product.getProductType()));
        
        dao.removeProduct(product2);
        
        assertEquals(0, dao.getAllProducts().size());
        assertNull(dao.getProduct(product2.getProductType()));
        
        dao.addProduct(product);
        dao.addProduct(product2);
        
        assertEquals(2, dao.getAllProducts().size());
        
        assertNotNull(dao.getProduct(product.getProductType()));
        assertNotNull(dao.getProduct(product2.getProductType()));

    }

    /**
     * Test of getAllProducts method, of class FlooringMasteryProductsDao.
     */
    @Test
    public void testGetAllProducts() {
        
        assertEquals(0, dao.getAllProducts().size());
        assertTrue(dao.getAllProducts().isEmpty());
        
        product = new Product();
        Product product2 = new Product();
        
        product.setProductType("Tile");
        product.setLaborCostPerSqFt(new BigDecimal(1.00));
        product.setCostPerSqFt(new BigDecimal(2.00));
        
        product2.setProductType("Wood");
        product2.setLaborCostPerSqFt(new BigDecimal(2.00));
        product2.setCostPerSqFt(new BigDecimal(3.00));
        
        dao.addProduct(product);
        dao.addProduct(product2);
        
        dao.getAllProducts();
        
        assertNotNull(dao.getAllProducts());
        assertEquals(2, dao.getAllProducts().size());
        assertFalse(dao.getAllProducts().isEmpty());
        
        dao.removeProduct(product);
        
        assertNotNull(dao.getAllProducts());
        assertEquals(1, dao.getAllProducts().size());
        assertFalse(dao.getAllProducts().isEmpty());
        
        dao.removeProduct(product2);
        assertNotNull(dao.getAllProducts());
        assertEquals(0, dao.getAllProducts().size());
        assertTrue(dao.getAllProducts().isEmpty());
        
        dao.addProduct(product);
        dao.addProduct(product2);
        
        assertNotNull(dao.getAllProducts());
        assertEquals(2, dao.getAllProducts().size());
        assertFalse(dao.getAllProducts().isEmpty());
        
        dao.removeProduct(product);
        dao.removeProduct(product2);
        
        
        
    }


   
}
