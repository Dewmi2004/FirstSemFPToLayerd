package org.example.firstsemfptolayerd.Dao.custom;

import org.example.firstsemfptolayerd.Dao.SuperDao;
import org.example.firstsemfptolayerd.entity.Fish;
import org.example.firstsemfptolayerd.entity.Order;
import org.example.firstsemfptolayerd.entity.Plant;

import java.sql.SQLException;

public interface OrderDao extends SuperDao {
    String generateNextOrderId() throws SQLException, ClassNotFoundException;

    String generateNextPaymentId() throws SQLException, ClassNotFoundException;

    boolean saveOrder(Order order) throws SQLException, ClassNotFoundException;

    boolean SavePayment(Order order) throws SQLException, ClassNotFoundException;

    boolean saveFish(Order order, Fish fish) throws SQLException, ClassNotFoundException;

    boolean updateFish(Fish fish) throws SQLException, ClassNotFoundException;

    boolean savePlant(Order order, Plant plant) throws SQLException, ClassNotFoundException;

    boolean updatePlant(Plant plant) throws SQLException, ClassNotFoundException;
}
