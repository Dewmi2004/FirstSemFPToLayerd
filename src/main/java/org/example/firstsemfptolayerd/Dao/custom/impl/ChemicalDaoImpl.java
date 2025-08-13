package org.example.firstsemfptolayerd.Dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.firstsemfptolayerd.Dao.SQLUtil;
import org.example.firstsemfptolayerd.Dao.custom.ChemicalDao;
import org.example.firstsemfptolayerd.entity.Chemical;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChemicalDaoImpl implements ChemicalDao {
    @Override
    public ArrayList<Chemical> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT * FROM chemical");
        ArrayList<Chemical> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Chemical(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            ));
        }
        return list;
    }

    @Override
    public boolean Save(Chemical chemical) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO chemical VALUES (?, ?, ?, ?, ?, ?)",
                chemical.getChemicalId(),
                chemical.getAcidOrBase(),
                chemical.getConcentration(),
                chemical.getStoreType(),
                chemical.getName(),
                chemical.getQuantity());    }

    @Override
    public boolean update(Chemical chemical) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE chemical SET acid_Or_Base = ?, concentration = ?, store_Type = ?, name = ?, quantity = ? WHERE chemical_Id = ?",
                chemical.getAcidOrBase(),
                chemical.getConcentration(),
                chemical.getStoreType(),
                chemical.getName(),
                chemical.getQuantity(),
                chemical.getChemicalId());    }

    @Override
    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM chemical WHERE chemical_Id = ?", id);
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT chemical_Id FROM chemical ORDER BY chemical_Id DESC LIMIT 1");
        char prefix = 'C';
        if (rs.next()) {
            String lastId = rs.getString(1);
            String number = lastId.substring(1);
            int nextId = Integer.parseInt(number) + 1;
            return String.format("%c%03d", prefix, nextId);
        }
        return prefix + "001";
    }

    @Override
    public ObservableList<String> getChemicalIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT chemical_Id FROM chemical");
        ObservableList<String> list = FXCollections.observableArrayList();
        while (rs.next()) {
            list.add(rs.getString("chemical_Id"));
        }
        return list;
    }

    @Override
    public boolean updateChemicalQntyUp(String chemicalquantity, String itemId) throws SQLException, ClassNotFoundException {
        return  SQLUtil.executeUpdate("UPDATE chemical SET quantity = quantity + ? WHERE chemical_Id=?",chemicalquantity, itemId);

    }
}


