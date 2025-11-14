package com.example.booking.service;

import com.example.booking.model.*;
import com.example.booking.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingService {
    private final CourtRepository courtRepository;
    private final BookingRepository bookingRepository;
    private final PlayerRepository playerRepository;

    public BookingService(CourtRepository courtRepository, BookingRepository bookingRepository, PlayerRepository playerRepository) {
        this.courtRepository = courtRepository;
        this.bookingRepository = bookingRepository;
        this.playerRepository = playerRepository;
    }

    public List<Court> listCourts() { return courtRepository.findAll(); }

    public List<Court> searchAvailable(CourtType type, LocalDateTime start, LocalDateTime end) {
        var courts = courtRepository.findAll().stream().filter(c -> c.getType() == type).collect(Collectors.toList());
        var clashes = bookingRepository.findByEndTimeAfterAndStartTimeBefore(start, end).stream().map(Booking::getCourtId).collect(Collectors.toSet());
        return courts.stream().filter(c -> !clashes.contains(c.getId())).collect(Collectors.toList());
    }

    public Booking createBooking(String courtId, LocalDateTime start, LocalDateTime end, List<String> playerIds) {
        // check clashes for the court
        var clash = bookingRepository.findByCourtIdAndEndTimeAfterAndStartTimeBefore(courtId, start, end);
        if (!clash.isEmpty()) throw new IllegalStateException("Court already booked for this time");

        var court = courtRepository.findById(courtId).orElseThrow(() -> new IllegalArgumentException("Court not found"));
        var id = UUID.randomUUID().toString();
        var playersCsv = String.join(",", playerIds);
        var booking = new Booking(id, courtId, court.getType(), start, end, playersCsv);
        return bookingRepository.save(booking);
    }

    public Optional<Player> findPlayer(String id){ return playerRepository.findById(id); }

    public Player savePlayer(Player p){ return playerRepository.save(p); }

    public List<Booking> listBookings(){ return bookingRepository.findAll(); }
}
