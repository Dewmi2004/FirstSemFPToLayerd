package org.example.firstsemfptolayerd.Dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.firstsemfptolayerd.Dao.SQLUtil;
import org.example.firstsemfptolayerd.Dao.custom.TankDao;
import org.example.firstsemfptolayerd.entity.Tank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TankDaoImpl implements TankDao {
    @Override
    public ObservableList<String> getTankIds() throws Exception {
        ResultSet rs = SQLUtil.exicute("SELECT tank_Id FROM tank");
        ObservableList<String> list = FXCollections.observableArrayList();
        while (rs.next()) {
            list.add(rs.getString("tank_Id"));
        }
        return list;
    }

    @Override
    public ObservableList<String> getGlassTypes() {
        return FXCollections.observableArrayList("Acrylic", "Glass", "Tempered Glass", "Plastic");
    }

    @Override
    public ObservableList<String> getTankTypes() {
        return FXCollections.observableArrayList("Fish", "Plant", "Mixed");
    }

    @Override
    public ObservableList<String> getWaterTypes() {
        return FXCollections.observableArrayList("Fresh Water", "Brackish Water", "Salt Water", "Soft Water", "Hard Water", "Cold Water", "Tropical Water");
    }

    @Override
    public ArrayList<Tank> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT * FROM tank");
        ArrayList<Tank> tankList = new ArrayList<>();
        while (rs.next()) {
            tankList.add(new Tank(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
            ));
        }
        return tankList;    }

    @Override
    public boolean Save(Tank tank) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO tank VALUES (?, ?, ?, ?)",
                tank.getTankId(),
                tank.getGlassType(),
                tank.getFishOrPlant(),
                tank.getWaterType());
    }

    @Override
    public boolean update(Tank tank) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE tank SET glass_Type = ?, fish_Or_Plant = ?, water_Type = ? WHERE tank_Id = ?",
                tank.getGlassType(),
                tank.getFishOrPlant(),
                tank.getWaterType(),
                tank.getTankId());
    }

    @Override
    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM tank WHERE tank_Id = ?", id);
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT tank_Id FROM tank ORDER BY tank_Id DESC LIMIT 1");
        char prefix = 'T';
        if (rs.next()) {
            String lastId = rs.getString(1);
            int nextId = Integer.parseInt(lastId.substring(1)) + 1;
            return String.format("%c%03d", prefix, nextId);
        }
        return prefix + "001";    }
}
