package org.example.firstsemfptolayerd.Dao.custom.impl;

import org.example.firstsemfptolayerd.Dao.SQLUtil;
import org.example.firstsemfptolayerd.Dao.custom.CartDao;
import org.example.firstsemfptolayerd.entity.Cart;
import org.example.firstsemfptolayerd.model.CartDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartDaoImpl implements CartDao {
//    @Override
//    public ArrayList<Cart> getAll() throws SQLException, ClassNotFoundException {
//        return null;
//    }
//
//    @Override
//    public boolean Save(Cart dto) throws SQLException, ClassNotFoundException {
//        return false;
//    }
//
//    @Override
//    public boolean update(Cart dto) throws SQLException, ClassNotFoundException {
//        return false;
//    }
//
//    @Override
//    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
//        return false;
//    }
//
//    @Override
//    public String getNextId() throws SQLException, ClassNotFoundException {
//        return "";
//    }

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
