package org.example.firstsemfptolayerd.Dao.custom;

import org.example.firstsemfptolayerd.Dao.SuperDao;
import org.example.firstsemfptolayerd.entity.Inventory;
import org.example.firstsemfptolayerd.entity.Plant;

import java.sql.SQLException;

public interface QueryDao extends SuperDao {
    boolean savePlantDetail(String plantId, Integer quantity, String priceStr, String inventoryId) throws SQLException, ClassNotFoundException;

    boolean saveFishDetails(String itemId, String fishquantity, String priceStr, String inventoryId) throws SQLException, ClassNotFoundException;

    boolean saveFoodDetails(String itemId, String foodquantity, String priceStr, String inventoryId) throws SQLException, ClassNotFoundException;


    boolean saveChemicalDetails(String itemId, String chemicalquantity, String priceStr, String inventoryId) throws SQLException, ClassNotFoundException;
}
