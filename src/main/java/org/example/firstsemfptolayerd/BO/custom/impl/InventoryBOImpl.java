package org.example.firstsemfptolayerd.BO.custom.impl;

import org.example.firstsemfptolayerd.BO.custom.InventoryBO;
import org.example.firstsemfptolayerd.Dao.DAOFactory;
import org.example.firstsemfptolayerd.Dao.custom.*;
import org.example.firstsemfptolayerd.db.DBConnection;
import org.example.firstsemfptolayerd.entity.*;
import org.example.firstsemfptolayerd.view.tdm.InventoryTM;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class InventoryBOImpl implements InventoryBO {
    private final InventoryDao inventoryDAO = (InventoryDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.INVENTORY);
    private final PlantDao plantDao = (PlantDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.PLANT);
    private final FishDao fishDao = (FishDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.FISH);
    private final ChemicalDao chemicalDao = (ChemicalDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.CHEMICAL);
    private  final FoodDao foodDao = (FoodDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.FOOD);
    private final QueryDao queryDAO = (QueryDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.QUERY);
    @Override
    public String generateNextInventoryId() throws SQLException, ClassNotFoundException {
        return inventoryDAO.generateNextInventoryId();
    }

    @Override
    public boolean placeInventoryOrder(Inventory inventory, ArrayList<InventoryTM> itemList, Map<String, Integer> updatedQuantities, Fish fish, Plant plant, Chemical chemical, Food food) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getDbConnection().getConnection();
        con.setAutoCommit(false);

        try {
            boolean isInventorySaved = inventoryDAO.saveInventory(inventory);

            if (!isInventorySaved) {
                con.rollback();
                return false;
            }

            for (InventoryTM item : itemList) {
                String itemId = item.getItemId();
                String priceStr = item.getUnitPrice();
                String inventoryId = inventory.getInventoryId();
                String plantquantity = (item.getQuantity());
                String chemicalquantity = item.getQuantity();
                String foodquantity = item.getQuantity();
                String fishquantity = item.getQuantity();
                boolean isSaved;
                boolean isUpdated;

                switch (inventory.getItemType()) {
                    case "Plant":
                        isSaved = queryDAO.savePlantDetail(itemId, Integer.valueOf(plantquantity), priceStr, inventoryId);
                        if (!isSaved) {
                            con.rollback();
                            return false;
                        }

                        isUpdated = plantDao.updateplantQntyUp(Integer.valueOf(plantquantity),itemId);
                        System.out.println(plantquantity);
                        if (!isUpdated) {
                            con.rollback();
                            return false;
                        }
                        updatedQuantities.put(itemId, Integer.valueOf(plantquantity));
                        break;

                    case "Fish":
                        isSaved =queryDAO.saveFishDetails(itemId, Integer.valueOf(fishquantity),priceStr,inventoryId);

                        if (!isSaved) {
                            con.rollback();
                            return false;
                        }
                        isUpdated = fishDao.updateFishQntyUp(Integer.parseInt(fishquantity),itemId);

                        if (!isUpdated) {
                            con.rollback();
                            return false;
                        }
                        updatedQuantities.put(itemId, Integer.valueOf(fishquantity));
                        break;

                    case "Food":
                        isSaved = queryDAO.saveFoodDetails(itemId, Integer.valueOf(foodquantity),priceStr,inventoryId);


                        if (!isSaved) {
                            con.rollback();
                            return false;
                        }
                        isUpdated = foodDao.updateFoodQntyUp(Integer.valueOf(foodquantity),itemId);

                        if (!isUpdated) {
                            con.rollback();
                            return false;
                        }
                        updatedQuantities.put(itemId, Integer.valueOf(chemicalquantity));
                        break;

                    case "Chemical":
                        isSaved = queryDAO.saveChemicalDetails(itemId, Integer.valueOf(chemicalquantity),priceStr,inventoryId);

                        if (!isSaved) {
                            con.rollback();
                            return false;
                        }
                        isUpdated = chemicalDao.updateChemicalQntyUp(Integer.valueOf(chemicalquantity),itemId);

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

