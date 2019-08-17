/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.advice;

import com.sg.flooringmastery.dao.FlooringMasteryAuditDao;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author keb03_000
 */
public class ExceptionLoggingAdvice {
    FlooringMasteryAuditDao auditDao;
    
    public ExceptionLoggingAdvice(FlooringMasteryAuditDao auditDao) {
        this.auditDao = auditDao;
    }
    public void createExceptionAuditEntry(JoinPoint jp, Exception ex) {
        String auditEntry = jp.getSignature().getName() + ": " + ex.getMessage();
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch(FlooringMasteryPersistenceException e) {
            System.err.println(
                    "ERROR: Couldn't create audit entry in ExceptionLoggingAdvice.");
        }
    }
}
