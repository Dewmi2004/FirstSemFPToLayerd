package org.example.firstsemfptolayerd.BO.custom;

import org.example.firstsemfptolayerd.BO.SuperBO;
import org.example.firstsemfptolayerd.model.SupplierDTO;

import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    boolean saveSupplier(SupplierDTO supplier) throws Exception;
    boolean updateSupplier(SupplierDTO supplier) throws Exception;
    boolean deleteSupplier(String id) throws Exception;
    ArrayList<SupplierDTO> getAllSuppliers() throws Exception;
    String getNextSupplierId() throws Exception;
}
