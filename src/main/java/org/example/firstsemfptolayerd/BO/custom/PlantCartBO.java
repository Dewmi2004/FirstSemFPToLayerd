package org.example.firstsemfptolayerd.BO.custom;

import org.example.firstsemfptolayerd.BO.SuperBO;
import org.example.firstsemfptolayerd.model.CartDTO;

import java.sql.SQLException;

public interface PlantCartBO extends SuperBO {
    CartDTO searchPlantUnitprice(String plantId) throws SQLException, ClassNotFoundException;
}
