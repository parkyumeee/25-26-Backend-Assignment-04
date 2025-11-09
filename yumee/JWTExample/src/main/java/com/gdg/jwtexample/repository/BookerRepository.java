package com.gdg.jwtexample.repository;

import com.gdg.jwtexample.domain.Booker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookerRepository extends JpaRepository<Booker, Long> {
}
