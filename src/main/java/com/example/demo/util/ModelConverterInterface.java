package com.example.demo.util;

import com.example.demo.dto.HotelDto;
import com.example.demo.dto.RoomDto;
import com.example.demo.dto.RoomList;
import com.example.demo.models.Hotel;
import com.example.demo.models.Room;

import java.util.Collection;

public interface ModelConverterInterface {
    HotelDto convert(Hotel hotel);
    RoomDto convert(Room room);


}
