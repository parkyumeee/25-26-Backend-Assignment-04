package com.gdg.jwtexample.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class BookerBookingDto {
    @NotBlank(message = "이름은 필수입니다.")
    private String name;
    @NotBlank(message = "전화번호는 필수입니다.")
    private String phone;
    @NotNull(message = "날짜는 필수입니다.")
    private LocalDate bookingDate;
    @NotNull(message = "시간은 필수입니다.")
    private LocalTime bookingTime;
}
