/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.util.List;

/**
 *
 * @author keb03_000
 */
public interface FlooringMasteryOrdersDao {
 
    public Order getOrder(int orderNumber);
    
    public Order addOrder(Order newOrder);
    
    public Order removeOrder(Order removedOrder);
    
    public List<Order> getAllOrders();
    
    public void editOrder(int oldOrderNumber, Order orderToEdit);
    
    public void loadOrderData() throws FlooringMasteryPersistenceException;
    
    public void writeOrderData() throws FlooringMasteryPersistenceException;
}
