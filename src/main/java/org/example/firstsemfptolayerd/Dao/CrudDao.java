package org.example.firstsemfptolayerd.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDao<T> extends SuperDao{
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    public boolean Save(T customerDTO) throws SQLException, ClassNotFoundException;
    public boolean update(T customerDTO) throws SQLException, ClassNotFoundException ;
    public boolean Delete(String id) throws SQLException, ClassNotFoundException ;
    public String getNextId() throws SQLException, ClassNotFoundException;

}
