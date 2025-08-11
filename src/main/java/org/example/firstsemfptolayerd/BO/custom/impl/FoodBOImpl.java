package org.example.firstsemfptolayerd.BO.custom.impl;

import org.example.firstsemfptolayerd.BO.custom.FoodBO;
import org.example.firstsemfptolayerd.Dao.DAOFactory;
import org.example.firstsemfptolayerd.Dao.custom.FoodDao;
import org.example.firstsemfptolayerd.entity.Food;
import org.example.firstsemfptolayerd.model.FoodDTO;

import java.util.ArrayList;

public class FoodBOImpl implements FoodBO {
    private final FoodDao foodDao = (FoodDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.FOOD);

    @Override
    public boolean saveFood(FoodDTO dto) throws Exception {
        return foodDao.Save(new Food(
                dto.getFoodId(),
                dto.getName(),
                dto.getFishType(),
                dto.getExDate(),
                dto.getQuantity()
        ));
    }

    @Override
    public boolean updateFood(FoodDTO dto) throws Exception {
        return foodDao.update(new Food(
                dto.getFoodId(),
                dto.getName(),
                dto.getFishType(),
                dto.getExDate(),
                dto.getQuantity()
        ));
    }

    @Override
    public boolean deleteFood(String id) throws Exception {
        return foodDao.Delete(id);
    }


    @Override
    public ArrayList<FoodDTO> getAllFoods() throws Exception {
        ArrayList<Food> foods = foodDao.getAll();
        ArrayList<FoodDTO> dtos = new ArrayList<>();
        for (Food food : foods) {
            dtos.add(new FoodDTO(
                    food.getFoodId(),
                    food.getName(),
                    food.getFishType(),
                    food.getExDate(),
                    food.getQuantity()
            ));
        }
        return dtos;
    }

    @Override
    public String getNextFoodId() throws Exception {
        return foodDao.getNextId();
    }
}
