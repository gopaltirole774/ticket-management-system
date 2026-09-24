package com.org.ticketmanagementsystem.service.impl;

import com.org.ticketmanagementsystem.dto.TicketPatchRequestDto;
import com.org.ticketmanagementsystem.dto.TicketRequestDto;
import com.org.ticketmanagementsystem.dto.TicketResponseDto;
import com.org.ticketmanagementsystem.entity.Ticket;
import com.org.ticketmanagementsystem.exception.TicketNotFoundException;
import com.org.ticketmanagementsystem.repository.TicketRepository;
import com.org.ticketmanagementsystem.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;


    public Ticket toEntity(TicketRequestDto ticketRequestDto) {

        Ticket ticket = new Ticket();
        ticket.setCategory(ticketRequestDto.getCategory());
        ticket.setPriority(ticketRequestDto.getPriority());
        ticket.setStatus(ticketRequestDto.getStatus());
        ticket.setTitle(ticketRequestDto.getTitle());
        ticket.setDescription(ticketRequestDto.getDescription());
        ticket.setCreatedBy(ticketRequestDto.getCreatedBy());
        return ticket;
    }

    public TicketResponseDto toResponse(Ticket ticket) {

        TicketResponseDto ticketResponseDto = new TicketResponseDto();

        ticketResponseDto.setDescription(ticket.getDescription());
        ticketResponseDto.setCategory(ticket.getCategory());
        ticketResponseDto.setPriority(ticket.getPriority());
        ticketResponseDto.setStatus(ticket.getStatus());
        ticketResponseDto.setTitle(ticket.getTitle());
        ticketResponseDto.setCreatedBy(ticket.getCreatedBy());
        ticketResponseDto.setId(ticket.getId());

        return ticketResponseDto;
    }

    @Override
    public TicketResponseDto createTicket(TicketRequestDto ticketRequestDto) {
        Ticket saved = ticketRepository.save(toEntity(ticketRequestDto));
        return toResponse(saved);
    }

    @Override
    public TicketResponseDto getTicketById(Integer id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException("Ticket not found with id: " + id));
        return toResponse(ticket);
    }

    @Override
    public List<TicketResponseDto> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();

        List<TicketResponseDto> ticketResponseDtoList = new ArrayList<>();

        for (Ticket ticket : tickets) {
            ticketResponseDtoList.add(toResponse(ticket));
        }
        return ticketResponseDtoList;

    }

    @Override

    public void deleteTicket(Integer id) {
        ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException("Ticket not found with id: " + id));
        ticketRepository.deleteById(id);

    }

    @Override
    public TicketResponseDto updateTicket(TicketRequestDto ticketRequestDto, Integer id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException("Ticket not found with id: " + id));

        ticket.setTitle(ticketRequestDto.getTitle());
        ticket.setPriority(ticketRequestDto.getPriority());
        ticket.setCreatedBy(ticketRequestDto.getCreatedBy());
        ticket.setStatus(ticketRequestDto.getStatus());
        ticket.setCategory(ticketRequestDto.getCategory());
        ticket.setDescription(ticketRequestDto.getDescription());
        Ticket updateTicket = ticketRepository.save(ticket);
        return toResponse(updateTicket);


    }

    @Override
    public TicketResponseDto patchTicket(TicketPatchRequestDto ticketPatchRequestDto, Integer id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException("Ticket not found with id: " + id));


        if (ticketPatchRequestDto.getTitle() != null) {
            ticket.setTitle(ticketPatchRequestDto.getTitle());
        }
        if (ticketPatchRequestDto.getCategory() != null) {
            ticket.setCategory(ticketPatchRequestDto.getCategory());
        }

        if (ticketPatchRequestDto.getDescription() != null) {
            ticket.setDescription(ticketPatchRequestDto.getDescription());
        }

        if (ticketPatchRequestDto.getPriority() != null) {
            ticket.setPriority(ticketPatchRequestDto.getPriority());
        }

        if (ticketPatchRequestDto.getStatus() != null) {
            ticket.setStatus(ticketPatchRequestDto.getStatus());
        }

        Ticket updateTicket = ticketRepository.save(ticket);
        return toResponse(updateTicket);


    }


}
