package com.org.ticketmanagementsystem.repository;

import com.org.ticketmanagementsystem.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
