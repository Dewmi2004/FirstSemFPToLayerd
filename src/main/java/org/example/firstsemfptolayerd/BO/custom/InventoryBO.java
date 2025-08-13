package org.example.firstsemfptolayerd.BO.custom;

import org.example.firstsemfptolayerd.BO.SuperBO;
import org.example.firstsemfptolayerd.entity.Inventory;
import org.example.firstsemfptolayerd.model.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InventoryBO extends SuperBO {
    String generateNextInventoryId() throws SQLException, ClassNotFoundException;
    boolean placeInventoryOrder(Inventory inventory, ArrayList<Object> objects, FishDTO fish, PlantDTO plant, ChemicalDTO chemical, FoodDTO food) throws SQLException, ClassNotFoundException;
}
