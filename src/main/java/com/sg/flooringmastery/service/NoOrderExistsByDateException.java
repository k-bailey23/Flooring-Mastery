/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

/**
 *
 * @author keb03_000
 */
public class NoOrderExistsByDateException extends Exception {
    
    public NoOrderExistsByDateException(String message) {
        super(message);
    }
    
    public NoOrderExistsByDateException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
