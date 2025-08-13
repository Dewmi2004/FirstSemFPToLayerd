package org.example.firstsemfptolayerd.Dao.custom.impl;

import org.example.firstsemfptolayerd.Dao.SQLUtil;
import org.example.firstsemfptolayerd.Dao.custom.InventoryDao;
import org.example.firstsemfptolayerd.entity.Inventory;
import org.example.firstsemfptolayerd.model.ChemicalDTO;
import org.example.firstsemfptolayerd.model.FishDTO;
import org.example.firstsemfptolayerd.model.FoodDTO;
import org.example.firstsemfptolayerd.model.PlantDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryDaoImpl implements InventoryDao {


    @Override
    public String generateNextInventoryId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT inventory_Id FROM inventory ORDER BY CAST(SUBSTRING(inventory_Id, 4) AS UNSIGNED) DESC LIMIT 1");
        if (rs.next()) {
            String lastId = rs.getString(1);
            int nextId = Integer.parseInt(lastId.replace("Inv", "")) + 1;
            return String.format("Inv%03d", nextId);
        } else {
            return "Inv001";
        }
    }


}
