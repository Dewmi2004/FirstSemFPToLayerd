package org.example.firstsemfptolayerd.BO.custom.impl;

import org.example.firstsemfptolayerd.BO.custom.OrderBO;
import org.example.firstsemfptolayerd.Dao.DAOFactory;
import org.example.firstsemfptolayerd.Dao.custom.CartDao;
import org.example.firstsemfptolayerd.Dao.custom.FishDao;
import org.example.firstsemfptolayerd.Dao.custom.OrderDao;
import org.example.firstsemfptolayerd.Dao.custom.PlantDao;
import org.example.firstsemfptolayerd.db.DBConnection;
import org.example.firstsemfptolayerd.entity.Fish;
import org.example.firstsemfptolayerd.entity.Order;
import org.example.firstsemfptolayerd.entity.Plant;
import org.example.firstsemfptolayerd.model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    private final OrderDao orderDAO = (OrderDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.ORDER);
    private final FishDao fishDAO = (FishDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.FISH);
    private final PlantDao plantDao = (PlantDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.PLANT);
    private final CartDao  cartDAO = (CartDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.CART);

    @Override
    public String generateNextOrderId() throws Exception {
        return orderDAO.generateNextOrderId();

    }

    @Override
    public String generateNextPaymentId() throws Exception {
        return orderDAO.generateNextPaymentId();
    }

    @Override
    public boolean placeOrder(Order order, Fish fish, Plant plant, ArrayList<CartDTO> cartDTOList, double total, String text) throws SQLException, ClassNotFoundException {
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
                        isItemOrderSaved =cartDAO.saveFish(order,fish);

                        isQuantityUpdated = fishDAO.updateFish(fish);

                    } else if (order.getItemType().equals("Plant Order")) {
                        isItemOrderSaved = cartDAO.savePlant(order,plant);

                        isQuantityUpdated = plantDao.updatePlant(plant);
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
