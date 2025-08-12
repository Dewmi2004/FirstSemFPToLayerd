package org.example.firstsemfptolayerd.Dao.custom;

import org.example.firstsemfptolayerd.Dao.CrudDao;
import org.example.firstsemfptolayerd.entity.Plant;
import org.example.firstsemfptolayerd.model.CartDTO;
import org.example.firstsemfptolayerd.model.PlantDTO;

import java.sql.SQLException;
import java.util.List;

public interface PlantDao extends CrudDao<Plant> {
    PlantDTO searchPlantByName(String plantId) throws SQLException, ClassNotFoundException;

    List<String> getAllPlantIds() throws SQLException, ClassNotFoundException;

}
