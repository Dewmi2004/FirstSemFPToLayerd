package org.example.firstsemfptolayerd.BO.custom.impl;

import org.example.firstsemfptolayerd.BO.custom.OrderBO;
import org.example.firstsemfptolayerd.Dao.DAOFactory;
import org.example.firstsemfptolayerd.Dao.SQLUtil;
import org.example.firstsemfptolayerd.Dao.custom.OrderDao;
import org.example.firstsemfptolayerd.db.DBConnection;
import org.example.firstsemfptolayerd.model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    private final OrderDao orderDAO = (OrderDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.ORDER);

    @Override
    public String generateNextOrderId() throws Exception {
        return orderDAO.generateNextOrderId();

    }

    @Override
    public String generateNextPaymentId() throws Exception {
        return orderDAO.generateNextPaymentId();
    }

    @Override
    public boolean placeOrder(OrderDTO order, FishDTO fish, PlantDTO plant, ArrayList<CartDTO> cartDTOList, double total, String text) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getDbConnection().getConnection();
        con.setAutoCommit(false);
        try {
            boolean isPaymentSaved = orderDAO.SavePayment(order);
            if (isPaymentSaved) {
                boolean isOrderSaved = orderDAO.saveOrder(order);
                if (isOrderSaved) {
                    boolean isItemOrderSaved = false;
                    boolean isQuantityUpdated = false;

                    if (order.getItemType().equals("Fish Order")) {
                        isItemOrderSaved =orderDAO.saveFish(order,fish);

                        isQuantityUpdated = orderDAO.updateFish(fish);

                    } else if (order.getItemType().equals("Plant Order")) {
                        isItemOrderSaved = orderDAO.savePlant(order,plant);

                        isQuantityUpdated = orderDAO.updatePlant(plant);
                    }
                    con.commit();
                    return true;
                }else {
                    con.rollback();
                    return false;
                }
            }else{
                con.rollback();
                return false;
            }
        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);

        }

    }
}
