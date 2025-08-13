package org.example.firstsemfptolayerd.Dao.custom;

import org.example.firstsemfptolayerd.Dao.CrudDao;
import org.example.firstsemfptolayerd.entity.Plant;

import java.sql.SQLException;
import java.util.List;

public interface PlantDao extends CrudDao<Plant> {
    Plant searchPlantByName(String plantId) throws SQLException, ClassNotFoundException;

    List<String> getAllPlantIds() throws SQLException, ClassNotFoundException;

    boolean updatePlant(Plant plant) throws SQLException, ClassNotFoundException;

    boolean updateplantQntyUp(Integer plantquantity,String itemId) throws SQLException, ClassNotFoundException;
}
