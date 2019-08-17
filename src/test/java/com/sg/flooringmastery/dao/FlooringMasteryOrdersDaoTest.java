/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
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
public class FlooringMasteryOrdersDaoTest {
    
    private FlooringMasteryOrdersDao dao = new FlooringMasteryOrdersDaoFileImpl();
    
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public Order order;
    
    public FlooringMasteryOrdersDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {     
        List<Order> ordersList = dao.getAllOrders();
        ordersList.forEach((order) -> {
            dao.removeOrder(order);
        });
        
    }
    
    @After
    public void tearDown() {
    }



    /**
     * Test of addOrder method, of class FlooringMasteryOrdersDao.
     */
    @Test
    public void testAddGetOrder() {
        order = new Order();
        order.setOrderNumber(1);
        order.setOrderDate(LocalDate.parse("10/21/2018", formatter));
        order.setCustomerName("Name");
        order.setState("OH");
        order.setTaxRate(new BigDecimal(1.00));
        order.setProductType("Tile");
        order.setArea(new BigDecimal(2.00));
        order.setCostPerSqFt(new BigDecimal(3.00));
        order.setLaborCostPerSqFt(new BigDecimal(2.00));
        order.setMaterialCost(new BigDecimal(1.11));
        order.setLaborCost(new BigDecimal(2.25));
        order.setTax(new BigDecimal(1.00));
        order.setTotal(new BigDecimal(100.00));
        
        dao.addOrder(order);
        
        Order fromDao = dao.getOrder(order.getOrderNumber());
        assertNotNull(fromDao);
        assertEquals(order, fromDao);
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryOrdersDao.
     */
    @Test
    public void testRemoveOrder() {
        order = new Order();
        order.setOrderNumber(1);
        order.setOrderDate(LocalDate.parse("10/21/2018", formatter));
        order.setCustomerName("Name");
        order.setState("OH");
        order.setTaxRate(new BigDecimal(1.00));
        order.setProductType("Tile");
        order.setArea(new BigDecimal(2.00));
        order.setCostPerSqFt(new BigDecimal(3.00));
        order.setLaborCostPerSqFt(new BigDecimal(2.00));
        order.setMaterialCost(new BigDecimal(1.11));
        order.setLaborCost(new BigDecimal(2.25));
        order.setTax(new BigDecimal(1.00));
        order.setTotal(new BigDecimal(100.00));
        
                
        Order order2 = new Order();
        order2.setOrderNumber(2);
        order2.setOrderDate(LocalDate.parse("10/21/2018", formatter));
        order2.setCustomerName("Name2");
        order2.setState("OH");
        order2.setTaxRate(new BigDecimal(2.00));
        order2.setProductType("Tile");
        order2.setArea(new BigDecimal(3.00));
        order2.setCostPerSqFt(new BigDecimal(4.00));
        order2.setLaborCostPerSqFt(new BigDecimal(3.00));
        order2.setMaterialCost(new BigDecimal(2.11));
        order2.setLaborCost(new BigDecimal(3.25));
        order2.setTax(new BigDecimal(2.00));
        order2.setTotal(new BigDecimal(200.00));
        
        dao.addOrder(order);
        dao.addOrder(order2);
        
        dao.removeOrder(order);
        
        assertEquals(1, dao.getAllOrders().size());
        assertNull(dao.getOrder(order.getOrderNumber()));
        
        dao.removeOrder(order2);
        
        assertEquals(0, dao.getAllOrders().size());
        assertNull(dao.getOrder(order2.getOrderNumber()));

    }

    /**
     * Test of getAllOrders method, of class FlooringMasteryOrdersDao.
     */
    @Test
    public void testGetAllOrders() {
        
        
        assertEquals(0, dao.getAllOrders().size());
        assertTrue(dao.getAllOrders().isEmpty());
        
        order = new Order();
        order.setOrderNumber(1);
        order.setOrderDate(LocalDate.parse("10/21/2018", formatter));
        order.setCustomerName("Name");
        order.setState("OH");
        order.setTaxRate(new BigDecimal(1.00));
        order.setProductType("Tile");
        order.setArea(new BigDecimal(2.00));
        order.setCostPerSqFt(new BigDecimal(3.00));
        order.setLaborCostPerSqFt(new BigDecimal(2.00));
        order.setMaterialCost(new BigDecimal(1.11));
        order.setLaborCost(new BigDecimal(2.25));
        order.setTax(new BigDecimal(1.00));
        order.setTotal(new BigDecimal(100.00));
        
                
        Order order2 = new Order();
        order2.setOrderNumber(2);
        order2.setOrderDate(LocalDate.parse("10/21/2018", formatter));
        order2.setCustomerName("Name2");
        order2.setState("OH");
        order2.setTaxRate(new BigDecimal(2.00));
        order2.setProductType("Tile");
        order2.setArea(new BigDecimal(3.00));
        order2.setCostPerSqFt(new BigDecimal(4.00));
        order2.setLaborCostPerSqFt(new BigDecimal(3.00));
        order2.setMaterialCost(new BigDecimal(2.11));
        order2.setLaborCost(new BigDecimal(3.25));
        order2.setTax(new BigDecimal(2.00));
        order2.setTotal(new BigDecimal(200.00));
        
        dao.addOrder(order);
        dao.addOrder(order2);
        
        dao.getAllOrders();
        
        assertNotNull(dao.getAllOrders());
        assertEquals(2, dao.getAllOrders().size());
        assertFalse(dao.getAllOrders().isEmpty());
        
        dao.removeOrder(order);
        
        assertNotNull(dao.getAllOrders());
        assertEquals(1, dao.getAllOrders().size());
        assertFalse(dao.getAllOrders().isEmpty());
        
        dao.addOrder(order);
        
        assertNotNull(dao.getAllOrders());
        assertEquals(2, dao.getAllOrders().size());
        assertFalse(dao.getAllOrders().isEmpty());
        
        

    }
    
}
