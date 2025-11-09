package com.gdg.jwtexample.dto;

import com.gdg.jwtexample.domain.Role;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
public class BookerInfoResponseDto {
    private Long id;
    private String name;
    private LocalDate date;
    private LocalTime time;
    private Role role;
}