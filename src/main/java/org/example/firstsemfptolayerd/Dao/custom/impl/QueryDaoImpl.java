package org.example.firstsemfptolayerd.Dao.custom.impl;

import org.example.firstsemfptolayerd.Dao.SQLUtil;
import org.example.firstsemfptolayerd.Dao.custom.QueryDao;
import org.example.firstsemfptolayerd.entity.Inventory;
import org.example.firstsemfptolayerd.entity.Plant;

import java.sql.SQLException;

public class QueryDaoImpl implements QueryDao {
    @Override
    public boolean savePlantDetail(String plantId, Integer quantity, String priceStr, String inventoryId) throws SQLException, ClassNotFoundException {
       return SQLUtil.executeUpdate(
                "INSERT INTO plant_detail (plant_Id, quantity, price, inventory_Id) VALUES (?, ?, ?, ?)",
                plantId,quantity,priceStr,inventoryId
        );
    }

    @Override
    public boolean saveFishDetails(String itemId, String fishquantity, String priceStr, String inventoryId) throws SQLException, ClassNotFoundException {
        return    SQLUtil.executeUpdate(
                "INSERT INTO fish_detail (fish_Id, quantity, price, inventory_Id) VALUES (?, ?, ?, ?)",
                itemId, fishquantity, priceStr, inventoryId
        );
    }

    @Override
    public boolean saveFoodDetails(String itemId, String foodquantity, String priceStr, String inventoryId) throws SQLException, ClassNotFoundException {
        return   SQLUtil.executeUpdate(
                "INSERT INTO food_detail (food_Id, quantity, price, inventory_Id) VALUES (?, ?, ?, ?)",
                itemId, foodquantity, priceStr, inventoryId
        );
    }

    @Override
    public boolean saveChemicalDetails(String itemId, String chemicalquantity, String priceStr, String inventoryId) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "INSERT INTO chemical_detail (chemical_Id, quantity, price, inventory_Id) VALUES (?, ?, ?, ?)",
                itemId, chemicalquantity, priceStr, inventoryId
        );
    }
}
