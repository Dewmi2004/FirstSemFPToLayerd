package org.example.firstsemfptolayerd.Dao.custom.impl;

import org.example.firstsemfptolayerd.Dao.SQLUtil;
import org.example.firstsemfptolayerd.Dao.custom.FoodDao;
import org.example.firstsemfptolayerd.entity.Food;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FoodDaoImpl implements FoodDao {
    @Override
    public ArrayList<Food> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT * FROM food");
        ArrayList<Food> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new Food(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDate(4),
                    rs.getString(5)
            ));
        }
        return list;
    }

    @Override
    public boolean Save(Food food) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO food VALUES (?, ?, ?, ?, ?)",
                food.getFoodId(),
                food.getName(),
                food.getFishType(),
                food.getExDate(),
                food.getQuantity()
        );
    }

    @Override
    public boolean update(Food food) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE food SET name = ?, fish_Type = ?, ex_Date = ?, quantity = ? WHERE food_Id = ?",
                food.getName(),
                food.getFishType(),
                food.getExDate(),
                food.getQuantity(),
                food.getFoodId()
        );
    }

    @Override
    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM food WHERE food_Id = ?", id);
    }
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT food_Id FROM food ORDER BY food_Id DESC LIMIT 1");
        char prefix = 'F';
        if (rs.next()) {
            String lastId = rs.getString(1);
            int nextNum = Integer.parseInt(lastId.substring(1)) + 1;
            return String.format("%c%03d", prefix, nextNum);
        }
        return prefix + "001";
    }
}
