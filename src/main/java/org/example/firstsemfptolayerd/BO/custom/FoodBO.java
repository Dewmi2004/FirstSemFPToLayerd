package org.example.firstsemfptolayerd.BO.custom;

import org.example.firstsemfptolayerd.BO.SuperBO;
import org.example.firstsemfptolayerd.model.FoodDTO;

import java.util.ArrayList;

public interface FoodBO extends SuperBO {
    boolean saveFood(FoodDTO dto) throws Exception;
    boolean updateFood(FoodDTO dto) throws Exception;
    boolean deleteFood(String id) throws Exception;
    ArrayList<FoodDTO> getAllFoods() throws Exception;
    String getNextFoodId() throws Exception;
}
