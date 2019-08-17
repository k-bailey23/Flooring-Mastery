/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author keb03_000
 */
public class FlooringMasteryOrdersDaoFileImpl implements FlooringMasteryOrdersDao{

    public String date = "";
    public final String DATA_FOLDER = "target/Orders";
    public final String DELIMITER = "::";
    
    
    private Map<Integer, Order> orders = new LinkedHashMap<>();
    
    /*
    +------------------------------------------------------------------------------------+
    |                                  unmarshallOrder                      	         |
    +------------------------------------------------------------------------------------+
    */
    // Parameters: String[] currentTokens, LocalDate orderDate
    // Returns: Order newOrder
    
    private Order unmarshallOrder(String[] currentTokens, LocalDate orderDate) throws
            FlooringMasteryPersistenceException {
        
        try {
            // Create new Order
            Order newOrder = new Order();
            // Set tokens
            newOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
            newOrder.setCustomerName(currentTokens[1]);
            newOrder.setState(currentTokens[2]);
            newOrder.setTaxRate(new BigDecimal(currentTokens[3]));
            newOrder.setProductType(currentTokens[4]);
            newOrder.setArea(new BigDecimal(currentTokens[5]));
            newOrder.setCostPerSqFt(new BigDecimal(currentTokens[6]));
            newOrder.setLaborCostPerSqFt(new BigDecimal(currentTokens[7]));
            newOrder.setMaterialCost(new BigDecimal(currentTokens[8]));
            newOrder.setLaborCost(new BigDecimal(currentTokens[9]));
            newOrder.setTax(new BigDecimal(currentTokens[10]));
            newOrder.setTotal(new BigDecimal(currentTokens[11]));
            newOrder.setOrderDate(orderDate);
            
            return newOrder;
            
        } catch (NumberFormatException e) {
            throw new FlooringMasteryPersistenceException("File data error.", e);
        }
    }
    
    /*
    +------------------------------------------------------------------------------------+
    |                                    marshallOrder              		         |
    +------------------------------------------------------------------------------------+	
    */
    // Parameters: Order singleOrder
    // Returns: String of marshalled order
    
    private String marshallOrder(Order singleOrder) {
        
        
        return singleOrder.getOrderNumber()
                + DELIMITER + singleOrder.getCustomerName()
                + DELIMITER + singleOrder.getState()
                + DELIMITER + singleOrder.getTaxRate()
                + DELIMITER + singleOrder.getProductType()
                + DELIMITER + singleOrder.getArea()
                + DELIMITER + singleOrder.getCostPerSqFt()
                + DELIMITER + singleOrder.getLaborCostPerSqFt()
                + DELIMITER + singleOrder.getMaterialCost()
                + DELIMITER + singleOrder.getLaborCost()
                + DELIMITER + singleOrder.getTax()
                + DELIMITER + singleOrder.getTotal();
        
    }
    
    /*
    +------------------------------------------------------------------------------------+
    |                                   fileNameToOrderDate		                 |
    +------------------------------------------------------------------------------------+
    */
    // Parameters: String fileName
    // Returns: LocalDate orderDate
    
    private LocalDate fileNameToOrderDate(String fileName) {
        // Replace anything besides 0-9 with ""
        String date = fileName.replaceAll("[^0-9]", "");
        // Parse orderDate to apply formatted pattern
        LocalDate orderDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMddyyyy"));
        
        return orderDate;
    }
    
    /*
    +------------------------------------------------------------------------------------+
    |                                   getAllOrdersByDate			         |
    +------------------------------------------------------------------------------------+
    */
    // Parameters: LocalDate orderDate
    // Returns: List<Order> allOrdersByDateList
    
    private List<Order> getAllOrdersByDate(LocalDate orderDate) throws FlooringMasteryPersistenceException {
        // Collection of Orders
        Collection<Order> allOrders = this.orders.values();
        // Stream of Order dates
        Stream<Order> allOrdersByDateStream = allOrders.stream().filter(p -> p.getOrderDate().equals(orderDate));
        // List of Orders by date
        List<Order> allOrdersByDateList = new ArrayList<>();
        // Add stream of collection of Orders to List
        allOrdersByDateList = allOrdersByDateStream.collect(Collectors.toList());
        return allOrdersByDateList;
    }
    
    /*
    +------------------------------------------------------------------------------------+
    |                                    getAllOrderDates				 |
    +------------------------------------------------------------------------------------+
    */
    // Parameters: none
    // Returns: Set<LocalDate> allOrderDatesSet
    
    private Set<LocalDate> getAllOrderDates() throws FlooringMasteryPersistenceException {
        // Collection of Orders
        Collection<Order> allOrders = this.orders.values();
        // Set of Order dates
        Set<LocalDate> allOrderDatesSet = new HashSet<>();
        // Get all Order dates and add to set
        for (Order allOrderDates : allOrders) {
            LocalDate orderDate = allOrderDates.getOrderDate();
            allOrderDatesSet.add(orderDate);
        }
        return allOrderDatesSet;
    }
    
