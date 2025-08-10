package org.example.firstsemfptolayerd.BO.custom;

import org.example.firstsemfptolayerd.BO.SuperBO;
import org.example.firstsemfptolayerd.model.PHChemicalDTO;

import java.util.ArrayList;

public interface PHChemicalBO extends SuperBO {
    boolean savePHChemical(PHChemicalDTO dto) throws Exception;
    boolean updatePHChemical(PHChemicalDTO dto) throws Exception;
    boolean deletePHChemical(String date) throws Exception;
    ArrayList<PHChemicalDTO> getAllPHChemicals() throws Exception;
}
