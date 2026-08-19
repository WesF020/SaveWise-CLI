package com.savewise;

import com.savewise.dao.TransactionDAOImpl;
import com.savewise.ui.MenuCLI;
import com.savewise.service.TransactionService;


public class Main {
    public static void main(String[] args) {
        TransactionDAOImpl transactionDAO = new TransactionDAOImpl();
        TransactionService transactionService = new TransactionService(transactionDAO);
        MenuCLI menuCLI = new MenuCLI(transactionService);
        menuCLI.start();
    }
}