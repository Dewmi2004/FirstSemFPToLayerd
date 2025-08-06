package org.example.firstsemfptolayerd.BO.custom.impl;

import org.example.firstsemfptolayerd.BO.custom.EmployeeBO;
import org.example.firstsemfptolayerd.Dao.DAOFactory;
import org.example.firstsemfptolayerd.Dao.custom.EmployeeDao;
import org.example.firstsemfptolayerd.entity.Employee;
import org.example.firstsemfptolayerd.model.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
    private final EmployeeDao employeeDAO = (EmployeeDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.EMPLOYEE);

    @Override
    public boolean saveEmployee(EmployeeDTO employee) throws Exception {
        return employeeDAO.Save(new Employee(
                employee.getId(),
                employee.getName(),
                employee.getAddress(),
                employee.getGender(),
                employee.getDob(),
                employee.getEmail(),
                employee.getContact()
        ));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employee) throws Exception {
        return employeeDAO.update(new Employee(
                employee.getId(),
                employee.getName(),
                employee.getAddress(),
                employee.getGender(),
                employee.getDob(),
                employee.getEmail(),
                employee.getContact()
        ));
    }

    @Override
    public boolean deleteEmployee(String id) throws Exception {
        return employeeDAO.Delete(id);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() throws Exception {
        ArrayList<Employee> entities = employeeDAO.getAll();
        ArrayList<EmployeeDTO> dtos = new ArrayList<>();
        for (Employee e : entities) {
            dtos.add(new EmployeeDTO(
                    e.getId(),
                    e.getName(),
                    e.getAddress(),
                    e.getGender(),
                    e.getDob(),
                    e.getEmail(),
                    e.getContact()
            ));
        }
        return dtos;
    }

    @Override
    public String getNextEmployeeId() throws Exception {
        return employeeDAO.getNextId();
    }
}
