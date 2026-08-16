package com.savewise.dao;
import com.savewise.model.Transaction;

import java.util.List;

public interface ITransactionDAO {
    void save (Transaction transaction);
    List<Transaction> findAll();
    void delete (int id);
}
