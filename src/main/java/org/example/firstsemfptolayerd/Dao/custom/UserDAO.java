package org.example.firstsemfptolayerd.Dao.custom;

import org.example.firstsemfptolayerd.Dao.CrudDao;
import org.example.firstsemfptolayerd.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserDAO extends CrudDao<User> {
    boolean checkUser(String username) throws SQLException, ClassNotFoundException;
    User getUser(String username) throws SQLException, ClassNotFoundException;
    public ResultSet exist(String id) throws SQLException, ClassNotFoundException;
    public User search(String id) throws SQLException, ClassNotFoundException;
}