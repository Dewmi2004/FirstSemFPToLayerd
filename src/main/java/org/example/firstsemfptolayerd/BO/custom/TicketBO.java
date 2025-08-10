package org.example.firstsemfptolayerd.BO.custom;

import org.example.firstsemfptolayerd.BO.SuperBO;
import org.example.firstsemfptolayerd.Dao.custom.TicketDao;
import org.example.firstsemfptolayerd.model.TicketDTO;

import java.util.ArrayList;

public interface TicketBO extends SuperBO {
    boolean saveTicket(TicketDTO ticket) throws Exception;
    boolean updateTicket(TicketDTO ticket) throws Exception;
    boolean deleteTicket(String id) throws Exception;
    ArrayList<TicketDTO> getAllTickets() throws Exception;
    String getNextTicketId() throws Exception;
}
