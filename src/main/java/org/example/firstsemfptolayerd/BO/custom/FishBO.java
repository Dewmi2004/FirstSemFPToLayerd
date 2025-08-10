package org.example.firstsemfptolayerd.BO.custom;

import org.example.firstsemfptolayerd.BO.SuperBO;
import org.example.firstsemfptolayerd.model.FishDTO;

import java.util.ArrayList;

public interface FishBO extends SuperBO {
    boolean saveFish(FishDTO dto) throws Exception;
    boolean updateFish(FishDTO dto) throws Exception;
    boolean deleteFish(String id) throws Exception;
    ArrayList<FishDTO> getAllFish() throws Exception;
    String getNextFishId() throws Exception;

}
