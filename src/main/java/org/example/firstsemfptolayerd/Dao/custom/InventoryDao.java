package org.example.firstsemfptolayerd.Dao.custom;

import org.example.firstsemfptolayerd.Dao.CrudDao;
import org.example.firstsemfptolayerd.Dao.SuperDao;
import org.example.firstsemfptolayerd.entity.Inventory;
import org.example.firstsemfptolayerd.model.ChemicalDTO;
import org.example.firstsemfptolayerd.model.FishDTO;
import org.example.firstsemfptolayerd.model.FoodDTO;
import org.example.firstsemfptolayerd.model.PlantDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InventoryDao extends SuperDao {
    String generateNextInventoryId() throws SQLException, ClassNotFoundException;
}
