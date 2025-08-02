package com.example.layeredarchitecture.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDao<T> extends SuperDao{
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    public boolean Save(T customerDTO) throws SQLException, ClassNotFoundException;
    public boolean update(T customerDTO) throws SQLException, ClassNotFoundException ;
    public boolean exist(String id) throws SQLException, ClassNotFoundException ;
    public boolean Delete(String id) throws SQLException, ClassNotFoundException ;
    public String Genaratenewid() throws SQLException, ClassNotFoundException;
    public  T search(String newValue) throws SQLException, ClassNotFoundException ;

}
