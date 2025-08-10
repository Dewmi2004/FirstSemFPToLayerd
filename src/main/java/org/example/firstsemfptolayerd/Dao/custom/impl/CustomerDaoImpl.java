package org.example.firstsemfptolayerd.Dao.custom.impl;

import org.example.firstsemfptolayerd.Dao.SQLUtil;
import org.example.firstsemfptolayerd.Dao.custom.CustomerDao;
import org.example.firstsemfptolayerd.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT * FROM customer");
        ArrayList<Customer> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(6), rs.getString(7)));
        }
        return list;
    }

    @Override
    public boolean Save(Customer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO customer VALUES(?,?,?,?,?,?,?)",
                dto.getId(), dto.getName(), dto.getAddress(), dto.getGender(),
                dto.getDob(), dto.getEmail(), dto.getContact());
    }

    @Override
    public boolean update(Customer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE customer SET customer_Name=?, customer_Address=?, customer_Gender=?, customer_Dob=?, customer_Email=?, customer_Contact=? WHERE customer_Id=?",
                dto.getName(), dto.getAddress(), dto.getGender(), dto.getDob(), dto.getEmail(), dto.getContact(), dto.getId());
    }



    @Override
    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM customer WHERE customer_Id = ? ", id);
    }


    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT customer_Id FROM customer ORDER BY customer_Id DESC LIMIT 1");
        if (rs.next()) {
            int idNum = Integer.parseInt(rs.getString(1).substring(1)) + 1;
            return String.format("C%03d", idNum);
        }
        return "C001";
    }

    @Override
    public Customer searchByPhone(String phone) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT customer_Id, customer_Name, customer_Email FROM customer WHERE customer_Contact = ?", phone);
        if (rs.next()) {
            return new Customer(
                    rs.getString("customer_Id"),
                    rs.getString("customer_Name"),
                    rs.getString("customer_Email")
            );
        }
        return null;
    }
}

