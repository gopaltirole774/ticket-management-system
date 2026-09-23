package com.org.ticketmanagementsystem.controller;

import com.org.ticketmanagementsystem.dto.TicketPatchRequestDto;
import com.org.ticketmanagementsystem.dto.TicketRequestDto;
import com.org.ticketmanagementsystem.dto.TicketResponseDto;
import com.org.ticketmanagementsystem.enums.Category;
import com.org.ticketmanagementsystem.enums.Priority;
import com.org.ticketmanagementsystem.enums.Status;
import com.org.ticketmanagementsystem.exception.TicketNotFoundException;
import com.org.ticketmanagementsystem.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(TicketController.class)
@AutoConfigureMockMvc(addFilters = false)
class TicketControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    TicketService ticketService;

    @Autowired
    ObjectMapper objectMapper;

    private TicketRequestDto ticketRequestDto;
    private TicketPatchRequestDto ticketPatchRequestDto;
    private TicketResponseDto ticketResponseDto;


    @BeforeEach
    void setup() {

        ticketRequestDto = new TicketRequestDto();
        ticketRequestDto.setStatus(Status.RESOLVED);
        ticketRequestDto.setPriority(Priority.HIGH);
        ticketRequestDto.setCategory(Category.LOGIN);
        ticketRequestDto.setTitle("Unable to login");
        ticketRequestDto.setDescription("Unable to login to the application using valid credentials");
        ticketRequestDto.setCreatedBy("Rahul");

        ticketPatchRequestDto = new TicketPatchRequestDto();
        ticketPatchRequestDto.setStatus(ticketRequestDto.getStatus());
        ticketPatchRequestDto.setPriority(ticketRequestDto.getPriority());
        ticketPatchRequestDto.setCategory(ticketRequestDto.getCategory());
        ticketPatchRequestDto.setTitle(ticketRequestDto.getTitle());
        ticketPatchRequestDto.setDescription(ticketRequestDto.getDescription());

        ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setCategory(ticketRequestDto.getCategory());
        ticketResponseDto.setPriority(ticketRequestDto.getPriority());
        ticketResponseDto.setStatus(ticketRequestDto.getStatus());
        ticketResponseDto.setTitle(ticketRequestDto.getTitle());
        ticketResponseDto.setDescription(ticketRequestDto.getDescription());
        ticketResponseDto.setCreatedBy(ticketRequestDto.getCreatedBy());
        ticketResponseDto.setId(1);

    }

    @Test
    void createTicketTest() throws Exception {
        when(ticketService.createTicket(any(TicketRequestDto.class))).thenReturn(ticketResponseDto);
        mockMvc.perform(post("/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ticketRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(ticketResponseDto.getId()));
        verify(ticketService).createTicket(any(TicketRequestDto.class));


    }

    @Test
    void getTicketByIdTest() throws Exception {
        when(ticketService.getTicketById(1)).thenReturn(ticketResponseDto);
        mockMvc.perform(get("/tickets/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ticketResponseDto.getId()));
        verify(ticketService).getTicketById(1);
    }

    @Test
    void getTicketByIdNotFoundTest() throws Exception {
        when(ticketService.getTicketById(1)).thenThrow(new TicketNotFoundException("Ticket not found"));
        mockMvc.perform(get("/tickets/1"))
                .andExpect(status().isNotFound());
        verify(ticketService).getTicketById(1);
    }


}
