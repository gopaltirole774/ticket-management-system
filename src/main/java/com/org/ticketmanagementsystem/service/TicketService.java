package com.org.ticketmanagementsystem.service;

import com.org.ticketmanagementsystem.dto.TicketPatchRequestDto;
import com.org.ticketmanagementsystem.dto.TicketRequestDto;
import com.org.ticketmanagementsystem.dto.TicketResponseDto;

import java.util.List;

public interface TicketService {

    TicketResponseDto createTicket(TicketRequestDto ticketRequestDto);

    TicketResponseDto getTicketById(Integer id);

    List<TicketResponseDto> getAllTickets();

    void deleteTicket(Integer id);

    TicketResponseDto updateTicket(TicketRequestDto ticketRequestDto, Integer id);

    TicketResponseDto patchTicket(TicketPatchRequestDto ticketPatchRequestDto, Integer id);




}
