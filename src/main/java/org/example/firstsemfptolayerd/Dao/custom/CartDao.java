package org.example.firstsemfptolayerd.Dao.custom;

import org.example.firstsemfptolayerd.Dao.CrudDao;
import org.example.firstsemfptolayerd.Dao.SuperDao;
import org.example.firstsemfptolayerd.entity.Cart;
import org.example.firstsemfptolayerd.model.CartDTO;

import java.sql.SQLException;

public interface CartDao extends SuperDao {
    CartDTO searchPlantUnitPrice(String plantId) throws SQLException, ClassNotFoundException;

    CartDTO searchFishUnitPrice(String fishId) throws SQLException, ClassNotFoundException;
}
