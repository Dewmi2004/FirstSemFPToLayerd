package org.example.firstsemfptolayerd.Dao.custom;

import javafx.collections.ObservableList;
import org.example.firstsemfptolayerd.Dao.CrudDao;
import org.example.firstsemfptolayerd.entity.Chemical;
import org.example.firstsemfptolayerd.view.tdm.InventoryTM;

import java.sql.SQLException;

public interface ChemicalDao extends CrudDao<Chemical> {
    ObservableList<String> getChemicalIds() throws SQLException, ClassNotFoundException;

    boolean updateChemicalQntyUp(Integer chemicalquantity, String itemId) throws SQLException, ClassNotFoundException;
}
