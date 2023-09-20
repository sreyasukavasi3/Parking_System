package com.example.helloworld.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pid;
    private String pname;
    private long pcost;

    public ParkingSpot(long pid, String pname, long pcost) {
        this.pid = pid;
        this.pname = pname;
        this.pcost = pcost;
    }

    public ParkingSpot(){

    }

}
