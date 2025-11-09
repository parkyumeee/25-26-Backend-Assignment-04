package com.gdg.jwtexample.service;

import com.gdg.jwtexample.domain.Booker;
import com.gdg.jwtexample.domain.Role;
import com.gdg.jwtexample.dto.BookerBookingDto;
import com.gdg.jwtexample.dto.BookerInfoResponseDto;
import com.gdg.jwtexample.dto.TokenDto;
import com.gdg.jwtexample.jwt.TokenProvider;
import com.gdg.jwtexample.repository.BookerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class BookerService {
    private final BookerRepository bookerRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public TokenDto booking(BookerBookingDto bookerBookingDto) {
        Booker booker = bookerRepository.save(Booker.builder()
                .name(bookerBookingDto.getName())
                .phone(bookerBookingDto.getPhone())
                .time(bookerBookingDto.getBookingTime())
                .date(bookerBookingDto.getBookingDate())
                .role(Role.ROLE_USER)
                .build());

        String accessToken = tokenProvider.createAccessToken(booker);

        return TokenDto.builder()
                .access_token(accessToken)
                .build();
    }

    @Transactional(readOnly = true)
    public BookerInfoResponseDto findBookerByPrincipal(Principal principal) {
        Long bookerId = Long.parseLong(principal.getName());

        Booker booker = bookerRepository.findById(bookerId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        return BookerInfoResponseDto.builder()
                .id(booker.getId())
                .name(booker.getName())
                .date(booker.getDate())
                .time(booker.getTime())
                .role(booker.getRole())
                .build();
    }

    @Transactional
    public BookerInfoResponseDto update(Long id, BookerBookingDto bookerBookingDto) {
        Booker booker = bookerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("예약건을 찾을 수 없습니다."));

        String name = bookerBookingDto.getName();
        String phone = bookerBookingDto.getPhone();
        LocalDate date = bookerBookingDto.getBookingDate();
        LocalTime time = bookerBookingDto.getBookingTime();
        Role role = booker.getRole();

        booker.update(name, phone, date, time, role);

        return BookerInfoResponseDto.builder()
                .id(booker.getId())
                .name(booker.getName())
                .date(booker.getDate())
                .time(booker.getTime())
                .role(booker.getRole())
                .build();
    }

    @Transactional
    public void delete(Long id) {bookerRepository.deleteById(id);}
}
