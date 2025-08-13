package org.example.firstsemfptolayerd.Dao.custom;

import org.example.firstsemfptolayerd.Dao.SuperDao;
import org.example.firstsemfptolayerd.entity.Inventory;
import org.example.firstsemfptolayerd.entity.Plant;

import java.sql.SQLException;

public interface QueryDao extends SuperDao {
    boolean savePlantDetail(String plantId, Integer quantity, String priceStr, String inventoryId) throws SQLException, ClassNotFoundException;

    boolean saveFishDetails(String itemId, Integer fishquantity, String priceStr, String inventoryId) throws SQLException, ClassNotFoundException;

    boolean saveFoodDetails(String itemId, Integer foodquantity, String priceStr, String inventoryId) throws SQLException, ClassNotFoundException;


    boolean saveChemicalDetails(String itemId, Integer chemicalquantity, String priceStr, String inventoryId) throws SQLException, ClassNotFoundException;
}
