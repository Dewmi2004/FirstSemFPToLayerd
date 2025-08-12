package org.example.firstsemfptolayerd.BO.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.firstsemfptolayerd.BO.custom.PlantBO;
import org.example.firstsemfptolayerd.Dao.DAOFactory;
import org.example.firstsemfptolayerd.Dao.custom.PlantDao;
import org.example.firstsemfptolayerd.entity.Customer;
import org.example.firstsemfptolayerd.entity.Plant;
import org.example.firstsemfptolayerd.model.CustomerDTO;
import org.example.firstsemfptolayerd.model.PlantDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public PlantDTO searchPlantByName(String plantId) throws SQLException, ClassNotFoundException {
        PlantDTO plant = plantDAO.searchPlantByName(plantId);
        if (plant != null) {
            return new PlantDTO(
                    plant.getName()
            );
        }
        return null;
    }

    @Override
    public ObservableList<String> getAllPlantIDS() throws SQLException, ClassNotFoundException {
        List<String> ids = plantDAO.getAllPlantIds();
        return FXCollections.observableArrayList(ids);
    }
}
