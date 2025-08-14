package org.example.firstsemfptolayerd.Dao.custom.impl;

import org.example.firstsemfptolayerd.Dao.SQLUtil;
import org.example.firstsemfptolayerd.Dao.custom.UserDAO;
import org.example.firstsemfptolayerd.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDaoImpl implements UserDAO {
    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.exicute("SELECT * FROM users");
        ArrayList<User> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new User(
                    resultSet.getString("user_id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("role"),
                    resultSet.getString("password")

            ));
        }
        return list;

    }

    @Override
    public boolean Save(User user) throws SQLException, ClassNotFoundException {
        SQLUtil.executeUpdate(
                "INSERT INTO users VALUES (?,?,?,?,?)",
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getPassword()

        );
        return true;
    }

    @Override
    public boolean update(User customerDTO) throws SQLException, ClassNotFoundException {
        boolean executeUpdate = SQLUtil.executeUpdate(
                "UPDATE users SET name=?, email=?, role=?, password=? WHERE user_id=?",
                customerDTO.getName(),
                customerDTO.getEmail(),
                customerDTO.getRole(),
                customerDTO.getPassword(),
                customerDTO.getId()
        );
        return executeUpdate;
    }

    @Override
    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
        boolean executeUpdate = SQLUtil.executeUpdate("DELETE FROM users WHERE user_id=?", id);
        return executeUpdate;    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        try{
            ResultSet rs = SQLUtil.exicute("select id from users order by id DESC limit 1");
            char tableCharactor ='U';
            if(rs.next()){
                String lastId =rs.getString(1);
                String lastIdNumberString = lastId.substring(1);
                int lastIdNumber = Integer.parseInt(lastIdNumberString);
                int nextIdNumber = lastIdNumber + 1;
                String nextIdString =String.format("U%03d", nextIdNumber);
                return nextIdString;
            }
            return tableCharactor+"001";

        }catch (Exception e){
            return "U001";
        }    }

    @Override
    public ResultSet exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.exicute("SELECT * FROM users WHERE user_id=?", id);
        return resultSet;
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.exicute("SELECT * FROM users WHERE user_id=?", id);
        if (resultSet.next()) {
            return new User(
                    resultSet.getString("user_id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("role"),
                    resultSet.getString("password")

            );
        }
        return null;
    }

    @Override
    public boolean checkUser(String username) throws SQLException, ClassNotFoundException {
        try {
            ResultSet rs = SQLUtil.exicute("SELECT * FROM users WHERE name = ?", username);

            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public User getUser(String username) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.exicute("SELECT * FROM users WHERE name = ?", username);
        if (resultSet.next()) {
            User user = new User(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("role"),
                    resultSet.getString("password")
            );

            return user;
        }

        return null;
    }
}