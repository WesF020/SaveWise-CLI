// src/main/java/com/savewise/dao/TransactionDAOImpl.java
package com.savewise.dao;

import com.savewise.connection.ConnectionFactory;
import com.savewise.model.Transaction;
import com.savewise.model.TransactionType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImpl implements ITransactionDAO {

    // 1. INSERT (DB)
    @Override
    public void save(Transaction transaction) {
        String sql = "INSERT INTO transactions (description, amount, type, category, date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, transaction.getDescription());
            stmt.setBigDecimal(2, transaction.getAmount());
            stmt.setString(3, transaction.getType().name());
            stmt.setString(4, transaction.getCategory());
            stmt.setDate(5, Date.valueOf(transaction.getDate()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving transaction", e);
        }
    }

    // 2. SELECT ALL (DB)
    @Override
    public List<Transaction> findAll() {
        String sql = "SELECT * FROM transactions";
        List<Transaction> transactions = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Transaction transaction = new Transaction(
                        rs.getInt("id"),
                        rs.getString("description"),
                        rs.getBigDecimal("amount"),
                        TransactionType.valueOf(rs.getString("type")),
                        rs.getString("category"),
                        rs.getDate("date").toLocalDate()
                );
                transactions.add(transaction);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching transactions", e);
        }

        return transactions;
    }

    // 3. DELETE (DB)
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM transactions WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting transaction", e);
        }
    }
}