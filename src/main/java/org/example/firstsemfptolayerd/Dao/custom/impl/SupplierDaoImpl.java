package org.example.firstsemfptolayerd.Dao.custom.impl;

import org.example.firstsemfptolayerd.Dao.SQLUtil;
import org.example.firstsemfptolayerd.Dao.custom.SupplierDao;
import org.example.firstsemfptolayerd.entity.Customer;
import org.example.firstsemfptolayerd.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT * FROM supplier");
        ArrayList<Supplier> suppliers = new ArrayList<>();
        while (rs.next()) {
            suppliers.add(new Supplier(
                    rs.getString("sup_Id"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    rs.getString("company_Address"),
                    rs.getString("supply_Type"),
                    rs.getString("Email")
            ));
        }
        return suppliers;
    }

    @Override
    public boolean Save(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "INSERT INTO supplier VALUES (?,?,?,?,?,?)",
                supplier.getSupId(),
                supplier.getName(),
                supplier.getContact(),
                supplier.getCompanyAddress(),
                supplier.getSupplyType(),
                supplier.getEmail()
        );
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "UPDATE supplier SET name=?, contact=?, company_Address=?, supply_Type=?, Email=? WHERE sup_Id=?",
                supplier.getName(),
                supplier.getContact(),
                supplier.getCompanyAddress(),
                supplier.getSupplyType(),
                supplier.getEmail(),
                supplier.getSupId()
        );
    }


    @Override
    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM supplier WHERE sup_Id=?", id);
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT sup_Id FROM supplier ORDER BY sup_Id DESC LIMIT 1");
        if (rs.next()) {
            String lastId = rs.getString(1);
            int num = Integer.parseInt(lastId.substring(1)) + 1;
            return String.format("S%03d", num);
        }
        return "S001";
    }

    @Override
    public Supplier searchSupplierByPhone(String phone) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT sup_Id, name, Email FROM supplier WHERE contact = ?", phone);
        if (rs.next()) {
            return new Supplier(
                    rs.getString("sup_Id"),
                    rs.getString("name"),
                    rs.getString("Email")
            );
        }
        return null;
    }

    @Override
    public Supplier getSupplierEmail(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT Email FROM supplier WHERE sup_Id = ?", id);
        if (rs.next()) {
            return new Supplier(
                    rs.getString("Email")
            );
        }
        return null;
    }
}
