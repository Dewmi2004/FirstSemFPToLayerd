package org.example.firstsemfptolayerd.BO.custom;

import org.example.firstsemfptolayerd.BO.SuperBO;
import org.example.firstsemfptolayerd.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDTO dto) throws Exception;
    boolean updateCustomer(CustomerDTO dto) throws Exception;
    boolean deleteCustomer(String id) throws Exception;
    ArrayList<CustomerDTO> getAllCustomers() throws Exception;
    String getNextCustomerId() throws Exception;
    CustomerDTO searchCustomerByPhone(String phone) throws SQLException, ClassNotFoundException;

}
