package org.example.firstsemfptolayerd.BO.custom;

import org.example.firstsemfptolayerd.BO.SuperBO;
import org.example.firstsemfptolayerd.entity.Fish;
import org.example.firstsemfptolayerd.entity.Order;
import org.example.firstsemfptolayerd.entity.Plant;
import org.example.firstsemfptolayerd.model.*;

import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    String generateNextOrderId() throws Exception;
    String generateNextPaymentId() throws Exception;
    boolean placeOrder(Order order, Fish fish, Plant plant, ArrayList<CartDTO> cartList, double total, String customerEmail) throws Exception;
}
