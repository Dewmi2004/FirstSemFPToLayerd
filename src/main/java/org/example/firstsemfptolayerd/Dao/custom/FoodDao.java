package org.example.firstsemfptolayerd.Dao.custom;

import org.example.firstsemfptolayerd.BO.SuperBO;
import org.example.firstsemfptolayerd.Dao.CrudDao;
import org.example.firstsemfptolayerd.entity.Food;

import java.sql.SQLException;
import java.util.List;

public interface FoodDao extends CrudDao<Food> {
    List<String> getAllfoodIds() throws SQLException, ClassNotFoundException;

    boolean updateFoodQntyUp(Integer foodquantity, String itemId) throws SQLException, ClassNotFoundException;
}
