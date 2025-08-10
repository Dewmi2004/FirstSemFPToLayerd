package org.example.firstsemfptolayerd.BO.custom.impl;

import org.example.firstsemfptolayerd.BO.custom.PHChemicalBO;
import org.example.firstsemfptolayerd.Dao.DAOFactory;
import org.example.firstsemfptolayerd.Dao.custom.PHChemicalDao;
import org.example.firstsemfptolayerd.entity.PHChemical;
import org.example.firstsemfptolayerd.model.PHChemicalDTO;

import java.util.ArrayList;

public class PHChemicalBOImpl implements PHChemicalBO {
    private final PHChemicalDao phChemicalDAO =
            (PHChemicalDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.PH_CHEMICAL);

    @Override
    public boolean savePHChemical(PHChemicalDTO dto) throws Exception {
        return phChemicalDAO.Save(new PHChemical(
                dto.getTankId(),
                dto.getChemicalId(),
                dto.getPhLevel(),
                dto.getDate(),
                dto.getTime()
        ));
    }

    @Override
    public boolean updatePHChemical(PHChemicalDTO dto) throws Exception {
        return phChemicalDAO.update(new PHChemical(
                dto.getTankId(),
                dto.getChemicalId(),
                dto.getPhLevel(),
                dto.getDate(),
                dto.getTime()
        ));
    }

    @Override
    public boolean deletePHChemical(String date) throws Exception {
        return phChemicalDAO.Delete(date);
    }

    @Override
    public ArrayList<PHChemicalDTO> getAllPHChemicals() throws Exception {
        ArrayList<PHChemical> entities = phChemicalDAO.getAll();
        ArrayList<PHChemicalDTO> dtos = new ArrayList<>();
        for (PHChemical e : entities) {
            dtos.add(new PHChemicalDTO(
                    e.getTankId(),
                    e.getChemicalId(),
                    e.getPhLevel(),
                    e.getDate(),
                    e.getTime()
            ));
        }
        return dtos;
    }
}
