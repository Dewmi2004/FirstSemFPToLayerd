package org.example.firstsemfptolayerd.Dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.firstsemfptolayerd.Dao.SQLUtil;
import org.example.firstsemfptolayerd.Dao.custom.FishDao;
import org.example.firstsemfptolayerd.entity.Fish;
import org.example.firstsemfptolayerd.model.FishDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FishDaoImpl implements FishDao {
    @Override
    public ArrayList<Fish> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT * FROM fish");
        ArrayList<Fish> fishList = new ArrayList<>();
        while (rs.next()) {
            fishList.add(new Fish(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9)
            ));
        }
        return fishList;
    }

    @Override
    public boolean Save(Fish fish) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO fish VALUES (?,?,?,?,?,?,?,?,?)",
                fish.getFishId(), fish.getName(), fish.getSize(), fish.getTankId(),
                fish.getGender(), fish.getWaterType(), fish.getCountry(),
                fish.getColour(), fish.getQuantity());
    }

    @Override
    public boolean update(Fish fish) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE fish SET name=?, size=?, tank_Id=?, gender=?, water_Type=?, country=?, colour=?, quantity=? WHERE fish_Id=?",
                fish.getName(), fish.getSize(), fish.getTankId(), fish.getGender(),
                fish.getWaterType(), fish.getCountry(), fish.getColour(), fish.getQuantity(),
                fish.getFishId());    }

    @Override
    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM fish WHERE fish_Id=?", id);
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT fish_Id FROM fish ORDER BY fish_Id DESC LIMIT 1");
        if (rs.next()) {
            String lastId = rs.getString(1).substring(1);
            int newId = Integer.parseInt(lastId) + 1;
            return String.format("F%03d", newId);
        }
        return "F001";
    }
    public ObservableList<String> getFishSize() {
        return FXCollections.observableArrayList("Fry Fish", "Juvenile Fish", "Young Adult Fish", "Adult age", "Mature Adult Fish");
    }

    public ObservableList<String> getFishGender() {
        return FXCollections.observableArrayList("Male", "Female");
    }

    public ObservableList<String> getFishWatertype() {
        return FXCollections.observableArrayList(
                "Fresh Water", "Brackish  Water", "Salt Water (Marian)",
                "Soft Water", "Hard Water", "Cold Water", "Tropical Water");
    }

    public ObservableList<String> getFishCountry() {
        return FXCollections.observableArrayList(
                "Sri Lanka", "Maldives", "Djibouti", "Australia", "Japan",
                "Brazil", "Somalia", "United States", "Indonesia", "Ghana", "Malaysia");
    }

    @Override
    public List<String> getAllfishIds() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("select fish_Id from fish");
        ObservableList<String> fishDtoArrayList = FXCollections.observableArrayList();
        while (rs.next()) {
            fishDtoArrayList.add(rs.getString("fish_Id"));
        }
        return  fishDtoArrayList;
    }

    @Override
    public Fish searchfishByName(String fishId) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT name FROM fish WHERE fish_Id = ?", fishId);
        if (rs.next()) {
            return new Fish(
                    rs.getString("name")
            );
        }
        return null;
    }

    @Override
    public boolean updateFish(Fish fish) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "UPDATE fish SET quantity = quantity - ? WHERE fish_Id = ?",
                fish.getQuantity(), fish.getFishId()

        );
    }

    @Override
    public boolean updateFishQntyUp(int fishquantity, String itemId) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate(
                "UPDATE fish SET quantity = quantity + ? WHERE fish_Id=?", fishquantity, itemId
        );
    }
}
