package org.example.firstsemfptolayerd.Dao.custom;

import org.example.firstsemfptolayerd.Dao.CrudDao;
import org.example.firstsemfptolayerd.entity.Fish;

import java.sql.SQLException;
import java.util.List;

public interface FishDao extends CrudDao<Fish> {
    List<String> getAllfishIds() throws SQLException, ClassNotFoundException;

    Fish searchfishByName(String fishId) throws SQLException, ClassNotFoundException;

    boolean updateFish(Fish fish) throws SQLException, ClassNotFoundException;

    boolean updateFishQntyUp(int fishquantity, String itemId) throws SQLException, ClassNotFoundException;
}
