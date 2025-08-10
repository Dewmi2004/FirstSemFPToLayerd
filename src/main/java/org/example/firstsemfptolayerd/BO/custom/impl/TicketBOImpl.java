package org.example.firstsemfptolayerd.BO.custom.impl;

import org.example.firstsemfptolayerd.BO.custom.TicketBO;
import org.example.firstsemfptolayerd.Dao.DAOFactory;
import org.example.firstsemfptolayerd.Dao.custom.TicketDao;
import org.example.firstsemfptolayerd.entity.Ticket;
import org.example.firstsemfptolayerd.model.TicketDTO;

import java.sql.Date;
import java.util.ArrayList;

public class TicketBOImpl implements TicketBO {
    private final TicketDao ticketDAO = (TicketDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOtypes.TICKET);

    @Override
    public boolean saveTicket(TicketDTO dto) throws Exception {
        return ticketDAO.Save(new Ticket(
                dto.getTicketId(),
                dto.getAge(),
                dto.getPrice(),
                dto.getDate(),
                dto.getTime(),
                dto.getCustomerId(),
                dto.getEmployeeId(),
                dto.getQuantity(),
                dto.getFullPrice()
        ));
    }

    @Override
    public boolean updateTicket(TicketDTO dto) throws Exception {
        return ticketDAO.update(new Ticket(
                dto.getTicketId(),
                dto.getAge(),
                dto.getPrice(),
                dto.getDate(),
                dto.getTime(),
                dto.getCustomerId(),
                dto.getEmployeeId(),
                dto.getQuantity(),
                dto.getFullPrice()
        ));
    }
    @Override
    public boolean deleteTicket(String id) throws Exception {
        return ticketDAO.Delete(id);
    }

    @Override
    public ArrayList<TicketDTO> getAllTickets() throws Exception {
        ArrayList<Ticket> entityList = ticketDAO.getAll();
        ArrayList<TicketDTO> dtoList = new ArrayList<>();
        for (Ticket ticket : entityList) {
            dtoList.add(new TicketDTO(
                    ticket.getTicketId(),
                    ticket.getAge(),
                    ticket.getPrice(),
                    (Date) ticket.getDate(),
                    ticket.getTime(),
                    ticket.getCustomerId(),
                    ticket.getEmployeeId(),
                    ticket.getQuantity()
            ));
        }
        return dtoList;
    }

    @Override
    public String getNextTicketId() throws Exception {
        return ticketDAO.getNextId();
    }
}
