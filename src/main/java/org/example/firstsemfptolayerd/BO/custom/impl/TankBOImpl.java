package org.example.firstsemfptolayerd.BO.custom.impl;

import javafx.collections.ObservableList;
import org.example.firstsemfptolayerd.BO.custom.TankBO;
import org.example.firstsemfptolayerd.Dao.DAOFactory;
import org.example.firstsemfptolayerd.Dao.custom.TankDao;
import org.example.firstsemfptolayerd.entity.Tank;
import org.example.firstsemfptolayerd.model.TankDTO;

import java.util.ArrayList;

public class TankBOImpl implements TankBO {
    private final TankDao tankDao = (TankDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.TANK);

    @Override
    public boolean saveTank(TankDTO dto) throws Exception {
        return tankDao.Save(new Tank(
                dto.getTankId(),
                dto.getGlassType(),
                dto.getFishOrPlant(),
                dto.getWaterType()
        ));
    }

    @Override
    public boolean updateTank(TankDTO dto) throws Exception {
        return tankDao.update(new Tank(
                dto.getTankId(),
                dto.getGlassType(),
                dto.getFishOrPlant(),
                dto.getWaterType()
        ));
    }

    @Override
    public boolean deleteTank(String id) throws Exception {
        return tankDao.Delete(id);
    }

    @Override
    public ArrayList<TankDTO> getAllTanks() throws Exception {
        ArrayList<Tank> entityList = tankDao.getAll();
        ArrayList<TankDTO> dtoList = new ArrayList<>();
        for (Tank t : entityList) {
            dtoList.add(new TankDTO(
                    t.getTankId(),
                    t.getGlassType(),
                    t.getFishOrPlant(),
                    t.getWaterType()
            ));
        }
        return dtoList;
    }

    @Override
    public String getNextTankId() throws Exception {
        return tankDao.getNextId();
    }

    @Override
    public ObservableList<String> getTankIds() throws Exception {
        return tankDao.getTankIds();
    }

    @Override
    public ObservableList<String> getGlassTypes() {
        return tankDao.getGlassTypes();
    }

    @Override
    public ObservableList<String> getTankTypes() {
        return tankDao.getTankTypes();
    }

    @Override
    public ObservableList<String> getWaterTypes() {
        return tankDao.getWaterTypes();
    }
}
