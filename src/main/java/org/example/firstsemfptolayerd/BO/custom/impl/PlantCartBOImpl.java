package org.example.firstsemfptolayerd.BO.custom.impl;

import org.example.firstsemfptolayerd.BO.custom.PlantCartBO;
import org.example.firstsemfptolayerd.Dao.DAOFactory;
import org.example.firstsemfptolayerd.Dao.custom.CartDao;
import org.example.firstsemfptolayerd.model.CartDTO;

import java.sql.SQLException;

public class PlantCartBOImpl implements PlantCartBO {
//    private final PlantDao plantDao  = (PlantDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.PLANT);
    private final CartDao cartDao = (CartDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.CART);
    @Override
    public CartDTO searchPlantUnitprice(String plantId) throws SQLException, ClassNotFoundException {
        CartDTO plant = cartDao.searchPlantUnitPrice(plantId);
        if (plant != null) {
            return new CartDTO(
                    plant.getUnitPrice()
            );
        }
        return null;
    }
}
