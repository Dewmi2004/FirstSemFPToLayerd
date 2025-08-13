package org.example.firstsemfptolayerd.BO.custom.impl;

import org.example.firstsemfptolayerd.BO.custom.InventoryBO;
import org.example.firstsemfptolayerd.Dao.DAOFactory;
import org.example.firstsemfptolayerd.Dao.custom.InventoryDao;
import org.example.firstsemfptolayerd.db.DBConnection;
import org.example.firstsemfptolayerd.entity.Inventory;
import org.example.firstsemfptolayerd.model.ChemicalDTO;
import org.example.firstsemfptolayerd.model.FishDTO;
import org.example.firstsemfptolayerd.model.FoodDTO;
import org.example.firstsemfptolayerd.model.PlantDTO;
import org.example.firstsemfptolayerd.view.tdm.InventoryTM;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryBOImpl implements InventoryBO {
    private final InventoryDao inventoryDAO = (InventoryDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.INVENTORY);
    @Override
    public String generateNextInventoryId() throws SQLException, ClassNotFoundException {
        return inventoryDAO.generateNextInventoryId();
    }

    @Override
    public boolean placeInventoryOrder(Inventory inventory, ArrayList<Object> cartList, FishDTO fish, PlantDTO plant, ChemicalDTO chemical, FoodDTO food) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getDbConnection().getConnection();
        con.setAutoCommit(false);

        try {
            boolean isInventorySaved = CrudUtil.execute(
                    "INSERT INTO inventory (inventory_Id, sup_Id, date) VALUES (?, ?, ?)",
                    inventory.getInventoryId(), inventory.getSupId(), inventory.getDate()
            );

            if (!isInventorySaved) {
                con.rollback();
                return false;
            }

            for (InventoryTM item : itemList) {
                String itemId = item.getItemId();
                String priceStr = item.getUnitPrice();
                String inventoryId = inventory.getInventoryId();
                String plantquantity = plant.getQuantity();
                String chemicalquantity = chemical.getQuantity();
                String foodquantity = food.getQuantity();
                String fishquantity = fish.getQuantity();
                boolean isSaved;
                boolean isUpdated;

                switch (inventory.getItemType()) {
                    case "Plant":
                        isSaved = CrudUtil.execute(
                                "INSERT INTO plant_detail (plant_Id, quantity, price, inventory_Id) VALUES (?, ?, ?, ?)",
                                itemId, plantquantity, priceStr, inventoryId
                        );
                        if (!isSaved) {
                            con.rollback();
                            return false;
                        }

                        isUpdated = CrudUtil.execute("UPDATE plant SET quantity = quantity + ? WHERE plant_Id=?",plantquantity, itemId);

                        if (!isUpdated) {
                            con.rollback();
                            return false;
                        }
                        updatedQuantities.put(itemId, Integer.valueOf(plantquantity));
                        break;

                    case "Fish":
                        isSaved = CrudUtil.execute(
                                "INSERT INTO fish_detail (fish_Id, quantity, price, inventory_Id) VALUES (?, ?, ?, ?)",
                                itemId, fishquantity, priceStr, inventoryId
                        );
                        if (!isSaved) {
                            con.rollback();
                            return false;
                        }
                        isUpdated = CrudUtil.execute("UPDATE fish SET quantity = quantity + ? WHERE fish_Id=?",fishquantity, itemId);

                        if (!isUpdated) {
                            con.rollback();
                            return false;
                        }
                        updatedQuantities.put(itemId, Integer.valueOf(fishquantity));
                        break;

                    case "Food":
                        System.out.println(itemId+" "+ foodquantity+" "+ priceStr+" "+ inventoryId);
                        isSaved = CrudUtil.execute(
                                "INSERT INTO food_detail (food_Id, quantity, price, inventory_Id) VALUES (?, ?, ?, ?)",
                                itemId, foodquantity, priceStr, inventoryId
                        );

                        if (!isSaved) {
                            con.rollback();
                            return false;
                        }
                        isUpdated = CrudUtil.execute("UPDATE food SET quantity = quantity + ? WHERE food_Id=?",chemicalquantity, itemId);

                        if (!isUpdated) {
                            con.rollback();
                            return false;
                        }
                        updatedQuantities.put(itemId, Integer.valueOf(chemicalquantity));
                        break;

                    case "Chemical":
                        isSaved = CrudUtil.execute(
                                "INSERT INTO chemical_detail (chemical_Id, quantity, price, inventory_Id) VALUES (?, ?, ?, ?)",
                                itemId, chemicalquantity, priceStr, inventoryId
                        );
                        if (!isSaved) {
                            con.rollback();
                            return false;
                        }
                        isUpdated = CrudUtil.execute("UPDATE chemical SET quantity = quantity + ? WHERE chemical_Id=?",foodquantity, itemId);

                        if (!isUpdated) {
                            con.rollback();
                            return false;
                        }
                        updatedQuantities.put(itemId, Integer.valueOf(foodquantity));
                        break;

                    default:
                        con.rollback();
                        return false;
                }
            }

            con.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            return false;
        } finally {
            con.setAutoCommit(true);
        }
    }
}
