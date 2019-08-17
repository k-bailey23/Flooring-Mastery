/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryOrdersDaoStubImpl;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dao.FlooringMasteryProductsDaoStubImpl;
import com.sg.flooringmastery.dao.FlooringMasteryTaxesDaoStubImpl;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author keb03_000
 */
public class FlooringMasteryServiceLayerTest {
    
    private FlooringMasteryServiceLayer service;
    
    private FlooringMasteryOrdersDaoStubImpl ordersStub;
    private FlooringMasteryProductsDaoStubImpl productsStub;
    private FlooringMasteryTaxesDaoStubImpl taxesStub;
    
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
       
    public FlooringMasteryServiceLayerTest() {
        ApplicationContext ctx = 
                new ClassPathXmlApplicationContext
                    ("applicationContext.xml");
        service = ctx.getBean(
                "serviceLayer", FlooringMasteryServiceLayer.class);
        ordersStub = ctx.getBean(
                "ordersDaoStub", FlooringMasteryOrdersDaoStubImpl.class);
        productsStub = ctx.getBean(
                "productsDaoStub", FlooringMasteryProductsDaoStubImpl.class);
        taxesStub = ctx.getBean(
                "taxesDaoStub", FlooringMasteryTaxesDaoStubImpl.class);
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of load method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testLoad() throws Exception {
        assertEquals(ordersStub.loads, 0);
        assertEquals(productsStub.loads, 0);
        assertEquals(taxesStub.loads, 0);
        
        service.load();
        
        assertEquals(ordersStub.loads, 1);
        assertEquals(productsStub.loads, 1);
        assertEquals(taxesStub.loads, 1);
        
    }

    /**
     * Test of bootConfig method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testBootConfig() {
        String mode1 = "PROD";
        String mode2 = "training";
        boolean isProd = true;
        boolean config;
        
        //PROD
        config = service.bootConfig(mode1);
        assertEquals(isProd, config);
        assertTrue(config);
                
        config = service.bootConfig(mode2);
        assertEquals(!isProd, config);
        assertFalse(config);
        
        
    }
    
    /**
     * Test of getAllOrders method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetAllOrders() {
        List<Order> orders = service.getAllOrders();
        //Orders list should be same list from dao
        Assert.assertEquals(orders, ordersStub.getAllOrders());
        assertEquals(2, service.getAllOrders().size());
    }

    /**
     * Test of getAllProducts method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetAllProducts() {
        List<Product> products = service.getAllProducts();
        //Products list should be same list from dao
        Assert.assertEquals(products, productsStub.getAllProducts());
    }

    /**
     * Test of getAllTaxes method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetAllTaxes() {
        List<Tax> taxes = service.getAllTaxes();
        //Taxes list should be same list from dao
        Assert.assertEquals(taxes, taxesStub.getAllTaxes());
    }

    /**
     * Test of createOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testCreateOrder() {
        Order order = new Order();
        order.setOrderNumber(1);
        order.setOrderDate(LocalDate.parse("10/21/2018", formatter));
        order.setCustomerName("NewName");
        order.setState("OH");
       
        order.setProductType("Wood");
        order.setArea(new BigDecimal("2.00"));
        
        assertEquals(1, ordersStub.getOrder(1).getOrderNumber());
        assertEquals(LocalDate.parse("10/21/2018", formatter), ordersStub.getOrder(1).getOrderDate());
        assertEquals("NewName", ordersStub.getOrder(1).getCustomerName());
        assertEquals("OH", ordersStub.getOrder(1).getState());
        assertEquals(new BigDecimal("6.25"), ordersStub.getOrder(1).getTaxRate());
        assertEquals("Wood", ordersStub.getOrder(1).getProductType());
        assertEquals(new BigDecimal("2.00"), ordersStub.getOrder(1).getArea());
        assertEquals(new BigDecimal("4.75"), ordersStub.getOrder(1).getCostPerSqFt());
        assertEquals(new BigDecimal("2.10"), ordersStub.getOrder(1).getLaborCostPerSqFt());
        assertEquals(new BigDecimal("9.50"), ordersStub.getOrder(1).getMaterialCost());
        assertEquals(new BigDecimal("4.20"), ordersStub.getOrder(1).getLaborCost());
        assertEquals(new BigDecimal(".08"), ordersStub.getOrder(1).getTax());
        assertEquals(new BigDecimal("13.78"), ordersStub.getOrder(1).getTotal());
        
                
    }
    
      
    

    /**
     * Test of getAllOrdersByDate method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetAllOrdersByDateWithNoExistingOrder() throws Exception {
        boolean correctException = false;
        try {
            service.getAllOrdersByDate(LocalDate.parse("11/11/2011", formatter));
            Assert.fail("Expected exception not thrown");
        } catch (NoOrderExistsByDateException e) {
            correctException = true;
        }
        assertTrue(correctException);
    }

    @Test 
    public void testGetAllOrdersByDateWithExistingOrder() throws Exception {
        List<Order> ordersByDate = service.
                getAllOrdersByDate(LocalDate.parse("10/21/2018", formatter));
        assertNotNull(ordersByDate);
        assertEquals(1, service.
                getAllOrdersByDate(LocalDate.parse("10/21/2018", formatter)).size());
    }
    
    /**
     * Test of getOrderByDateAndOrderNumber method, of class FlooringMasteryServiceLayer.
     * 
     */
    @Test
    public void testGetOrderByDateAndOrderNumberWithNoExistingOrder() throws Exception {       
        boolean correctException = false;
        try {
            service.getOrderByDateAndOrderNumber(1, LocalDate.parse("10/27/2018", formatter));
            Assert.fail("Did not throw expected exception");
        } catch (NoOrderExistsByOrderNumberAndByDateException e) {
            correctException = true;
        }
        
                
    }

    @Test
    public void testGetOrderByDateAndOrderNumberWithExistingOrder() throws Exception {
        LocalDate date = (LocalDate.parse("10/21/2018", formatter));
        int orderNum = 1;
        service.getOrderByDateAndOrderNumber(orderNum, date);
        
        //assertNotNull(order);
        
        //assertEquals(orderNum, order.getOrderNumber());
        //assertEquals(date, order.getOrderDate());
           
    }
    
    /**
     * Test of removeOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testRemoveOrder() {
        Order order = ordersStub.getOrder(1);       
        assertNotNull(ordersStub.getOrder(1));
        service.removeOrder(order);
        assertNull(ordersStub.getOrder(1));
        
    }

    /**
     * Test of editOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testEditOrder() {
        Order order = new Order();
        order.setOrderNumber(1);
        order.setOrderDate(LocalDate.parse("10/21/2018", formatter));
        order.setCustomerName("NewName");
        order.setState("OH");
        order.setTaxRate(new BigDecimal("6.25"));
        order.setProductType("Wood");
        order.setArea(new BigDecimal("2.00"));
        order.setCostPerSqFt(new BigDecimal("4.75"));
        order.setLaborCostPerSqFt(new BigDecimal("2.10"));
        order.setMaterialCost(new BigDecimal("9.50"));
        order.setLaborCost(new BigDecimal("4.20"));
        order.setTax(new BigDecimal(".08"));
        order.setTotal(new BigDecimal("13.78"));
        
        service.editOrder(order);
        assertNotNull(order);
               
        assertEquals(1, ordersStub.getOrder(1).getOrderNumber());
        assertEquals(LocalDate.parse("10/21/2018", formatter), ordersStub.getOrder(1).getOrderDate());
        assertEquals("NewName", ordersStub.getOrder(1).getCustomerName());
        assertEquals("OH", ordersStub.getOrder(1).getState());
        assertEquals(new BigDecimal("6.25"), ordersStub.getOrder(1).getTaxRate());
        assertEquals("Wood", ordersStub.getOrder(1).getProductType());
        assertEquals(new BigDecimal("2.00"), ordersStub.getOrder(1).getArea());
        assertEquals(new BigDecimal("4.75"), ordersStub.getOrder(1).getCostPerSqFt());
        assertEquals(new BigDecimal("2.10"), ordersStub.getOrder(1).getLaborCostPerSqFt());
        assertEquals(new BigDecimal("9.50"), ordersStub.getOrder(1).getMaterialCost());
        assertEquals(new BigDecimal("4.20"), ordersStub.getOrder(1).getLaborCost());
        assertEquals(new BigDecimal(".08"), ordersStub.getOrder(1).getTax());
        assertEquals(new BigDecimal("13.78"), ordersStub.getOrder(1).getTotal());
    
        
    }

    /**
     * Test of save method, of class FlooringMasteryServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testSave() throws Exception {

    }
    

}
