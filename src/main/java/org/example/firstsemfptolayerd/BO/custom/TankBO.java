package org.example.firstsemfptolayerd.BO.custom;

import javafx.collections.ObservableList;
import org.example.firstsemfptolayerd.BO.SuperBO;
import org.example.firstsemfptolayerd.model.TankDTO;

import java.util.ArrayList;

public interface TankBO extends SuperBO {
    boolean saveTank(TankDTO dto) throws Exception;
    boolean updateTank(TankDTO dto) throws Exception;
    boolean deleteTank(String id) throws Exception;
    ArrayList<TankDTO> getAllTanks() throws Exception;
    String getNextTankId() throws Exception;

    ObservableList<String> getTankIds() throws Exception;
    ObservableList<String> getGlassTypes();
    ObservableList<String> getTankTypes();
    ObservableList<String> getWaterTypes();
}
