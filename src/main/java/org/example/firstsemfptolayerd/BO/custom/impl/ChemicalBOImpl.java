package org.example.firstsemfptolayerd.BO.custom.impl;

import javafx.collections.ObservableList;
import org.example.firstsemfptolayerd.BO.custom.ChemicalBO;
import org.example.firstsemfptolayerd.Dao.DAOFactory;
import org.example.firstsemfptolayerd.Dao.custom.ChemicalDao;
import org.example.firstsemfptolayerd.entity.Chemical;
import org.example.firstsemfptolayerd.model.ChemicalDTO;

import java.util.ArrayList;

public class ChemicalBOImpl implements ChemicalBO {
    private final ChemicalDao chemicalDAO = (ChemicalDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.CHEMICAL);

    @Override
    public boolean saveChemical(ChemicalDTO dto) throws Exception {
        return chemicalDAO.Save(new Chemical(
                dto.getChemicalId(),
                dto.getAcidOrBase(),
                dto.getConcentration(),
                dto.getStoreType(),
                dto.getName(),
                dto.getQuantity()
        ));
    }

    @Override
    public boolean updateChemical(ChemicalDTO dto) throws Exception {
        return chemicalDAO.update(new Chemical(
                dto.getChemicalId(),
                dto.getAcidOrBase(),
                dto.getConcentration(),
                dto.getStoreType(),
                dto.getName(),
                dto.getQuantity()
        ));
    }


    @Override
    public boolean deleteChemical(String id) throws Exception {
        return chemicalDAO.Delete(id);
    }

    @Override
    public ArrayList<ChemicalDTO> getAllChemicals() throws Exception {
        ArrayList<Chemical> entityList = chemicalDAO.getAll();
        ArrayList<ChemicalDTO> dtoList = new ArrayList<>();
        for (Chemical c : entityList) {
            dtoList.add(new ChemicalDTO(
                    c.getChemicalId(),
                    c.getAcidOrBase(),
                    c.getConcentration(),
                    c.getStoreType(),
                    c.getName(),
                    c.getQuantity()
            ));
        }
        return dtoList;
    }

    @Override
    public String getNextChemicalId() throws Exception {
        return chemicalDAO.getNextId();
    }

    @Override
    public ObservableList<String> getAllChemicalIDS() throws Exception {
        return chemicalDAO.getChemicalIds();
    }
}
