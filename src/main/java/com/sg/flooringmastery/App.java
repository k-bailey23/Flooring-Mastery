/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery;

import com.sg.flooringmastery.controller.FlooringMasteryController;
import com.sg.flooringmastery.dao.FlooringMasteryAuditDao;
import com.sg.flooringmastery.dao.FlooringMasteryAuditDaoFileImpl;
import com.sg.flooringmastery.dao.FlooringMasteryOrdersDao;
import com.sg.flooringmastery.dao.FlooringMasteryOrdersDaoFileImpl;
import com.sg.flooringmastery.dao.FlooringMasteryProductsDao;
import com.sg.flooringmastery.dao.FlooringMasteryProductsDaoFileImpl;
import com.sg.flooringmastery.dao.FlooringMasteryTaxesDao;
import com.sg.flooringmastery.dao.FlooringMasteryTaxesDaoFileImpl;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayer;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayerImpl;
import com.sg.flooringmastery.ui.FlooringMasteryView;
import com.sg.flooringmastery.ui.UserIO;
import com.sg.flooringmastery.ui.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author keb03_000
 */
public class App {
    public static void main(String[] args) {
        /*
        UserIO myIo = new UserIOConsoleImpl();
        FlooringMasteryView myView = new FlooringMasteryView(myIo);
        FlooringMasteryOrdersDao myOrderDao = new FlooringMasteryOrdersDaoFileImpl();
        FlooringMasteryProductsDao myProductDao = new FlooringMasteryProductsDaoFileImpl();
        FlooringMasteryTaxesDao myTaxDao = new FlooringMasteryTaxesDaoFileImpl();
        FlooringMasteryAuditDao myAuditDao = new FlooringMasteryAuditDaoFileImpl();
        FlooringMasteryServiceLayer myService = new FlooringMasteryServiceLayerImpl(myOrderDao, myProductDao, myTaxDao);
        FlooringMasteryController controller = new FlooringMasteryController(myView, myService);
        controller.run();     
        */
        
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        FlooringMasteryController controller
                = ctx.getBean("controller", FlooringMasteryController.class);
        
        controller.run();
         
    }
}
