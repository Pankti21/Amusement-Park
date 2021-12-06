package com.team5.HAPark.ticket.mocks;

import com.team5.HAPark.ticket.DAO.ITicketPersistence;

public class TicketPersistenceMockFactory implements ITicketPersistenceMockFactory {

    @Override
    public ITicketPersistence getTicketPersistenceMock() {
        return new TicketPersistenceMock();
    }
}
