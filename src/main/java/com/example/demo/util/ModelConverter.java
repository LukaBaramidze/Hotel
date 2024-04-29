package com.example.demo.util;

import com.example.demo.dto.HotelDto;
import com.example.demo.dto.RoomDto;
import com.example.demo.dto.RoomList;
import com.example.demo.models.Hotel;
import com.example.demo.models.Room;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ModelConverter implements ModelConverterInterface{

    @Override
    public HotelDto convert(Hotel hotel) {
        return HotelDto.builder()
                .name(hotel.getName())
                .build();
    }

    @Override
    public RoomDto convert(Room room) {
        return RoomDto.builder()
                .roomNumber(room.getRoomNumber())
                .isBooked(room.isBooked())
                .hotelId(room.getHotelId())
                .build();
    }




}
