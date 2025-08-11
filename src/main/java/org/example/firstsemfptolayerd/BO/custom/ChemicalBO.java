package org.example.firstsemfptolayerd.BO.custom;

import javafx.collections.ObservableList;
import org.example.firstsemfptolayerd.BO.SuperBO;
import org.example.firstsemfptolayerd.model.ChemicalDTO;

import java.util.ArrayList;

public interface ChemicalBO extends SuperBO {
    boolean saveChemical(ChemicalDTO dto) throws Exception;
    boolean updateChemical(ChemicalDTO dto) throws Exception;
    boolean deleteChemical(String id) throws Exception;
    ArrayList<ChemicalDTO> getAllChemicals() throws Exception;
    String getNextChemicalId() throws Exception;
    ObservableList<String> getAllChemicalIDS() throws Exception;
}
