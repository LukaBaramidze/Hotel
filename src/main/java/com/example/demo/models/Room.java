package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private Integer id;
    private String roomNumber;
    private boolean isBooked;
    private Integer hotelId;
}
