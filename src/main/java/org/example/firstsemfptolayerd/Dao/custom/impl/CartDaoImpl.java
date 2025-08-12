package org.example.firstsemfptolayerd.Dao.custom.impl;

import org.example.firstsemfptolayerd.Dao.SQLUtil;
import org.example.firstsemfptolayerd.Dao.custom.CartDao;
import org.example.firstsemfptolayerd.model.CartDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDaoImpl implements CartDao {

    @Override
    public CartDTO searchPlantUnitPrice(String plantId) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT price FROM plant_detail WHERE plant_Id = ?", plantId);
        if (rs.next()) {
            return new CartDTO(
                    rs.getString("price")
            );
        }
        return null;
    }

    @Override
    public CartDTO searchFishUnitPrice(String fishId) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT price FROM fish_detail WHERE fish_Id = ?", fishId);
        if (rs.next()) {
            return new CartDTO(
                    rs.getString("price")
            );
        }
        return null;
    }
}
