package com.org.ticketmanagementsystem.controller;

import com.org.ticketmanagementsystem.dto.TicketPatchRequestDto;
import com.org.ticketmanagementsystem.dto.TicketRequestDto;
import com.org.ticketmanagementsystem.dto.TicketResponseDto;
import com.org.ticketmanagementsystem.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tickets")
@Tag(name = "Ticket Management")

public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    @Operation(summary = "Create a new ticket")
    @ApiResponse(responseCode = "201", description = "Ticket created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<TicketResponseDto> createTicket(@Valid @RequestBody TicketRequestDto ticketRequestDto) {
        return new ResponseEntity<>(ticketService.createTicket(ticketRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get ticket by id")
    @ApiResponse(responseCode = "200", description = "Ticket found successfully")
    @ApiResponse(responseCode = "404", description = "Ticket not found")
    public ResponseEntity<TicketResponseDto> getTicketById(@PathVariable Integer id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @GetMapping
    @Operation(summary = "Get all tickets")
    @ApiResponse(responseCode = "200", description = "Tickets found successfully")
    public ResponseEntity<List<TicketResponseDto>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a ticket by id")
    @ApiResponse(responseCode = "200", description = "Ticket deleted successfully")
    @ApiResponse(responseCode = "404", description = "Ticket not found")
    public ResponseEntity<String> deleteTicket(@PathVariable Integer id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.ok("Ticket deleted successfully");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a ticket by id")
    @ApiResponse(responseCode = "200", description = "Ticket updated successfully")
    @ApiResponse(responseCode = "404", description = "Ticket not found")
    public ResponseEntity<TicketResponseDto> updateTicket(@PathVariable Integer id, @Valid @RequestBody TicketRequestDto ticketRequestDto) {
        return ResponseEntity.ok(ticketService.updateTicket(ticketRequestDto, id));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Patch a ticket by id")
    @ApiResponse(responseCode = "200", description = "Ticket patched successfully")
    @ApiResponse(responseCode = "404", description = "Ticket not found")
    public ResponseEntity<TicketResponseDto> patchTicket(@PathVariable Integer id, @Valid @RequestBody TicketPatchRequestDto ticketPatchRequestDto) {
        return ResponseEntity.ok(ticketService.patchTicket(ticketPatchRequestDto, id));
    }


}
