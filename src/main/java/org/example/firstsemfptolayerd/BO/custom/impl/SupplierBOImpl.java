package org.example.firstsemfptolayerd.BO.custom.impl;

import org.example.firstsemfptolayerd.BO.custom.SupplierBO;
import org.example.firstsemfptolayerd.Dao.DAOFactory;
import org.example.firstsemfptolayerd.Dao.custom.SupplierDao;
import org.example.firstsemfptolayerd.entity.Supplier;
import org.example.firstsemfptolayerd.model.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    private final SupplierDao supplierDAO = (SupplierDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.SUPPLIER);

    @Override
    public boolean saveSupplier(SupplierDTO supplier) throws Exception {
        return supplierDAO.Save(new Supplier(
                supplier.getSupId(),
                supplier.getName(),
                supplier.getContact(),
                supplier.getCompanyAddress(),
                supplier.getSupplyType(),
                supplier.getEmail()
        ));
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplier) throws Exception {
        return supplierDAO.update(new Supplier(
                supplier.getSupId(),
                supplier.getName(),
                supplier.getContact(),
                supplier.getCompanyAddress(),
                supplier.getSupplyType(),
                supplier.getEmail()
        ));    }

    @Override
    public boolean deleteSupplier(String id) throws Exception {
        return supplierDAO.Delete(id);

    }

    @Override
    public ArrayList<SupplierDTO> getAllSuppliers() throws Exception {
        ArrayList<Supplier> entities = supplierDAO.getAll();
        ArrayList<SupplierDTO> dtoList = new ArrayList<>();
        for (Supplier s : entities) {
            dtoList.add(new SupplierDTO(
                    s.getSupId(),
                    s.getName(),
                    s.getContact(),
                    s.getCompanyAddress(),
                    s.getSupplyType(),
                    s.getEmail()
            ));
        }
        return dtoList;
    }

    @Override
    public String getNextSupplierId() throws Exception {
        return supplierDAO.getNextId();

    }

    @Override
    public Supplier searchSupplierByPhone(String phone) throws SQLException, ClassNotFoundException {

        Supplier supplier = supplierDAO.searchSupplierByPhone(phone);
        if (supplier != null) {
            return new Supplier(
                    supplier.getSupId(),
                    supplier.getName(),
                    supplier.getEmail()
            );
        }
        return null;
    }

    @Override
    public Supplier getSupplierEmailById(String supId) throws SQLException, ClassNotFoundException {
        return supplierDAO.getSupplierEmail(supId);

    }


}
