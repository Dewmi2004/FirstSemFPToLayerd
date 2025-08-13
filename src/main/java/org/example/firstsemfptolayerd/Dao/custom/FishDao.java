package org.example.firstsemfptolayerd.Dao.custom;

import org.example.firstsemfptolayerd.Dao.CrudDao;
import org.example.firstsemfptolayerd.entity.Fish;
import org.example.firstsemfptolayerd.model.FishDTO;

import java.sql.SQLException;
import java.util.List;

public interface FishDao extends CrudDao<Fish> {
    List<String> getAllfishIds() throws SQLException, ClassNotFoundException;

    FishDTO searchfishByName(String fishId) throws SQLException, ClassNotFoundException;

    boolean updateFish(Fish fish) throws SQLException, ClassNotFoundException;

    boolean updateFishQntyUp(String fishquantity, String itemId) throws SQLException, ClassNotFoundException;
}
