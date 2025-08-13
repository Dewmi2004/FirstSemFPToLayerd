package org.example.firstsemfptolayerd.Dao.custom;

import org.example.firstsemfptolayerd.Dao.CrudDao;
import org.example.firstsemfptolayerd.Dao.SuperDao;
import org.example.firstsemfptolayerd.entity.Cart;
import org.example.firstsemfptolayerd.entity.Fish;
import org.example.firstsemfptolayerd.entity.Order;
import org.example.firstsemfptolayerd.entity.Plant;
import org.example.firstsemfptolayerd.model.CartDTO;

import java.sql.SQLException;

public interface CartDao extends SuperDao {
    CartDTO searchPlantUnitPrice(String plantId) throws SQLException, ClassNotFoundException;

    CartDTO searchFishUnitPrice(String fishId) throws SQLException, ClassNotFoundException;

    boolean saveFish(Order order, Fish fish) throws SQLException, ClassNotFoundException;

    boolean savePlant(Order order, Plant plant) throws SQLException, ClassNotFoundException;
}
