/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;
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
public class FlooringMasteryTaxesDaoStubImpl implements FlooringMasteryTaxesDao {

    private Map<String, Tax> taxList = new LinkedHashMap<>();
    
    public int loads = 0;
    public int saves = 0;
    
    public FlooringMasteryTaxesDaoStubImpl() {
        Tax tax = new Tax();
        tax.setState("KY");
        tax.setTaxRate(new BigDecimal(1.00));
        
        taxList.put(tax.getState(), tax);
    }
    @Override
    public Tax getTax(String state) {
        return this.taxList.get(state);
        
    }

    @Override
    public Tax addTax(Tax newTax) {
        taxList.put(newTax.getState(), newTax);
        return newTax;
        
    }

    @Override
    public Tax removeTax(Tax removedTax) {
        taxList.remove(removedTax);
        return removedTax;
        
    }

    @Override
    public List<Tax> getAllTaxes() {
        Collection<Tax> taxes = this.taxList.values();
        return new ArrayList<>(taxes);
        
    }

    @Override
    public void editTax(String state, Tax taxToEdit) {
    }

    @Override
    public void loadTaxData() throws FlooringMasteryPersistenceException {
        loads++;
    }

    @Override
    public void saveTaxData() throws FlooringMasteryPersistenceException {
        saves++;
    }
    
}
