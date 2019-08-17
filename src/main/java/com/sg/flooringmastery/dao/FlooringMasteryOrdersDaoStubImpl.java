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
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author keb03_000
 */
public class FlooringMasteryOrdersDaoStubImpl implements FlooringMasteryOrdersDao {

    
    Order order;
    Order order2;
    
    private Map<Integer, Order> orderList = new LinkedHashMap<>();
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public int loads = 0;
    public int saves = 0;
    
    

    public FlooringMasteryOrdersDaoStubImpl() {
        order = new Order();
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
        
        orderList.put(order.getOrderNumber(), order);
        
        order2 = new Order();
        order2.setOrderNumber(2);
        order2.setOrderDate(LocalDate.parse("10/22/2018", formatter));
        order2.setCustomerName("Name2");
        order2.setState("KY");
        order2.setTaxRate(new BigDecimal("1.00"));
        order2.setProductType("Tile");
        order2.setArea(new BigDecimal("1.00"));
        order2.setCostPerSqFt(new BigDecimal("3.50"));
        order2.setLaborCostPerSqFt(new BigDecimal("4.15"));
        order2.setMaterialCost(new BigDecimal("3.50"));
        order2.setLaborCost(new BigDecimal("4.15"));
        order2.setTax(new BigDecimal(".04"));
        order2.setTotal(new BigDecimal("7.69"));
        
        orderList.put(order2.getOrderNumber(), order2);
        
    }
    
    
    @Override
    public Order getOrder(int orderNumber) {
        if (orderNumber == 1) {
            return order;
        } else if (orderNumber == 2) {
            return order2;
        } else {
            return null;
        }
    }

    @Override
    public Order addOrder(Order newOrder) {
        if (newOrder == order) {
            return order;
        } else if (newOrder == order2) {
            return order2;
        } else { 
            return null;
        }
    }

    @Override
    public Order removeOrder(Order removedOrder) {
        if (removedOrder == order) {
            orderList.remove(order.getOrderNumber());
            order = orderList.get(0);
            return order; 
        } else if (removedOrder == order2) {
            orderList.remove(order2.getOrderNumber());
            order = orderList.get(1);
            return order2;
        } else {
            return null;
        }
    }

    @Override
    public List<Order> getAllOrders() {
        Collection<Order> orders = this.orderList.values();
        return new ArrayList<>(orders);
    }

    @Override
    public void editOrder(int oldOrderNumber, Order orderToEdit) {
        if(oldOrderNumber == orderToEdit.getOrderNumber()) {
            this.orderList.replace(oldOrderNumber, orderToEdit);
        } else {
            Order oldOrder = this.orderList.remove(oldOrderNumber);
            this.orderList.put(orderToEdit.getOrderNumber(), orderToEdit);
        }
    }

    @Override
    public void loadOrderData() throws FlooringMasteryPersistenceException {
        loads++;
    }

    @Override
    public void writeOrderData() throws FlooringMasteryPersistenceException {
        saves++;
    }
    
}
