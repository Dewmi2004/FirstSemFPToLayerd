package org.example.firstsemfptolayerd.Dao.custom;

import org.example.firstsemfptolayerd.Dao.CrudDao;
import org.example.firstsemfptolayerd.Dao.SuperDao;
import org.example.firstsemfptolayerd.entity.Order;
import org.example.firstsemfptolayerd.model.FishDTO;
import org.example.firstsemfptolayerd.model.OrderDTO;
import org.example.firstsemfptolayerd.model.PlantDTO;

import java.sql.SQLException;

public interface OrderDao extends SuperDao {
    String generateNextOrderId() throws SQLException, ClassNotFoundException;

    String generateNextPaymentId() throws SQLException, ClassNotFoundException;

    boolean saveOrder(OrderDTO order) throws SQLException, ClassNotFoundException;

    boolean SavePayment(OrderDTO order) throws SQLException, ClassNotFoundException;

    boolean saveFish(OrderDTO order, FishDTO fish) throws SQLException, ClassNotFoundException;

    boolean updateFish(FishDTO fish) throws SQLException, ClassNotFoundException;

    boolean savePlant(OrderDTO order, PlantDTO plant) throws SQLException, ClassNotFoundException;

    boolean updatePlant(PlantDTO plant) throws SQLException, ClassNotFoundException;
}
