/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author keb03_000
 */
public class FlooringMasteryView {

    private UserIO io;

    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }
    
    public void displayProgramBanner() {
        io.print("************************************************************************");
        io.print("***                                                                  ***");
        io.print("***                  Software Guild Java OOP Online                  ***");
        io.print("***                                                                  ***");
        io.print("***                         Flooring Mastery                         ***");
        io.print("***                                                                  ***");
        io.print("************************************************************************");

    }
    
    public void displayModeBanner() {
            io.print("");
            io.print("             +-------------------------------------------+");
            io.print("             |                Select Mode                |");
            io.print("             |            (PROD or TRAINING)             |");
            io.print("             +-------------------------------------------+");
            io.print("");
    }
    
    public String getMode() {
            io.print("Enter 'PROD' for prod mode.");
            io.print("Enter anything else for training mode");
            io.print("");
            return io.readString("Mode Choice?");
    }
    
    public void displayMode(boolean isProd) {
        
        if (isProd == true) {
            io.print("             +-------------------------------------------+");
            io.print("             |                 Prod Mode                 |");
            io.print("             +-------------------------------------------+");
        } else {
            io.print("             +-------------------------------------------+");
            io.print("             |               Training Mode               |");
            io.print("             +-------------------------------------------+");
        }
    }

    public int printMenuAndGetSelection() {
            io.print("");
            io.print("                     +---------------------------+");
            io.print("                     |    <<Flooring Mastery>>   |");
            io.print("                     |                           |");
            io.print("                     | 1. Display Orders         |");
            io.print("                     | 2. Add an Order           |");
            io.print("                     | 3. Edit an Order          |");
            io.print("                     | 4. Remove an Order        |");
            io.print("                     | 5. Save Current Work      |");
            io.print("                     | 6. Quit                   |");
            io.print("                     |                           |");
            io.print("                     +---------------------------+");

        return io.readInt("\nSelect menu option from the choices above.", 1, 6);

    }
    // User Choice to Proceed (Y/N)
    public boolean userChoice() {
        String proceed = io.readString("Proceed? (Y/N)?").toUpperCase();
        boolean userChoice;
        while (!proceed.equals("Y") && !proceed.equals("N")) {
            io.print("***Error***");
            proceed = io.readString("Please enter valid choice.").toUpperCase();
        }
        if (proceed.equals("Y")) {
            userChoice = true;
        } else {
            userChoice = false;
        }
        return userChoice;
    }

    public void displayListOrdersBanner() {
        io.print("             +-------------------------------------------+");
        io.print("             |               Display Orders              |");
        io.print("             +-------------------------------------------+");
    }

    public void displayOrderListByDate(List<Order> allOrdersByDateList) {
        io.print("");
        io.print("    +-------------------------------------------------------------+");
        io.print("    |                            Orders                           |");
        io.print("    +-------------------------------------------------------------+");
        
        // Stream all orders by date
        Stream<Order> allOrdersByDateStream = allOrdersByDateList.stream();
        allOrdersByDateStream.forEach(s -> 
                io.print("\n                    Order Number:            " + s.getOrderNumber() + "\n" 
                + "                    Order Date:              " + s.getOrderDateString() + "\n"
                + "                    Customer Name:           " + s.getCustomerName() + "\n"
                + "                    State:                   " + s.getState() + "\n"
                + "                    Tax Rate:                " + s.getTaxRate() + "%" + "\n"
                + "                    Product Type:            " + s.getProductType() + "\n"
                + "                    Area:                    " + s.getArea() + " sq ft" + "\n"
                + "                    Cost per Sq Ft:          " + "$" + s.getCostPerSqFt() + "\n"
                + "                    Labor Cost per Sq Ft:    " + "$" + s.getLaborCostPerSqFt() + "\n"
                + "                    Material Cost:           " + "$" + s.getMaterialCost() + "\n"
                + "                    Labor Cost:              " + "$" + s.getLaborCost() + "\n"
                + "                    Tax:                     " + "$" + s.getTax() + "\n"
                + "                    Total Cost:              " + "$" + s.getTotal()));
    
    }

    public int getOrderNumber() {
        int orderNumber = io.readInt("Enter Order Number: ");
        return orderNumber;
    }

    public void displaySingleOrder(Order singleOrder) {
        // Single Order displayed
        io.print("                    Order Number:            " + singleOrder.getOrderNumber() + "\n"
                + "                    Order Date:              " + singleOrder.getOrderDateString() + "\n"
                + "                    Customer Name:           " + singleOrder.getCustomerName() + "\n"
                + "                    State:                   " + singleOrder.getState() + "\n"
                + "                    Tax Rate:                " + singleOrder.getTaxRate() + "\n"
                + "                    Product Type:            " + singleOrder.getProductType() + "\n"
                + "                    Area:                    " + singleOrder.getArea() + "\n"
                + "                    Cost Per Sq Ft:          " + "$" + singleOrder.getCostPerSqFt() + "\n"
                + "                    Labor Cost per Sq Ft:    " + "$" + singleOrder.getLaborCostPerSqFt() + "\n"
                + "                    Material Cost:           " + "$" + singleOrder.getMaterialCost() + "\n"
                + "                    LaborCost:               " + "$" + singleOrder.getLaborCost() + "\n"
                + "                    Tax:                     " + "$" + singleOrder.getTax() + "\n"
                + "                    Total Cost:              " + "$" + singleOrder.getTotal());
    }

    public void displayTaxList(List<Tax> allTaxesList) {
        io.print("                     +----------------------------+");
        io.print("                     |    State    |   Tax Rate   |");
        io.print("                     +----------------------------+");
        // All taxes list streamed for state and tax rate
        Stream<Tax> allTaxesStream = allTaxesList.stream();
        allTaxesStream.forEach(s -> io.print(s.getState()
                + "..." + s.getTaxRate()));
    }

    public void displayProductList(List<Product> allProductsList) {
        io.print("       +----------------------------------------------------------+");
        io.print("       |   Product Type   |   Cost/Sq ft   |   Labor Cost/Sq ft   |");
        io.print("       +----------------------------------------------------------+");
        // All products list streamed for product type, cost per sq ft
        // and labor cost per sq ft
        Stream<Product> allProductsStream = allProductsList.stream();
        allProductsStream.forEach(s -> io.print(s.getProductType()
                + " | $" + s.getCostPerSqFt() + " | $" + s.getLaborCostPerSqFt()));
    }

    public void displayAddOrderBanner() {
        io.print("             +-------------------------------------------+");
        io.print("             |                 Add Order                 |");
        io.print("             +-------------------------------------------+");
    }

    public void displayCreateOrderSuccessBanner() {
        io.print("             +-------------------------------------------+");
        io.print("             |           Order has been created          |");
        io.print("             +-------------------------------------------+");
    }

    public void displayEditOrderBanner() {
        io.print("             +-------------------------------------------+");
        io.print("             |                 Edit Order                |");
        io.print("             +-------------------------------------------+");
    }

    public void displayEditOrderNotEdited() {
        io.print("             +-------------------------------------------+");
        io.print("             |              Order not edited             |");
        io.print("             +-------------------------------------------+");
    }

    public Order displayAndGetEditedOrder(Order oldOrder,
            List<Tax> allTaxesList,
            List<Product> allProductsList) {

        // Old order displayed
        displaySingleOrder(oldOrder);
        // Get replacement data from user (enter to skip field)
        io.print("\nEnter new replacement data OR hit enter to skip field");
        
        // Edited order replacement data
        Order editedOrder = new Order();
        editedOrder.setOrderNumber(oldOrder.getOrderNumber());
        editedOrder.setOrderDate(oldOrder.getOrderDate());
        editedOrder.setCustomerName(oldOrder.getCustomerName());
        editedOrder.setState(oldOrder.getState());
        editedOrder.setTaxRate(oldOrder.getTaxRate());
        editedOrder.setProductType(oldOrder.getProductType());
        editedOrder.setArea(oldOrder.getArea());
        editedOrder.setCostPerSqFt(oldOrder.getCostPerSqFt());
        editedOrder.setLaborCostPerSqFt(oldOrder.getLaborCostPerSqFt());
        editedOrder.setMaterialCost(oldOrder.getMaterialCost());
        editedOrder.setLaborCost(oldOrder.getLaborCost());
        editedOrder.setTax(oldOrder.getTax());
        editedOrder.setTotal(oldOrder.getTotal());
        
        // Edited date
        LocalDate editedDate = getDateWithExisting(oldOrder.getOrderDate());
        // Set edited date to edited order
        editedOrder.setOrderDate(editedDate);
        // Read in edited customer name from user
        String editedName = io.readString("\nEnter name (" + oldOrder.getCustomerName() + "):");
        // if user enters "," for customer name - replace w/ ""
        if (editedName.contains(",")) {
            editedName = editedName.replace(",", "");
        }
        // if user enters anything but "" for customer name - set edited name to edited order 
        if (!editedName.equals("")) {
            editedOrder.setCustomerName(editedName);
        }
        // display all taxes list
        displayTaxList(allTaxesList);
        // Read in editedState from user
        String editedState = io.readString("\nEnter state ("+ oldOrder.getState() + "):").toUpperCase();
        // Stream all taxes list to get all states list as string
        List<String> allStatesListString = allTaxesList.stream()
                .map(tax -> tax.getState())
                    .collect(Collectors.toList());
        // while edited state not in list
        while (!allStatesListString.contains(editedState)) {
            if (editedState.equals("")) {
                break;
            }
            io.print("\n***ERROR***");
            // User prompted for valid state after error
            editedState = io.readString("Enter a valid state: ").toUpperCase();
        }
        if (!editedState.equals("")) {
            // update edited order w/ edited state
            editedOrder.setState(editedState);
        }
        // display all products list
        displayProductList(allProductsList);
        // Read in edited product type from user
        String editedProductType = io.readString("\nEnter product type (" + oldOrder.getProductType() + "):");
        // Stream all products list to get all product types as string
        List<String> allProductTypeString = allProductsList.stream()
                .map(product -> product.getProductType())
                    .collect(Collectors.toList());
        // while edited product type not in list
        while (!allProductTypeString.contains(editedProductType)) {
            if (editedProductType.equals("")) {
                break;
            }
            io.print("\n***ERROR***");
            // User prompted for valid product type after error
            editedProductType = io.readString("Enter valid product type: ");
        }
        if (!editedProductType.equals("")) {
            // update edited order w/ edited product type
            editedOrder.setProductType(editedProductType);
        }
        // get area w/ existing using oldOrder and editedOrder
        getAreaWithExisting(oldOrder, editedOrder);
        
        return editedOrder;
    }

    public Order editOrderCorrect(Order oldOrder,
            Order editedOrder) {
        boolean editProcessed = false;
        // boolean for user to keep changes or not
        String isCorrect = io.readString("\nUpdated order displayed, keep changes (Y/N)?)").toUpperCase();
        
        while (!isCorrect.equals("Y") && !isCorrect.equals("N")) {
            io.print("\n****Error****");
            isCorrect = io.readString("Enter valid choice.").toUpperCase();
        }
        if (isCorrect.equals("Y")) {
            displayEditOrderSuccess();
            editProcessed = true;
        }
        if (isCorrect.equals("N")) {
            displayEditOrderNotEdited();
            editProcessed = false;
        }
        if (!editProcessed) {
            editedOrder = oldOrder;
        }
        return editedOrder;
    }

    public void displayEditOrderSuccess() {
        io.print("             +-------------------------------------------+");
        io.print("             |           Order has been edited           |");
        io.print("             +-------------------------------------------+");
    }

    public void displayRemoveOrderBanner() {
        io.print("             +-------------------------------------------+");
        io.print("             |               Remove Order                |");
        io.print("             +-------------------------------------------+");

    }
    
    public Order displayRemovedOrder(Order removedOrder) {
        displaySingleOrder(removedOrder);
        return removedOrder;
    }

    public void displayOrderNotRemoved() {
        io.print("             +-------------------------------------------+");
        io.print("             |             Order not removed             |");
        io.print("             +-------------------------------------------+");
    }

    public void displayRemoveOrderSuccessBanner() {
        io.print("             +-------------------------------------------+");
        io.print("             |          Order has been removed           |");
        io.print("             +-------------------------------------------+");
    }

    public String displaySave() {
        // prompt user for save (Y/N)
        String save = io.readString("Save current work? (Y/N)?").toUpperCase();
        // user entry isn't "Y" or "N"
        while (!save.equals("Y") && !save.equals("N")) {
            io.print("***Error***");
            save = io.readString("Please enter valid choice.").toUpperCase();
        }
        // user entry = "N"
        if (save.equals("N")) {
       
            io.print("             +-------------------------------------------+");
            io.print("             |             Order not saved               |");
            io.print("             +-------------------------------------------+");
        }
        // String returned
        return save;
    }

    public void displaySuccessfulSave(boolean isProd) {
        // mode = training (can't save)
        if (isProd == false) {
            io.print("             +-------------------------------------------+");
            io.print("             |        Can't save in Training Mode        |");
            io.print("             +-------------------------------------------+");
        // mode = prod 
        } else {
            io.print("             +-------------------------------------------+");
            io.print("             |               Save successful             |");
            io.print("             +-------------------------------------------+");
        }
    }

    public void displayExitBanner() {
        io.print("             +-------------------------------------------+");
        io.print("             |                  Goodbye!                 |");
        io.print("             +-------------------------------------------+");
    }

    public Order displayUserOrderEntry(Order generatedOrder) {
    
        io.print("");
        io.print("     +-------------------------------------------------------------+");
        io.print("     |                     User Order Entry                        |");
        io.print("     +-------------------------------------------------------------+");
        // user order entry data
        io.print("                    Order Date:             " + generatedOrder.getOrderDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + "\n"
                + "                   Customer Name:          " + generatedOrder.getCustomerName() + "\n"
                + "                   State:                  " + generatedOrder.getState() + "\n"
                + "                   Tax Rate:               " + generatedOrder.getTaxRate() + "\n"
                + "                   Product Type:           " + generatedOrder.getProductType() + "\n"
                + "                   Area (sq ft):           " + generatedOrder.getArea() + "\n"
                + "                   Cost per sq ft:         " + generatedOrder.getCostPerSqFt() + "\n"
                + "                   Labor cost per sq ft:   " + generatedOrder.getLaborCostPerSqFt() + "\n"
                + "                   Material cost:          " + generatedOrder.getMaterialCost() + "\n"
                + "                   Labor cost:             " + generatedOrder.getLaborCost() + "\n"
                + "                   Tax:                    " + generatedOrder.getTax() + "\n"
                + "                   Total:                  " + generatedOrder.getTotal());
      
        return generatedOrder;
    }

    public Order getNewOrderInfo(List<Tax> allTaxesList,
            List<Product> allProductsList) {
        // order date
        LocalDate dateOrdered = getDate();
        String customerName = io.readString("\nEnter in a name: ");
        
        if (customerName.contains(",")) {
            customerName = customerName.replace(",", " ");
        }
        
        // All taxes list displayed
        displayTaxList(allTaxesList);
        
        String state = io.readString("\nEnter in a state: ").toUpperCase();
        List<String> statesListString = allTaxesList.stream()
                .map(tax -> tax.getState())
                    .collect(Collectors.toList());
        
        while (!statesListString.contains(state)) {
            io.print("\n***Error***");
            state = io.readString("Enter a valid state: ").toUpperCase();
        }
        
        // All products list displayed
        displayProductList(allProductsList);
        
        String productType = io.readString("\nEnter in a product type: ");
        List<String> productTypesString = allProductsList.stream()
                .map(product -> product.getProductType())
                    .collect(Collectors.toList());
        
        while (!productTypesString.contains(productType)) {
            io.print("\n***Error***");
            productType = io.readString("Enter in a valid product type: ");
        }
        
        // new area & new order
        BigDecimal area = getArea();
        Order newOrder = new Order();
        newOrder.setOrderDate(dateOrdered);
        newOrder.setCustomerName(customerName);
        newOrder.setState(state);
        newOrder.setProductType(productType);
        newOrder.setArea(area);
        
        return newOrder;
    }

    public LocalDate getDate() {
        boolean hasErrors = false;
        LocalDate dateOrdered = null;
        
        do {
            String date = io.readString("\nEnter date (MM/DD/YYYY): ");
            try {
                dateOrdered = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                hasErrors = false;
            } catch (DateTimeParseException e) {
                hasErrors = true;
                displayInvalidDate();
            }
        } while (hasErrors);
        
        return dateOrdered;
    }

    public LocalDate getDateWithExisting(LocalDate existingDate) {
        boolean hasErrors = false;
        LocalDate editedDate = null;
        
        do {
            String date = io.readString("\nEnter date (" + existingDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + "):");
            
            if (date.equals("")) {
                editedDate = existingDate;
                break;
            }
            try {
                editedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                hasErrors = false;
            } catch (DateTimeParseException e) {
                hasErrors = true;
                displayInvalidDate();
            }
        } while (hasErrors);
        
        return editedDate;

    }

    public void displayInvalidDate() {
        io.print("***Error***");
        io.print("Invalid date, please enter valid date.");
    }

    public BigDecimal getArea() {
        double area;
        
        do { 
            // read in area in sq ft
            area = io.readDouble("\nEnter approx. area in sq ft: ");
            
            if (area <= 0) {
                io.print("\n***Error***");
                io.print("Invalid area entry. Enter valid area: ");
            }
            
        
        //} while (area <= 0);
        } while (area <= 0);   
        // area as new big decimal
        BigDecimal areaBigDecimal = new BigDecimal(area).setScale(2, RoundingMode.HALF_UP);
        return areaBigDecimal;
    }

    public void getAreaWithExisting(Order oldOrder,
            Order editedOrder) {
        double editedArea;
        String editedAreaString = io.readString("\nEnter approx. area in sq ft: (" + oldOrder.getArea() + "):");
        
        if (!editedAreaString.equals("")) {
            do { 
                editedArea = Double.parseDouble(editedAreaString);

                if (editedArea <= 0) {
                    io.print("\n***Error***");
                    io.print("Invalid area entry. Enter valid area: ");
                }
            } while (editedArea <= 0);
            BigDecimal editedAreaBigDecimal = new BigDecimal(editedArea).setScale(2, RoundingMode.HALF_UP);
            // update editedOrder area data with edited area big decimal
            editedOrder.setArea(editedAreaBigDecimal);
        }
    }

    public void displayEntryDiscarded() {
        io.print("             +-------------------------------------------+");
        io.print("             |               Entry discarded             |");
        io.print("             +-------------------------------------------+");

    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown command");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("***Error***");
        io.print(errorMsg);
    }
}
