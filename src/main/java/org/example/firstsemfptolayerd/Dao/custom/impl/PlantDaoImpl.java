package org.example.firstsemfptolayerd.Dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.firstsemfptolayerd.Dao.SQLUtil;
import org.example.firstsemfptolayerd.Dao.custom.PlantDao;
import org.example.firstsemfptolayerd.entity.Plant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlantDaoImpl implements PlantDao {
    @Override
    public ArrayList<Plant> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT * FROM plant");
        ArrayList<Plant> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Plant(
                    rs.getString("plant_Id"),
                    rs.getString("name"),
                    rs.getString("water_Type"),
                    rs.getString("tank_Id"),
                    rs.getString("size"),
                    rs.getString("quantity")
            ));
        }
        return list;

    }

    @Override
    public boolean Save(Plant plant) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "INSERT INTO plant (plant_Id, name, water_Type, tank_Id, size, quantity) VALUES (?,?,?,?,?,?)",
                plant.getPlantId(),
                plant.getName(),
                plant.getWaterType(),
                plant.getTankId(),
                plant.getSize(),
                plant.getQuantity()
        );
    }

    @Override
    public boolean update(Plant plant) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "UPDATE plant SET name=?, water_Type=?, tank_Id=?, size=?, quantity=? WHERE plant_Id=?",
                plant.getName(),
                plant.getWaterType(),
                plant.getTankId(),
                plant.getSize(),
                plant.getQuantity(),
                plant.getPlantId()
        );
    }

    @Override
    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM plant WHERE plant_Id=?", id);
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT plant_Id FROM plant ORDER BY plant_Id DESC LIMIT 1");
        if (rs.next()) {
            String lastId = rs.getString("plant_Id");
            int num = Integer.parseInt(lastId.replace("PL", ""));
            return String.format("PL%03d", num + 1);
        }
        return "PL001";    }

    @Override
    public Plant searchPlantByName(String plantId) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT name FROM plant WHERE plant_Id = ?", plantId);
        if (rs.next()) {
            return new Plant(
                    rs.getString("name")
            );
        }
        return null;
    }

    @Override
    public List<String> getAllPlantIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT plant_Id FROM plant");
        ObservableList<String> plantDtoArrayList = FXCollections.observableArrayList();
        while (rs.next()) {
            plantDtoArrayList.add(rs.getString("plant_Id"));
        }
        return  plantDtoArrayList;
    }

    @Override
    public boolean updatePlant(Plant plant) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "UPDATE plant SET quantity = quantity - ? WHERE plant_Id = ?",
                plant.getQuantity(), plant.getPlantId()
        );
    }
}
