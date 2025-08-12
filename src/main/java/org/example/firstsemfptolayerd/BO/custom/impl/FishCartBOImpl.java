package org.example.firstsemfptolayerd.BO.custom.impl;

import org.example.firstsemfptolayerd.BO.custom.FishCartBO;
import org.example.firstsemfptolayerd.Dao.DAOFactory;
import org.example.firstsemfptolayerd.Dao.custom.CartDao;
import org.example.firstsemfptolayerd.model.CartDTO;

import java.sql.SQLException;

public class FishCartBOImpl implements FishCartBO {
    private final CartDao cartDao = (CartDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.CART);

    @Override
    public CartDTO searchFishUnitPrice(String fishId) throws SQLException, ClassNotFoundException {
        CartDTO fish = cartDao.searchFishUnitPrice(fishId);
        if (fish != null) {
            return new CartDTO(
                    fish.getUnitPrice()
            );
        }
        return null;
    }
}
