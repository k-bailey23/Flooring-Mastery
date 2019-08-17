/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.util.List;

/**
 *
 * @author keb03_000
 */
public interface FlooringMasteryProductsDao {
    
    public Product getProduct(String productType);
    
    public Product addProduct(Product newProduct);
    
    public Product removeProduct(Product removedProduct);
    
    public List<Product> getAllProducts();
    
    public void editProduct(String productType, Product productToEdit);
    
    public void loadProductData() throws FlooringMasteryPersistenceException;
    
    public void writeProductData() throws FlooringMasteryPersistenceException;

}
