package org.example.firstsemfptolayerd.BO.custom;

import org.example.firstsemfptolayerd.BO.SuperBO;
import org.example.firstsemfptolayerd.entity.*;
import org.example.firstsemfptolayerd.view.tdm.InventoryTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface InventoryBO extends SuperBO {
    String generateNextInventoryId() throws SQLException, ClassNotFoundException;
    boolean placeInventoryOrder(Inventory inventory, ArrayList<InventoryTM> itemlist, Map<String, Integer> updatedQuantities, Fish fish, Plant plant, Chemical chemical, Food food) throws SQLException, ClassNotFoundException;
}
