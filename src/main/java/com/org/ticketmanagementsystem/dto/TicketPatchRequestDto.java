package com.org.ticketmanagementsystem.dto;

import com.org.ticketmanagementsystem.enums.Category;
import com.org.ticketmanagementsystem.enums.Priority;
import com.org.ticketmanagementsystem.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TicketPatchRequestDto {

    private String title;
    private String description;
    private Priority priority;
    private Status status;
    private Category category;
}
