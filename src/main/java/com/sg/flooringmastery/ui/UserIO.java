/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import java.math.BigDecimal;

/**
 *
 * @author keb03_000
 */
public interface UserIO {
        
    void print(String msg);
    
    int readInt(String prompt);
    
    int readInt(String prompt, int min, int max);
    
    double readDouble(String prompt);
    
    double readDouble(String prompt, double min, double max);
    
    float readFloat(String prompt);
    
    float readFloat(String prompt, float min, float max);
    
    long readLong(String prompt);
    
    long readLong(String prompt, long min, long max);
    
    BigDecimal readBigDecimal(String prompt);
    
    BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max);
    
    String readString(String prompt);
}
