package org.example.firstsemfptolayerd.BO.custom.impl;

import org.example.firstsemfptolayerd.BO.custom.CustomerBO;
import org.example.firstsemfptolayerd.Dao.DAOFactory;
import org.example.firstsemfptolayerd.Dao.custom.CustomerDao;
import org.example.firstsemfptolayerd.entity.Customer;
import org.example.firstsemfptolayerd.model.CustomerDTO;

import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    private final CustomerDao customerDAO = (CustomerDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO customer) throws Exception {
        return customerDAO.Save(new Customer(  customer.getId(),
                customer.getContact(),
                customer.getEmail(),
                customer.getDob(),
                customer.getGender(),
                customer.getAddress(),
                customer.getName()));

    }

    @Override
    public boolean updateCustomer(CustomerDTO customer) throws Exception {
        return customerDAO.update(new Customer(  customer.getId(),
                customer.getContact(),
                customer.getEmail(),
                customer.getDob(),
                customer.getGender(),
                customer.getAddress(),
                customer.getName()));
    }


    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return customerDAO.Delete(id);
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws Exception {
        ArrayList<Customer>entity =customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOs = new ArrayList<>();
        for(Customer customer : entity){
            customerDTOs.add(new CustomerDTO(  customer.getId(),
                    customer.getContact(),
                    customer.getEmail(),
                    customer.getDob(),
                    customer.getGender(),
                    customer.getAddress(),
                    customer.getName()));
        }
        return customerDTOs;

    }

    @Override
    public String getNextCustomerId() throws Exception {
        return customerDAO.getNextId();

    }
}
