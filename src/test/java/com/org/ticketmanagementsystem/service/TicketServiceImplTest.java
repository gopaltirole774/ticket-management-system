package com.org.ticketmanagementsystem.service;

import com.org.ticketmanagementsystem.dto.TicketPatchRequestDto;
import com.org.ticketmanagementsystem.dto.TicketRequestDto;
import com.org.ticketmanagementsystem.dto.TicketResponseDto;
import com.org.ticketmanagementsystem.entity.Ticket;
import com.org.ticketmanagementsystem.exception.TicketNotFoundException;
import com.org.ticketmanagementsystem.repository.TicketRepository;
import com.org.ticketmanagementsystem.service.impl.TicketServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.org.ticketmanagementsystem.enums.Category.LOGIN;
import static com.org.ticketmanagementsystem.enums.Priority.MEDIUM;
import static com.org.ticketmanagementsystem.enums.Status.OPEN;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketServiceImpl ticketServiceImpl;

    private Ticket ticket;
    private TicketRequestDto ticketRequestDto;
    private TicketPatchRequestDto ticketPatchRequestDto;

    @BeforeEach
    void setUp() {
        ticketRequestDto = new TicketRequestDto();
        ticketRequestDto.setDescription("Unable to login to the application using valid credentials");
        ticketRequestDto.setStatus(OPEN);
        ticketRequestDto.setPriority(MEDIUM);
        ticketRequestDto.setCategory(LOGIN);
        ticketRequestDto.setTitle("Unable to login");
        ticketRequestDto.setCreatedBy("Rahul");

        ticket = new Ticket();
        ticket.setId(1);
        ticket.setDescription(ticketRequestDto.getDescription());
        ticket.setStatus(ticketRequestDto.getStatus());
        ticket.setPriority(ticketRequestDto.getPriority());
        ticket.setCategory(ticketRequestDto.getCategory());
        ticket.setTitle(ticketRequestDto.getTitle());
        ticket.setCreatedBy(ticketRequestDto.getCreatedBy());

        ticketPatchRequestDto = new TicketPatchRequestDto();
        ticketPatchRequestDto.setStatus(ticketRequestDto.getStatus());
        ticketPatchRequestDto.setPriority(ticketRequestDto.getPriority());
        ticketPatchRequestDto.setCategory(ticketRequestDto.getCategory());
        ticketPatchRequestDto.setTitle(ticketRequestDto.getTitle());
        ticketPatchRequestDto.setDescription(ticketRequestDto.getDescription());

    }

    @Test
    void createTicketTest() {
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);
        TicketResponseDto ticketResponseDto = ticketServiceImpl.createTicket(ticketRequestDto);
        assertNotNull(ticketResponseDto);
        assertEquals(ticketRequestDto.getCategory(), ticketResponseDto.getCategory());

        verify(ticketRepository).save(any(Ticket.class));

    }

    @Test
    void getTicketByIdTest() {
        when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));
        TicketResponseDto ticketResponseDto = ticketServiceImpl.getTicketById(1);
        assertNotNull(ticketResponseDto);
        assertEquals(ticketRequestDto.getCategory(), ticketResponseDto.getCategory());

        verify(ticketRepository).findById(1);
    }

    @Test
    void getTicketByIdNotFoundTest() {
        when(ticketRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(TicketNotFoundException.class, () -> ticketServiceImpl.getTicketById(1));
        verify(ticketRepository).findById(1);
    }

    @Test
    void deleteTicketTest() {
        when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));
        ticketServiceImpl.deleteTicket(1);
        verify(ticketRepository).deleteById(1);
    }

    @Test
    void deleteTicketNotFoundTest() {
        when(ticketRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(TicketNotFoundException.class, () -> ticketServiceImpl.deleteTicket(1));
        verify(ticketRepository).findById(1);
    }

    @Test
    void getAllTicketsTest() {
        when(ticketRepository.findAll()).thenReturn(List.of(ticket));
        List<TicketResponseDto> ticketResponseDtoList = ticketServiceImpl.getAllTickets();
        assertNotNull(ticketResponseDtoList);
        assertEquals(1, ticketResponseDtoList.size());
        verify(ticketRepository).findAll();
    }

    @Test
    void getAllTicketsListEmpty() {
        when(ticketRepository.findAll()).thenReturn(List.of());
        List<TicketResponseDto> ticketResponseDtoList = ticketServiceImpl.getAllTickets();
        assertNotNull(ticketResponseDtoList);
        assertEquals(0, ticketResponseDtoList.size());
        verify(ticketRepository).findAll();
    }

    @Test
    void updateTicketTest() {
        when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(ticket)).thenReturn(ticket);
        TicketResponseDto ticketResponseDto = ticketServiceImpl.updateTicket(ticketRequestDto, 1);
        assertNotNull(ticketResponseDto);
        assertEquals(ticketRequestDto.getCategory(), ticketResponseDto.getCategory());
        verify(ticketRepository).findById(1);
        verify(ticketRepository).save(ticket);
    }

    @Test
    void updateTicketNotFoundTest() {
        when(ticketRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(TicketNotFoundException.class, () -> ticketServiceImpl.updateTicket(ticketRequestDto, 1));
        verify(ticketRepository).findById(1);
    }


}
