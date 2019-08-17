/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author keb03_000
 */
public class FlooringMasteryProductsDaoFileImpl implements FlooringMasteryProductsDao{

    public final String DATA_FILE = "products.txt";
    public final String DELIMITER = "::";
    
    
    private Map<String, Product> products = new LinkedHashMap<>();
    
    /*
    +--------------------------------------------------------------------------------------------------------+
    |                                             unmarshallProduct                                          |
    +--------------------------------------------------------------------------------------------------------+
    */
    // Parameters: String[] currentTokens
    // Returns: Product
    
    private Product unmarshallProduct(String[] currentTokens) throws FlooringMasteryPersistenceException {
        
        try {
            Product newProduct = new Product();
            newProduct.setProductType(currentTokens[0]);
            newProduct.setCostPerSqFt(new BigDecimal(currentTokens[1]));
            newProduct.setLaborCostPerSqFt(new BigDecimal(currentTokens[2]));
            
            return newProduct;
            
        } catch(NumberFormatException e) {
            throw new FlooringMasteryPersistenceException("File data error.", e);
        }
    }
    
    /*
    +--------------------------------------------------------------------------------------------------------+
    |                                            unmarshallProduct                                           |
    +--------------------------------------------------------------------------------------------------------+
    */
    // Parameters: Product singleProduct
    // Returns: String
    
    private String marshallProduct(Product singleProduct) {
        
        return singleProduct.getProductType() 
                + DELIMITER + singleProduct.getCostPerSqFt()
                + DELIMITER + singleProduct.getLaborCostPerSqFt();
    }
    
    /*
    +--------------------------------------------------------------------------------------------------------+
    |                                               getProduct                                               |
    +--------------------------------------------------------------------------------------------------------+   
    */
    // Parameters: String productType
    // Returns: Product
    
    @Override
    public Product getProduct(String productType) {
        return this.products.get(productType);
    }

    /*
    +--------------------------------------------------------------------------------------------------------+
    |                                               addProduct                                               |
    +--------------------------------------------------------------------------------------------------------+   
    */
    // Parameters: Product newProduct
    // Returns: Product
    
    @Override
    public Product addProduct(Product newProduct) {
        this.products.put(newProduct.getProductType(), newProduct);
        return newProduct;
    }

    /*
    +--------------------------------------------------------------------------------------------------------+
    |                                             removeProduct                                              |
    +--------------------------------------------------------------------------------------------------------+   
    */
    // Parameters: Product removedProduct
    // Returns: Product
    
    @Override
    public Product removeProduct(Product removedProduct) {
        this.products.remove(removedProduct.getProductType());
        return removedProduct;
    }

    /*
    +--------------------------------------------------------------------------------------------------------+
    |                                             getAllProducts                                             |
    +--------------------------------------------------------------------------------------------------------+   
    */
    // Parameters: none
    // Returns: List<Product>
    
    @Override
    public List<Product> getAllProducts() {
        Collection<Product> allProducts = this.products.values();
        return new ArrayList<>(allProducts);
    }

    /*
    +--------------------------------------------------------------------------------------------------------+
    |                                               editProduct                                              | 
    +--------------------------------------------------------------------------------------------------------+   
    */
    // Parameters: String productType, Product productToEdit
    // Returns: void
    
    @Override
    public void editProduct(String productType, Product productToEdit) {
        if (productType.equals(productToEdit.getProductType())) {
            this.products.replace(productType, productToEdit);
        } else {
            Product oldProduct = this.products.remove(productType);
            this.products.put(productToEdit.getProductType(), productToEdit);
        }
    }

    /*
    +--------------------------------------------------------------------------------------------------------+
    |                                             loadProductData                                            |
    +--------------------------------------------------------------------------------------------------------+   
    */
    // Parameters: none
    // Returns: void
    
    @Override
    public void loadProductData() throws FlooringMasteryPersistenceException {
        Scanner scanner;
        
        try {
            
            FileReader fileReader = new FileReader(DATA_FILE);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            scanner = new Scanner(bufferedReader);
            
            while (scanner.hasNextLine()) {
                Product newProduct = new Product();
                String currentLine = scanner.nextLine();
                String[] currentTokens = currentLine.split(DELIMITER);
                
                newProduct = unmarshallProduct(currentTokens);
                this.addProduct(newProduct);
            }
            scanner.close();
        } catch (FileNotFoundException noFile) {
            throw new FlooringMasteryPersistenceException(DATA_FILE + " not found.", noFile);
        }
    }

    /*
    +--------------------------------------------------------------------------------------------------------+
    |                                             writeProductData                                           |
    +--------------------------------------------------------------------------------------------------------+   
    */
    // Parameters: none
    // Returns: void
    
    @Override
    public void writeProductData() throws FlooringMasteryPersistenceException {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(DATA_FILE));
            Collection<Product> allProducts = this.products.values();
            for (Product product : allProducts) {
                String productString = this.marshallProduct(product);
                printWriter.println(productString);
                printWriter.flush();
            }
            printWriter.close();
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Data couldn't be saved.", e);
        }
    }
    
}
