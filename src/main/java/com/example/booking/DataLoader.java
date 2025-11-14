package com.example.booking;

import com.example.booking.model.*;
import com.example.booking.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {
    private final CourtRepository courtRepository;
    private final PlayerRepository playerRepository;
    private final BookingRepository bookingRepository;

    public DataLoader(CourtRepository courtRepository, PlayerRepository playerRepository, BookingRepository bookingRepository) {
        this.courtRepository = courtRepository;
        this.playerRepository = playerRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        courtRepository.save(new Court("C-1", CourtType.BADMINTON));
        courtRepository.save(new Court("C-2", CourtType.PICKLEBALL));
        courtRepository.save(new Court("C-3", CourtType.BADMINTON));
        playerRepository.save(new Player("p1", "Alice"));
        playerRepository.save(new Player("p2", "Bob"));
        playerRepository.save(new Player("p3", "Charlie"));

        var start = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0).withSecond(0).withNano(0);
        var end = start.plusHours(1);
        bookingRepository.save(new Booking(java.util.UUID.randomUUID().toString(), "C-1", CourtType.BADMINTON, start, end, "p2"));
    }
}
