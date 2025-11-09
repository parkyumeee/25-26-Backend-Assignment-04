package com.gdg.jwtexample.controller;

import com.gdg.jwtexample.dto.BookerBookingDto;
import com.gdg.jwtexample.dto.BookerInfoResponseDto;
import com.gdg.jwtexample.dto.TokenDto;
import com.gdg.jwtexample.service.BookerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booker")
public class BookerController {
    private final BookerService bookerService;

    @PostMapping
    public ResponseEntity<TokenDto> booking(@Valid @RequestBody BookerBookingDto bookerBookingDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookerService.booking(bookerBookingDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookerInfoResponseDto> findBookerByPrincipal(Principal principal) {
        return ResponseEntity.ok(bookerService.findBookerByPrincipal(principal));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookerInfoResponseDto> update(@PathVariable Long id,
                                                        @Valid @RequestBody BookerBookingDto bookerBookingDto) {
        return ResponseEntity.status(HttpStatus.OK).body(bookerService.update(id, bookerBookingDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookerInfoResponseDto> delete(@PathVariable Long id) {
        bookerService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
