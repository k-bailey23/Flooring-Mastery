/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 *
 * @author keb03_000
 */
public class Product {
    /*
    ProductType,CostPerSquareFoot,LaborCostPerSquareFoot
        Carpet,2.25,2.10
        Laminate,1.75,2.10
        Tile,3.50,4.15
        Wood,5.15,4.75
    */
    
    private String productType;
    private BigDecimal costPerSqFt;
    private BigDecimal laborCostPerSqFt;
    
    public Product() {
        
    }
    
    public Product(String productType,
            BigDecimal costPerSqFt, BigDecimal laborCostPerSqFt) {
        
        this.productType = productType;
        this.costPerSqFt = costPerSqFt.setScale(2, RoundingMode.HALF_UP);
        this.laborCostPerSqFt = laborCostPerSqFt.setScale(2, RoundingMode.HALF_UP);
    }
    
    public String getProductType() {
        return productType;
    }
    
    public void setProductType(String productType) {
        this.productType = productType;
    }
    
    public BigDecimal getCostPerSqFt() {
        return costPerSqFt;
    }
    
    public void setCostPerSqFt(BigDecimal costPerSqFt) {
        this.costPerSqFt = costPerSqFt.setScale(2, RoundingMode.HALF_UP);
    }
    
    public BigDecimal getLaborCostPerSqFt() {
        return laborCostPerSqFt;
    }
    
    public void setLaborCostPerSqFt(BigDecimal laborCostPerSqFt) {
        this.laborCostPerSqFt = laborCostPerSqFt.setScale(2, RoundingMode.HALF_UP);
    }

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.productType);
        hash = 31 * hash + Objects.hashCode(this.costPerSqFt);
        hash = 31 * hash + Objects.hashCode(this.laborCostPerSqFt);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.costPerSqFt, other.costPerSqFt)) {
            return false;
        }
        if (!Objects.equals(this.laborCostPerSqFt, other.laborCostPerSqFt)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "ProductType: " + productType + " CostPerSqFt: " + costPerSqFt 
                + " LaborCostPerSqFt: " + laborCostPerSqFt;
    }
}
