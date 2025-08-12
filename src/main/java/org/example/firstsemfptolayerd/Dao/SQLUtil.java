package org.example.firstsemfptolayerd.Dao;



import org.example.firstsemfptolayerd.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUtil {

    public static ResultSet exicute(String sql, Object... ob) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getDbConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i=0 ; i< ob.length;i++){
            ps.setObject(i + 1, ob[i]);
        }
        return ps.executeQuery();
    }
    public static boolean executeUpdate(String sql, Object... ob) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getDbConnection().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i =0 ; i< ob.length;i++){
            ps.setObject(i + 1, ob[i]);
        }
        return ps.executeUpdate() > 0;
    }
}
