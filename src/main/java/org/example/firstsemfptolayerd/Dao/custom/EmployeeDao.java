package org.example.firstsemfptolayerd.Dao.custom;

import javafx.collections.ObservableList;
import org.example.firstsemfptolayerd.Dao.CrudDao;
import org.example.firstsemfptolayerd.entity.Employee;

import java.sql.SQLException;

public interface EmployeeDao extends CrudDao<Employee> {
    public  ObservableList<String> getAllEmployeeIds() throws SQLException, ClassNotFoundException;
}
