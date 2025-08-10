package org.example.firstsemfptolayerd.BO.custom;

import javafx.collections.ObservableList;
import org.example.firstsemfptolayerd.BO.SuperBO;
import org.example.firstsemfptolayerd.model.EmployeeDTO;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    boolean saveEmployee(EmployeeDTO dto) throws Exception;
    boolean updateEmployee(EmployeeDTO dto) throws Exception;
    boolean deleteEmployee(String id) throws Exception;
    List<EmployeeDTO> getAllEmployees() throws Exception;
    String getNextEmployeeId() throws Exception;
    ObservableList<String> getAllEmployeeIds() throws SQLException, ClassNotFoundException;

}
