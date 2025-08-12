package org.example.firstsemfptolayerd.Dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.firstsemfptolayerd.Dao.SQLUtil;
import org.example.firstsemfptolayerd.Dao.custom.EmployeeDao;
import org.example.firstsemfptolayerd.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT * FROM employee");
        ArrayList<Employee> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Employee(
                    rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
        }
        return list;
    }

    @Override
    public boolean Save(Employee e) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO employee VALUES(?,?,?,?,?,?,?)",
                e.getId(), e.getName(), e.getAddress(), e.getDob(), e.getGender(), e.getContact(),
                e.getEmail());
    }

    @Override
    public boolean update(Employee e) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE employee SET employee_Name=?, employee_Address=?, employee_Gender=?, employee_Dob=?, employee_Email=?, employee_Contact=? WHERE employee_Id=?",
                e.getName(), e.getAddress(), e.getDob(), e.getGender(), e.getContact(),
                e.getEmail(), e.getId());
    }


    @Override
    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM employee WHERE employee_Id=?", id);
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT employee_Id FROM employee ORDER BY employee_Id DESC LIMIT 1");
        if (rs.next()) {
            int idNum = Integer.parseInt(rs.getString(1).substring(1)) + 1;
            return String.format("E%03d", idNum);
        }
        return "E001";
    }
    public ObservableList<String> getAllEmployeeIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("select employee_Id from employee");
        ObservableList<String> employeeDtoArrayList = FXCollections.observableArrayList();
        while (rs.next()) {
            employeeDtoArrayList.add(rs.getString("employee_Id"));
        }
        return  employeeDtoArrayList;
    }

}
