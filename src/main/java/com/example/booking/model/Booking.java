package com.example.booking.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    private String id;

    private String courtId;

    @Enumerated(EnumType.STRING)
    private CourtType courtType;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // store players as a comma-separated list of player ids for simplicity
    private String playerIds;

    public Booking() {}

    public Booking(String id, String courtId, CourtType courtType, LocalDateTime startTime, LocalDateTime endTime, String playerIds) {
        this.id = id;
        this.courtId = courtId;
        this.courtType = courtType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.playerIds = playerIds;
    }

    public String getId(){ return id; }
    public void setId(String id){ this.id = id; }

    public String getCourtId(){ return courtId; }
    public void setCourtId(String courtId){ this.courtId = courtId; }

    public CourtType getCourtType(){ return courtType; }
    public void setCourtType(CourtType courtType){ this.courtType = courtType; }

    public LocalDateTime getStartTime(){ return startTime; }
    public void setStartTime(LocalDateTime startTime){ this.startTime = startTime; }

    public LocalDateTime getEndTime(){ return endTime; }
    public void setEndTime(LocalDateTime endTime){ this.endTime = endTime; }

    public String getPlayerIds(){ return playerIds; }
    public void setPlayerIds(String playerIds){ this.playerIds = playerIds; }
}
