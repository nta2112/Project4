package com.aptech.project4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aptech.project4.models.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
