/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author keb03_000
 */
public interface FlooringMasteryServiceLayer {
    /* 
    Methods:
       + load
       + bootConfig
       + generateOrderData
       + getAllOrders
       + getAllProducts
       + getAllTaxes
       + getAllOrdersByDate
       + getOrderByDateAndOrderNumber
       + createOrder
       + removeOrder
       + editOrder
       + save
    */
    public void load() throws FlooringMasteryPersistenceException;
    
    public boolean bootConfig(String mode);
    
    public Order generateOrderData(Order generatedOrder);
    
    public List<Order> getAllOrders();
    
    public List<Product> getAllProducts();
    
    public List<Tax> getAllTaxes();
    
    public List<Order> getAllOrdersByDate(LocalDate orderDate)
        throws NoOrderExistsByDateException;
    
    public Order getOrderByDateAndOrderNumber(int orderNumber, LocalDate orderDate) 
            throws NoOrderExistsByOrderNumberAndByDateException;
    
    public Order createOrder(Order newOrder);
    
    public void removeOrder(Order removedOrder);
    
    public void editOrder(Order editedOrder);
    
    public void save(String mode) throws FlooringMasteryPersistenceException;
    
}
