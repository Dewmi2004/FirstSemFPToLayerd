package org.example.firstsemfptolayerd.Dao.custom.impl;

import org.example.firstsemfptolayerd.Dao.custom.PaymentDao;
import org.example.firstsemfptolayerd.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDaoImpl implements PaymentDao {
    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean Save(Payment dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Payment dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }
}
