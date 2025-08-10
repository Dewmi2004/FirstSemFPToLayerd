package org.example.firstsemfptolayerd.Dao.custom.impl;

import org.example.firstsemfptolayerd.Dao.SQLUtil;
import org.example.firstsemfptolayerd.Dao.custom.PHChemicalDao;
import org.example.firstsemfptolayerd.entity.PHChemical;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PHChemicalDaoImpl implements PHChemicalDao {
    @Override
    public ArrayList<PHChemical> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT * FROM ph_chemical");
        ArrayList<PHChemical> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new PHChemical(
                    rs.getString("tank_Id"),
                    rs.getString("chemical_Id"),
                    rs.getString("ph_Level"),
                    rs.getString("date"),
                    rs.getString("check_In_Time")
            ));
        }
        return list;
    }

    @Override
    public boolean Save(PHChemical entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "INSERT INTO ph_chemical VALUES (?, ?, ?, ?, ?)",
                entity.getPhLevel(),
                entity.getTankId(),
                entity.getTime(),
                entity.getChemicalId(),
                entity.getDate()
        );
    }

    @Override
    public boolean update(PHChemical entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "UPDATE ph_chemical SET ph_Level=?, check_In_Time=?, chemical_Id=?, date=? WHERE tank_Id=?",
                entity.getPhLevel(),
                entity.getTime(),
                entity.getChemicalId(),
                entity.getDate(),
                entity.getTankId()
        );
    }

    @Override
    public boolean Delete(String date) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM ph_chemical WHERE date=?", date);

    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }
}
