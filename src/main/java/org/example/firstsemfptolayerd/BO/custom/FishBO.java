package org.example.firstsemfptolayerd.BO.custom;

import javafx.collections.ObservableList;
import org.example.firstsemfptolayerd.BO.SuperBO;
import org.example.firstsemfptolayerd.model.FishDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FishBO extends SuperBO {
    boolean saveFish(FishDTO dto) throws Exception;
    boolean updateFish(FishDTO dto) throws Exception;
    boolean deleteFish(String id) throws Exception;
    ArrayList<FishDTO> getAllFish() throws Exception;
    String getNextFishId() throws Exception;

    ObservableList<String> getAllFishIDS() throws SQLException, ClassNotFoundException;

    FishDTO searchFishByName(String fishId);
}
