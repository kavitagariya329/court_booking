package com.example.booking.model;

import jakarta.persistence.*;

@Entity
@Table(name = "court")
public class Court {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private CourtType type;

    public Court() {}

    public Court(String id, CourtType type) {
        this.id = id;
        this.type = type;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public CourtType getType() { return type; }
    public void setType(CourtType type) { this.type = type; }
}
