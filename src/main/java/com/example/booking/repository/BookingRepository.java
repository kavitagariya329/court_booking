package com.example.booking.repository;

import com.example.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, String> {
    List<Booking> findByCourtIdAndEndTimeAfterAndStartTimeBefore(String courtId, LocalDateTime start, LocalDateTime end);
    List<Booking> findByEndTimeAfterAndStartTimeBefore(LocalDateTime start, LocalDateTime end);
}
