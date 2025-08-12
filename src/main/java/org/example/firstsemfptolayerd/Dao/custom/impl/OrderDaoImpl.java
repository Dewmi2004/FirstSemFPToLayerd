package org.example.firstsemfptolayerd.Dao.custom.impl;

import org.example.firstsemfptolayerd.Dao.custom.OrderDao;
import org.example.firstsemfptolayerd.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {
    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean Save(Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Order dto) throws SQLException, ClassNotFoundException {
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
