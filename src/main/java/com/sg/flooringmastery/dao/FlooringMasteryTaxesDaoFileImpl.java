/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;
import java.io.BufferedReader;
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
public class FlooringMasteryTaxesDaoFileImpl implements FlooringMasteryTaxesDao {

    public final String DATA_FILE = "taxes.txt";
    public final String DELIMITER = "::";
    
    
    private Map<String, Tax> taxes = new LinkedHashMap<>();
    
    
    /*
    +--------------------------------------------------------------------------------------------------------+
    |                                             unmarshallTax                                              |
    +--------------------------------------------------------------------------------------------------------+
    */
    // Parameters: String[] currentTokens
    // Returns: Tax newTax
    
    private Tax unmarshallTax(String[] currentTokens) throws FlooringMasteryPersistenceException {
        
        try {
            Tax newTax = new Tax();
            newTax.setState(currentTokens[0]);
            newTax.setTaxRate(new BigDecimal(currentTokens[1]));
        
            return newTax;            
        } catch(NumberFormatException e) {
            throw new FlooringMasteryPersistenceException("File data error.", e);
        }

    }
    
    /*
    +--------------------------------------------------------------------------------------------------------+
    |                                              marshallTax                                               |
    +--------------------------------------------------------------------------------------------------------+
    */
    // Parameters: Tax singleTax
    // Returns: String
    
    private String marshallTax(Tax singleTax) {
        
        return singleTax.getState() 
                + DELIMITER + singleTax.getTaxRate();

    }
    
    /*
    +--------------------------------------------------------------------------------------------------------+
    |                                                getTax                                                  |
    +--------------------------------------------------------------------------------------------------------+
    */
    // Parameters: String state
    // Returns: Tax
    
    @Override
    public Tax getTax(String state) {
        return this.taxes.get(state);
    }

    /*
    +--------------------------------------------------------------------------------------------------------+
    |                                                addTax                                                  |
    +--------------------------------------------------------------------------------------------------------+
    */
    // Parameters: Tax newTax
    // Returns: Tax newTax
    
    @Override
    public Tax addTax(Tax newTax) {
        this.taxes.put(newTax.getState(), newTax);
        return newTax;
    }

    /*
    +--------------------------------------------------------------------------------------------------------+
    |                                               removeTax                                                |
    +--------------------------------------------------------------------------------------------------------+
    */
    // Parameters: Tax removedTax
    // Returns: Tax removedTax
    
    @Override
    public Tax removeTax(Tax removedTax) {
        this.taxes.remove(removedTax.getState());
        return removedTax;
    }

    /*
    +--------------------------------------------------------------------------------------------------------+
    |                                              getAllTaxes                                               |
    +--------------------------------------------------------------------------------------------------------+
    */
    // Parameters: none
    // Returns: List<Tax> new ArrayList<>(allTaxes)
    
    @Override
    public List<Tax> getAllTaxes() {
        Collection<Tax> allTaxes = this.taxes.values();
        return new ArrayList<>(allTaxes);
    }

    /*
    +--------------------------------------------------------------------------------------------------------+
    |                                                editTax                                                 |
    +--------------------------------------------------------------------------------------------------------+
    */
    // Parameters: String state, Tax taxToEdit
    // Returns: void
    @Override
    public void editTax(String state, Tax taxToEdit) {
        if (state.equals(taxToEdit.getState())) {
            this.taxes.replace(state, taxToEdit);
        } else {
            Tax oldTax = this.taxes.remove(state);
            this.taxes.put(taxToEdit.getState(), taxToEdit);
        }
    }

    /*
    +--------------------------------------------------------------------------------------------------------+
    |                                              loadTaxData                                               |
    +--------------------------------------------------------------------------------------------------------+
    */
    // Parameters: none
    // Returns: void
    
    @Override
    public void loadTaxData() throws FlooringMasteryPersistenceException {
        Scanner scanner;
        
        try {
            FileReader fileReader = new FileReader(DATA_FILE);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            scanner = new Scanner(bufferedReader);
            
            while (scanner.hasNextLine()) {
                Tax newTax = new Tax();
                String currentLine = scanner.nextLine();
                String[] currentTokens = currentLine.split(DELIMITER);
                
                newTax = unmarshallTax(currentTokens);
                this.addTax(newTax);
            }
            scanner.close();
        } catch (FileNotFoundException noFile) {
            throw new FlooringMasteryPersistenceException(DATA_FILE + " not found.", noFile);
        }
    }

    /*
    +--------------------------------------------------------------------------------------------------------+
    |                                              saveTaxData                                              |
    +--------------------------------------------------------------------------------------------------------+
    */
    // Parameters: none
    // Returns: void
    
    @Override
    public void saveTaxData() throws FlooringMasteryPersistenceException {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(DATA_FILE));
            Collection<Tax> allTaxes = this.taxes.values();
            for (Tax tax : allTaxes) {
                String taxString = this.marshallTax(tax);
                printWriter.println(taxString);
                printWriter.flush();
            }
            printWriter.close();
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Data couldn't be saved.", e);
        }
    }
    
}
