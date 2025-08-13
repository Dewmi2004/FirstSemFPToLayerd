package org.example.firstsemfptolayerd.Dao.custom;

import org.example.firstsemfptolayerd.Dao.CrudDao;
import org.example.firstsemfptolayerd.entity.Customer;
import org.example.firstsemfptolayerd.entity.Supplier;

import java.sql.SQLException;

public interface SupplierDao extends CrudDao<Supplier> {
    Supplier searchSupplierByPhone(String phone) throws SQLException, ClassNotFoundException;

    Supplier getSupplierEmail(String email) throws SQLException, ClassNotFoundException;
}
