package org.example.firstsemfptolayerd.Dao.custom;

import javafx.collections.ObservableList;
import org.example.firstsemfptolayerd.Dao.CrudDao;
import org.example.firstsemfptolayerd.entity.Chemical;

import java.sql.SQLException;

public interface ChemicalDao extends CrudDao<Chemical> {
    ObservableList<String> getChemicalIds() throws SQLException, ClassNotFoundException;
}
