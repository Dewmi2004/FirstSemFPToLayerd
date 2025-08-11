package org.example.firstsemfptolayerd.BO.custom.impl;

import org.example.firstsemfptolayerd.BO.custom.PlantBO;
import org.example.firstsemfptolayerd.Dao.DAOFactory;
import org.example.firstsemfptolayerd.Dao.custom.PlantDao;
import org.example.firstsemfptolayerd.entity.Plant;
import org.example.firstsemfptolayerd.model.PlantDTO;

import java.util.ArrayList;

public class PlantBOImpl implements PlantBO {
    private final PlantDao plantDAO = (PlantDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.PLANT);

    @Override
    public boolean savePlant(PlantDTO dto) throws Exception {
        return plantDAO.Save(new Plant(
                dto.getPlantId(),
                dto.getName(),
                dto.getWaterType(),
                dto.getTankId(),
                dto.getSize(),
                dto.getQuantity()
        ));
    }

    @Override
    public boolean updatePlant(PlantDTO dto) throws Exception {
        return plantDAO.update(new Plant(
            dto.getPlantId(),
            dto.getName(),
            dto.getWaterType(),
            dto.getTankId(),
            dto.getSize(),
            dto.getQuantity()
    ));
    }
    @Override
    public boolean deletePlant(String id) throws Exception {
        return plantDAO.Delete(id);

    }

    @Override
    public ArrayList<PlantDTO> getAllPlants() throws Exception {
        ArrayList<Plant> entityList = plantDAO.getAll();
        ArrayList<PlantDTO> dtoList = new ArrayList<>();
        for (Plant plant : entityList) {
            dtoList.add(new PlantDTO(
                    plant.getPlantId(),
                    plant.getName(),
                    plant.getWaterType(),
                    plant.getTankId(),
                    plant.getSize(),
                    plant.getQuantity()
            ));
        }
        return dtoList;    }

    @Override
    public String getNextPlantId() throws Exception {
        return plantDAO.getNextId();
    }
}
