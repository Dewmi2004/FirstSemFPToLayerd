package org.example.firstsemfptolayerd.BO.custom;

import org.example.firstsemfptolayerd.BO.SuperBO;
import org.example.firstsemfptolayerd.model.*;

import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    String generateNextOrderId() throws Exception;
    String generateNextPaymentId() throws Exception;
    boolean placeOrder(OrderDTO order, FishDTO fish, PlantDTO plant, ArrayList<CartDTO> cartList, double total, String customerEmail) throws Exception;
}
