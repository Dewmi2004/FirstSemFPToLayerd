package org.example.firstsemfptolayerd.BO.custom.impl;

import org.example.firstsemfptolayerd.BO.custom.FishBO;
import org.example.firstsemfptolayerd.Dao.DAOFactory;
import org.example.firstsemfptolayerd.Dao.custom.FishDao;
import org.example.firstsemfptolayerd.entity.Fish;
import org.example.firstsemfptolayerd.model.FishDTO;

import java.util.ArrayList;

public class FishBOImpl implements FishBO {
    private final FishDao fishDao = (FishDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.FISH);

    @Override
    public boolean saveFish(FishDTO dto) throws Exception {
        return fishDao.Save(new Fish(
                dto.getFishId(),
                dto.getName(),
                dto.getSize(),
                dto.getTankId(),
                dto.getGender(),
                dto.getWaterType(),
                dto.getCountry(),
                dto.getColour(),
                dto.getQuantity()
        ));    }

    @Override
    public boolean updateFish(FishDTO dto) throws Exception {
        return fishDao.update(new Fish(
                dto.getFishId(),
                dto.getName(),
                dto.getSize(),
                dto.getTankId(),
                dto.getGender(),
                dto.getWaterType(),
                dto.getCountry(),
                dto.getColour(),
                dto.getQuantity()
        ));
    }

    @Override
    public boolean deleteFish(String id) throws Exception {
        return fishDao.Delete(id);    }

    @Override
    public ArrayList<FishDTO> getAllFish() throws Exception {
        ArrayList<Fish> list = fishDao.getAll();
        ArrayList<FishDTO> dtoList = new ArrayList<>();
        for (Fish f : list) {
            dtoList.add(new FishDTO(
                    f.getFishId(), f.getName(), f.getSize(), f.getTankId(),
                    f.getGender(), f.getWaterType(), f.getCountry(),
                    f.getColour(), f.getQuantity()
            ));
        }
        return dtoList;    }

    @Override
    public String getNextFishId() throws Exception {
        return fishDao.getNextId();
    }
}
