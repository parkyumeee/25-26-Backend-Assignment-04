package com.gdg.jwtexample.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="booker")
@Getter
@NoArgsConstructor
public class Booker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BOOKER_NAME", nullable = false)
    private String name;

    @Column(name = "BOOKER_TEL", nullable = false, unique = true)
    private String phone;

    @Column(name = "BOOKER_DATE", nullable = false)
    private LocalDate date;

    @Column(name = "BOOKER_TIME", nullable = false)
    private LocalTime time;

    @Enumerated(EnumType.STRING)
    @Column(name = "BOOKER_ROLE", nullable = false)
    private Role role;

    @Builder
    public Booker(String name, String phone, LocalDate date, LocalTime time, Role role) {
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.time = time;
        this.role = role;
    }

    public void update(String name, String phone, LocalDate date, LocalTime time, Role role) {
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.time = time;
        this.role = role;
    }
}
