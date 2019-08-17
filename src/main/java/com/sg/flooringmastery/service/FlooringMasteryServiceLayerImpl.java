/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryAuditDao;
import com.sg.flooringmastery.dao.FlooringMasteryOrdersDao;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dao.FlooringMasteryProductsDao;
import com.sg.flooringmastery.dao.FlooringMasteryTaxesDao;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author keb03_000
 */
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {

    
    FlooringMasteryOrdersDao orderDao;
    FlooringMasteryProductsDao productDao;
    FlooringMasteryTaxesDao taxDao;
    
    private FlooringMasteryAuditDao auditDao;

    String bootConfig;
    
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public String getBootConfig() {
        return bootConfig;
    }

    public void setBootConfig(String bootConfig) {
        this.bootConfig = bootConfig;
    }

    public FlooringMasteryServiceLayerImpl(FlooringMasteryOrdersDao orderDao,
            FlooringMasteryProductsDao productDao, FlooringMasteryTaxesDao taxDao, 
            FlooringMasteryAuditDao auditDao) {

        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
        this.auditDao = auditDao;
        
    }

    @Override
    public boolean bootConfig(String mode) {
        
        // bootConfig = getBootConfig();
        if (mode.equals("PROD")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    // load Order/Product/Tax data
    public void load() throws FlooringMasteryPersistenceException {
        orderDao.loadOrderData();
        productDao.loadProductData();
        taxDao.loadTaxData();
        
    }

    @Override
    // get all orders using getAllOrders() from Order Dao
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    // get all products using getAllProducts() from Product Dao
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    // get all taxes using getAllTaxes() from Tax Dao
    public List<Tax> getAllTaxes() {
        return taxDao.getAllTaxes();
    }

    @Override
    // get all orders by date using getAllOrders() from Order Dao
    // getAllOrders() as Collection & Stream for order date
    public List<Order> getAllOrdersByDate(LocalDate orderDate) throws NoOrderExistsByDateException {
        Collection<Order> allOrders = orderDao.getAllOrders();
        Stream<Order> allOrdersByDateStream = allOrders.stream()
                .filter(p -> p.getOrderDate()
                .equals(orderDate));
        List<Order> allOrdersByDateList = new ArrayList<>();
        allOrdersByDateList = allOrdersByDateStream
                .collect(Collectors.toList());
        if (allOrdersByDateList.isEmpty()) {
            throw new NoOrderExistsByDateException("No order exists for " + orderDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        }
        return allOrdersByDateList;
    }

    @Override
    // get order by date & order number using getAllOrders() from Order Dao
    // getAllOrders() as Collection & Stream for order date & order number
    // store order date & number in new ArrayList<>() & collect stream to list
    public Order getOrderByDateAndOrderNumber(int orderNumber, LocalDate orderDate) throws NoOrderExistsByOrderNumberAndByDateException {
        Order singleOrderByDateAndOrderNumber = null;
      
        Collection<Order> allOrders = orderDao.getAllOrders();
        Stream<Order> OrderByDateAndOrderNumberStream = allOrders
                .stream()
                .filter(p -> p.getOrderDate()
                .equals(orderDate)
                && p.getOrderNumber() == orderNumber);

        List<Order> orderByDateAndOrderNumber = new ArrayList<>();

        orderByDateAndOrderNumber = OrderByDateAndOrderNumberStream
                .collect(Collectors
                        .toList());
        
        if (!orderByDateAndOrderNumber.isEmpty()) {
            singleOrderByDateAndOrderNumber = orderByDateAndOrderNumber.get(0);
        } else {
            throw new NoOrderExistsByOrderNumberAndByDateException(
                    "No order exists for order number: " + orderNumber 
                        + " and date: " + orderDate
                                .format(formatter) + ".");

        }
        

        return singleOrderByDateAndOrderNumber;
    }

    @Override
    // create order
    public Order createOrder(Order newOrder) {
        setOrderNumber(newOrder);
        setOrderFields(newOrder);
        return orderDao.addOrder(newOrder);
    }

    @Override
    // remove order
    public void removeOrder(Order removedOrder) {
        orderDao.removeOrder(removedOrder);
    }

    @Override
    // edit order
    public void editOrder(Order editedOrder) {
        orderDao.editOrder(editedOrder.getOrderNumber(), editedOrder);
    }

    @Override
    // generate order data
    public Order generateOrderData(Order newOrder) {
        setOrderFields(newOrder);
        Order generatedOrder = newOrder;
        return generatedOrder;
    }

    @Override
    // save
    public void save(String mode) throws FlooringMasteryPersistenceException {
        if (mode.equals("PROD")) {
            orderDao.writeOrderData();
        }
    }
    
    // generate order number
    private int generateOrderNumber(int highestOrderNumber) {
        int newOrderNumber = highestOrderNumber + 1;
        return newOrderNumber;
    }
    
    // validate order number
    private int validateOrderNumber() {       
        List<Order> allOrders = orderDao.getAllOrders();
        ArrayList<Integer> orderNumbers = new ArrayList<>();
        for (Order allOrderNumbers : allOrders) {
            int orderNumber = allOrderNumbers.getOrderNumber();
            orderNumbers.add(orderNumber);
        }
        int highestOrderNumber = Collections.max(orderNumbers);
        int newOrderNumber = generateOrderNumber(highestOrderNumber);
        return newOrderNumber;
    }
    
    // set order number
    private void setOrderNumber(Order newOrder) {
        int newOrderNumber = validateOrderNumber();
        newOrder.setOrderNumber(newOrderNumber);
    }

    // set order fields
    private void setOrderFields(Order newOrder) {
        String state = newOrder.getState();
        newOrder.setTaxRate(taxDao.getTax(state).getTaxRate());

        String productType = newOrder.getProductType();
        newOrder.setCostPerSqFt(productDao.getProduct(productType).getCostPerSqFt());
        newOrder.setLaborCostPerSqFt(productDao.getProduct(productType).getLaborCostPerSqFt());

        BigDecimal materialCost = newOrder.getArea().multiply(newOrder.getCostPerSqFt());
        newOrder.setMaterialCost(materialCost);

        BigDecimal laborCost = newOrder.getArea().multiply(newOrder.getLaborCostPerSqFt());
        newOrder.setLaborCost(laborCost);

        BigDecimal materialAndLaborCostTotal = materialCost.add(laborCost);

        BigDecimal tax = newOrder.getTaxRate().multiply(materialAndLaborCostTotal);
        newOrder.setTax(tax.movePointLeft(2));

        BigDecimal total = materialAndLaborCostTotal.add(tax.movePointLeft(2));
        newOrder.setTotal(total);
    }
}
