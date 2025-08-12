package org.example.firstsemfptolayerd.Dao.custom.impl;

import org.example.firstsemfptolayerd.Dao.SQLUtil;
import org.example.firstsemfptolayerd.Dao.custom.OrderDao;
import org.example.firstsemfptolayerd.db.DBConnection;
import org.example.firstsemfptolayerd.model.FishDTO;
import org.example.firstsemfptolayerd.model.OrderDTO;
import org.example.firstsemfptolayerd.model.PlantDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDaoImpl implements OrderDao {

    @Override
    public String generateNextOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT order_Id FROM orders ORDER BY CAST(SUBSTRING(order_Id, 4) AS UNSIGNED) DESC LIMIT 1");
        if (rs.next()) {
            String lastId = rs.getString(1);
            int nextId = Integer.parseInt(lastId.replace("ORD", "")) + 1;
            return String.format("ORD%03d", nextId);
        } else {
            return "ORD001";
        }
    }

    @Override
    public String generateNextPaymentId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT payment_Id FROM orders ORDER BY CAST(SUBSTRING(payment_Id, 4) AS UNSIGNED) DESC LIMIT 1");
        if (rs.next()) {
            String lastId = rs.getString(1);
            int nextId = Integer.parseInt(lastId.replace("PAY", "")) + 1;
            return String.format("PAY%03d", nextId);
        } else {
            return "PAY001";
        }
    }

    @Override
    public boolean saveOrder(OrderDTO order) throws SQLException, ClassNotFoundException {
       return SQLUtil.executeUpdate(
                "INSERT INTO orders(order_Id, payment_Id,date, customer_Id, item) VALUES (?, ?, ?, ?, ?)",
                order.getOrderId(), order.getPaymentId(), order.getDate(), order.getCustomerId(), order.getItemType()
        );
    }

    @Override
    public boolean SavePayment(OrderDTO order) throws SQLException, ClassNotFoundException {
       return SQLUtil.executeUpdate(
                "INSERT INTO payment(payment_Id,method,date,amount) VALUES (?,?,?,?)",
                order.getPaymentId(),order.getMethod(),order.getDate(),order.getAmount()
        );

    }

    @Override
    public boolean saveFish(OrderDTO order, FishDTO fish) throws SQLException, ClassNotFoundException {
       return SQLUtil.executeUpdate(
                "INSERT INTO order_fish(order_Id, fish_Id) VALUES (?, ?)",
                order.getOrderId(),fish.getFishId()
        );
    }

    @Override
    public boolean updateFish(FishDTO fish) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "UPDATE fish SET quantity = quantity - ? WHERE fish_Id = ?",
                fish.getQuantity(), fish.getFishId()

        );
    }

    @Override
    public boolean savePlant(OrderDTO order, PlantDTO plant) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "INSERT INTO order_plant(order_Id, plant_Id) VALUES (?, ?)",
                order.getOrderId(),plant.getPlantId()
        );
    }

    @Override
    public boolean updatePlant(PlantDTO plant) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "UPDATE plant SET quantity = quantity - ? WHERE plant_Id = ?",
                plant.getQuantity(), plant.getPlantId()
        );
    }
}
