package com.org.ticketmanagementsystem.dto;

import com.org.ticketmanagementsystem.enums.Category;
import com.org.ticketmanagementsystem.enums.Priority;
import com.org.ticketmanagementsystem.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequestDto {

    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Description is required")
    private String description;
    @NotNull(message = "Priority is required")
    private Priority priority;
    @NotNull(message = "Status is required")
    private Status status;
    @NotNull(message = "Category is required")
    private Category category;
    @NotBlank(message = "Created by is required")
    private String createdBy;
}
