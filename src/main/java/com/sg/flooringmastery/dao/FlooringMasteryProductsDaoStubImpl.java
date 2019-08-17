/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author keb03_000
 */


public class FlooringMasteryProductsDaoStubImpl implements FlooringMasteryProductsDao {

    

    private Map<String, Product> productList = new LinkedHashMap<>();
    public int loads = 0;
    public int saves = 0;
   
    public FlooringMasteryProductsDaoStubImpl() {
        Product product = new Product();
        product.setProductType("Gold");
        product.setCostPerSqFt(new BigDecimal(100.00));
        product.setLaborCostPerSqFt(new BigDecimal(50.00));
        
        productList.put(product.getProductType(), product);
    }
    
    @Override
    public Product getProduct(String productType) {
        return this.productList.get(productType);
    }

    @Override
    public Product addProduct(Product newProduct) {
        productList.put(newProduct.getProductType(), newProduct);
        return newProduct;
    }

    @Override
    public Product removeProduct(Product removedProduct) {
        productList.remove(removedProduct);
        return removedProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        Collection<Product> products = this.productList.values();
        return new ArrayList<>(products);
    }

    @Override
    public void editProduct(String productType, Product productToEdit) {
    }

    @Override
    public void loadProductData() throws FlooringMasteryPersistenceException {
        loads++;
    }

    @Override
    public void writeProductData() throws FlooringMasteryPersistenceException {
        saves++;
    }
    
}
