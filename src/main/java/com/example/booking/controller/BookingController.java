package com.example.booking.controller;

import com.example.booking.model.*;
import com.example.booking.service.BookingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BookingController {
    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping("/courts")
    public List<Court> courts(){ return service.listCourts(); }

    @GetMapping("/courts/search")
    public List<Court> search(@RequestParam CourtType type,
                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end){
        return service.searchAvailable(type, start, end);
    }

    record BookingRequest(String courtId, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end, List<String> playerIds){}

    @PostMapping("/bookings")
    public ResponseEntity<?> book(@RequestBody BookingRequest req){
        var b = service.createBooking(req.courtId, req.start, req.end, req.playerIds);
        return ResponseEntity.ok(b);
    }

    @GetMapping("/players/{id}")
    public ResponseEntity<?> getPlayer(@PathVariable String id){
        return service.findPlayer(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/players")
    public ResponseEntity<?> addPlayer(@RequestBody Player p){
        return ResponseEntity.ok(service.savePlayer(p));
    }

    @GetMapping("/bookings")
    public List<Booking> bookings(){ return service.listBookings(); }
}
