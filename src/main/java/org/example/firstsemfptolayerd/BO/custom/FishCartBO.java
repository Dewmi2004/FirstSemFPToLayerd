package org.example.firstsemfptolayerd.BO.custom;

import org.example.firstsemfptolayerd.BO.SuperBO;
import org.example.firstsemfptolayerd.model.CartDTO;

import java.sql.SQLException;

public interface FishCartBO extends SuperBO {
    CartDTO searchFishUnitPrice(String fishId) throws SQLException, ClassNotFoundException;
}
