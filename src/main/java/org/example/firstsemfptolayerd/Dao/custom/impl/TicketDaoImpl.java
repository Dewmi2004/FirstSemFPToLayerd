package org.example.firstsemfptolayerd.Dao.custom.impl;

import org.example.firstsemfptolayerd.Dao.SQLUtil;
import org.example.firstsemfptolayerd.Dao.custom.TicketDao;
import org.example.firstsemfptolayerd.entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TicketDaoImpl implements TicketDao {
    @Override
    public ArrayList<Ticket> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Ticket> list = new ArrayList<>();
        ResultSet rs = SQLUtil.exicute("SELECT * FROM ticket");
        while (rs.next()) {
            list.add(new Ticket(
                    rs.getString("ticket_Id"),
                    rs.getString("age"),
                    rs.getString("price"),
                    rs.getDate("date"),
                    rs.getTime("time"),
                    rs.getString("customer_Id"),
                    rs.getString("employee_Id"),
                    rs.getString("Quantity"),
                    rs.getString("Full_Price")
            ));
        }
        return list;
    }

    @Override
    public boolean Save(Ticket ticket) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO ticket VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                ticket.getTicketId(),
                ticket.getAge(),
                ticket.getPrice(),
                ticket.getDate(),
                ticket.getTime(),
                ticket.getCustomerId(),
                ticket.getEmployeeId(),
                ticket.getQuantity(),
                ticket.getFullPrice());
    }

    @Override
    public boolean update(Ticket ticket) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE ticket SET age=?, price=?, date=?, time=?, customer_Id=?, employee_Id=?, Quantity=?, Full_Price=? WHERE ticket_Id=?",
                ticket.getAge(),
                ticket.getPrice(),
                ticket.getDate(),
                ticket.getTime(),
                ticket.getCustomerId(),
                ticket.getEmployeeId(),
                ticket.getQuantity(),
                ticket.getFullPrice(),
                ticket.getTicketId());
    }

    @Override
    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM ticket WHERE ticket_Id = ?", id);
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.exicute("SELECT ticket_Id FROM ticket ORDER BY CAST(SUBSTRING(ticket_Id, 5) AS UNSIGNED) DESC LIMIT 1");
        if (rs.next()) {
            String lastId = rs.getString(1);
            int nextId = Integer.parseInt(lastId.replace("TKT-", "")) + 1;
            return String.format("TKT-%03d", nextId);
        } else {
            return "TKT-001";
        }
    }
}
