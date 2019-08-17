/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayer;
import com.sg.flooringmastery.service.NoOrderExistsByDateException;
import com.sg.flooringmastery.service.NoOrderExistsByOrderNumberAndByDateException;
import com.sg.flooringmastery.ui.FlooringMasteryView;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author keb03_000
 */
public class FlooringMasteryController {
    
    private FlooringMasteryView view;
    private FlooringMasteryServiceLayer service;
    
    public String mode;
    
    public FlooringMasteryController(FlooringMasteryView view, 
            FlooringMasteryServiceLayer service) {
        
        this.view = view;
        this.service = service;
        
    }
        
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
       
        viewProgramBanner();
        determineMode();
       
        try {
            service.load();
            while(keepGoing) {
                menuSelection = getMenuSelection();
                switch(menuSelection) {
                    case 1:
                        listAllOrdersByDate();
                        break;
                    case 2:
                        createOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        saveWork();
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
                
            }
            exitMessage();
            
        }catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        
                    
        
    }
        
    private void viewProgramBanner() {
        view.displayProgramBanner();
    }
    
    private void determineMode() {
        view.displayModeBanner();
        mode = view.getMode();
        boolean isProd = service.bootConfig(mode);
        view.displayMode(isProd); 
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void listAllOrdersByDate() {
        try {
            view.displayListOrdersBanner();
            LocalDate orderDate = view.getDate();
            List<Order> ordersByDate = service.getAllOrdersByDate(orderDate);
            view.displayOrderListByDate(ordersByDate);
        } catch (NoOrderExistsByDateException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private List<Tax> listAllTaxes() {
        List<Tax> allTaxes = service.getAllTaxes();
        return allTaxes;
    }
    
    private List<Product> listAllProducts() {
        List<Product> allProducts = service.getAllProducts();
        return allProducts;
    }
    
    private void createOrder() {
        // add/creat order banner
        view.displayAddOrderBanner();
        // all taxes list
        List<Tax> allTaxes = listAllTaxes();
        // all products list
        List<Product> allProducts = listAllProducts();
        // new order
        Order newOrder = view.getNewOrderInfo(allTaxes, allProducts);
        // generated order
        Order generatedOrder = service.generateOrderData(newOrder);
        // created order
        Order createdOrder = view.displayUserOrderEntry(generatedOrder);
        // user entry
        boolean userInput = view.userChoice();
        if (userInput) {
            // create order
            service.createOrder(createdOrder);
            // create order success banner
            view.displayCreateOrderSuccessBanner();
        } else {
            // display entry discarded
            view.displayEntryDiscarded();
        }
                
        
    }
      
    private void editOrder() {
        try {
            // List all taxes & Products
            List<Tax> allTaxes = listAllTaxes();
            List<Product> allProducts = listAllProducts();
            // Get old order # and date
            int orderNum = view.getOrderNumber();
            LocalDate date = view.getDate();
            // Get order by date and order #
            Order order = service.getOrderByDateAndOrderNumber(orderNum, date);
            // Display and get edited order
            Order editedOrder = view.displayAndGetEditedOrder(order, allTaxes, allProducts);
            // Generate order data
            Order generatedOrder = service.generateOrderData(editedOrder);
            // Display generated order
            view.displaySingleOrder(generatedOrder);
            // Edit order correct
            editedOrder = view.editOrderCorrect(order, editedOrder);
            // Edit order
            service.editOrder(editedOrder);
        } catch (NoOrderExistsByOrderNumberAndByDateException e) {
            // Display error message
            view.displayErrorMessage(e.getMessage());               
        }
    }
    
    private void removeOrder() {
        try {
            // Display remove order banner
            view.displayRemoveOrderBanner();
            // Get order # and date
            int orderNum = view.getOrderNumber();
            LocalDate date = view.getDate();
            // Get order by date and order #
            Order removedOrder = service.getOrderByDateAndOrderNumber(orderNum, date);
            // Display removed order
            removedOrder = view.displayRemovedOrder(removedOrder);
            
            boolean userChoice = view.userChoice();
            if (userChoice) {            
                // Remove order
                service.removeOrder(removedOrder);
                // Display remove order success
                view.displayRemoveOrderSuccessBanner();
            } else {
                // Display entry not removed
                view.displayOrderNotRemoved();
            }
        } catch (NoOrderExistsByOrderNumberAndByDateException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    
    private void saveWork() throws FlooringMasteryPersistenceException {
        service.save(mode);
        //boolean isProd = service.bootConfig(mode);
        //view.displaySuccessfulSave(isProd);
    }
        
    private void exitMessage() throws FlooringMasteryPersistenceException {
        String save = view.displaySave();
        
        if (save.equals("Y")) {
            service.save(mode);
            boolean isProd = service.bootConfig(mode);
            view.displaySuccessfulSave(isProd);
            
        }
        view.displayExitBanner();
        
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
}
