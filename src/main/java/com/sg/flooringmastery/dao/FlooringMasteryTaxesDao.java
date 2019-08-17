/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;
import java.util.List;

/**
 *
 * @author keb03_000
 */
public interface FlooringMasteryTaxesDao {
    
    public Tax getTax(String state);
    
    public Tax addTax(Tax newTax);
    
    public Tax removeTax(Tax removedTax);
    
    public List<Tax> getAllTaxes();
    
    public void editTax(String state, Tax taxToEdit);
    
    public void loadTaxData() throws FlooringMasteryPersistenceException;
    
    public void saveTaxData() throws FlooringMasteryPersistenceException;

}