    /*
    +------------------------------------------------------------------------------------+
    |                                       getOrder					 |
    +------------------------------------------------------------------------------------+
    */
    // Parameters: int orderNumber
    // Returns: Order
    
    @Override
    public Order getOrder(int orderNumber) {
        return this.orders.get(orderNumber);
    }

    /*
    +------------------------------------------------------------------------------------+
    |                                       addOrder				         |
    +------------------------------------------------------------------------------------+
    */
    // Parameters: Order newOrder
    // Returns: Order newOrder
    
    @Override
    public Order addOrder(Order newOrder) {
        this.orders.put(newOrder.getOrderNumber(), newOrder);
        return newOrder;
    }

    /*
    +------------------------------------------------------------------------------------+
    |                                      removeOrder				         |
    +------------------------------------------------------------------------------------+
    */
    // Parameters: Order removedOrder    
    // Returns: Order removedOrder
    
    @Override
    public Order removeOrder(Order removedOrder) {
        this.orders.remove(removedOrder.getOrderNumber());
        return removedOrder;
    }
	
    /*
    +------------------------------------------------------------------------------------+
    |                                      getAllOrders					 |
    +------------------------------------------------------------------------------------+
    */
    // Parameters: none
    // Returns: List<Order> new ArrayList<>(allOrders)
    
    @Override
    public List<Order> getAllOrders() {
        Collection<Order> allOrders = this.orders.values();
        return new ArrayList<>(allOrders);
    }

    /*
    +------------------------------------------------------------------------------------+
    |                                  	    editOrder                                    |
    +------------------------------------------------------------------------------------+
    */
    // Parameters: int oldOrderNumber, Order orderToEdit
    // Returns: void
    
    @Override
    public void editOrder(int oldOrderNumber, Order orderToEdit) {
        if (oldOrderNumber == orderToEdit.getOrderNumber()) {
            this.orders.replace(oldOrderNumber, orderToEdit);
        } else {
            Order oldOrder = this.orders.remove(oldOrderNumber);
            this.orders.put(orderToEdit.getOrderNumber(), orderToEdit);
        }
    }

    /*
    +------------------------------------------------------------------------------------+
    |                                    loadOrderData					 |
    +------------------------------------------------------------------------------------+
    */
    // Parameters: none
    // Returns: void
    
    @Override
    public void loadOrderData() throws FlooringMasteryPersistenceException {
        Scanner scanner;
        
        try {
            File dataFolder = new File(DATA_FOLDER);
            File[] files = dataFolder.listFiles();
            
            for (File file : files) {
                
                if (file.isFile()) {
                    // New FileReader
                    FileReader fileReader = new FileReader(file);
                    // New BufferedReader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    // New Scanner
                    scanner = new Scanner(bufferedReader);
                    
                    while (scanner.hasNextLine()) {
                        Order newOrder = new Order();
                        String currentLine = scanner.nextLine();
                        String[] currentTokens = currentLine.split(DELIMITER);
                        
                        if (currentTokens.length != 12) {
                            continue;
                        }
                        // change file name to order date
                        LocalDate orderDate = fileNameToOrderDate(file.getName());
                        // unmarshall newOrder
                        newOrder = unmarshallOrder(currentTokens, orderDate);
                        // add newOrder
                        this.addOrder(newOrder);
                    }
                    
                    scanner.close();
                             
        }
            }
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(e.getMessage());
        }
          
            
            
            
        
    }

    /*
    +------------------------------------------------------------------------------------+
    |                                   writeOrderData                                   |
    +------------------------------------------------------------------------------------+
    */
    // Parameters: none
    // Returns: void
    
    @Override
    public void writeOrderData() throws FlooringMasteryPersistenceException {
        try {
            
            File dataFolder = new File(DATA_FOLDER);
            File[] files = dataFolder.listFiles();
            
            for (File file : files) {
                
                if (file.isFile()) {
                    file.delete();
                }
            }
            // Set of Order dates
            Set<LocalDate> allOrderDatesSet = getAllOrderDates();
            for (LocalDate allOrderDates : allOrderDatesSet) {
                // List orders by date
                List<Order> allOrdersByDateList = getAllOrdersByDate(allOrderDates);
                // print updated list to data folder
                PrintWriter printWriter = new PrintWriter(new FileWriter(DATA_FOLDER + "/Orders_" + allOrderDates.format(DateTimeFormatter.ofPattern("MMddyyyy"))));
                
                for (Order singleOrder : allOrdersByDateList) {
                    // marshall Order
                    String orderAsString = this.marshallOrder(singleOrder);
                    // print marshalled Order as String
                    printWriter.println(orderAsString);
                    printWriter.flush();
                }
                printWriter.close();
            }
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Data couldn't be "
                    + "saved.", e);
        }
    }


    
}
