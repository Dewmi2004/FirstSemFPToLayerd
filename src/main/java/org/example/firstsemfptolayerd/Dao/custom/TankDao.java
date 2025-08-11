package org.example.firstsemfptolayerd.Dao.custom;

import javafx.collections.ObservableList;
import org.example.firstsemfptolayerd.Dao.CrudDao;
import org.example.firstsemfptolayerd.entity.Tank;

public interface TankDao extends CrudDao<Tank> {
    ObservableList<String> getTankIds() throws Exception;
    ObservableList<String> getGlassTypes();
    ObservableList<String> getTankTypes();
    ObservableList<String> getWaterTypes();
}
