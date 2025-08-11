package org.example.firstsemfptolayerd.BO.custom;

import org.example.firstsemfptolayerd.BO.SuperBO;
import org.example.firstsemfptolayerd.model.PlantDTO;

import java.util.ArrayList;

public interface PlantBO extends SuperBO {
    boolean savePlant(PlantDTO plant) throws Exception;
    boolean updatePlant(PlantDTO plant) throws Exception;
    boolean deletePlant(String id) throws Exception;
    ArrayList<PlantDTO> getAllPlants() throws Exception;
    String getNextPlantId() throws Exception;
}
