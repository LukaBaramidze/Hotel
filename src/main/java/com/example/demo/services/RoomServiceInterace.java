package com.example.demo.services;

import com.example.demo.dto.RoomDto;
import com.example.demo.dto.RoomList;
import com.example.demo.models.Room;

public interface RoomServiceInterace {

    RoomList getRooms(Integer hotelId);

    RoomDto addRoom(Room room, Integer hotelId); // hotelid aris romeli hotelshi unda davamatot
    RoomDto deleteRoom(Integer id);
    RoomDto updateRoom(Integer id, Room newRoom);
    boolean bookRoom(Integer id);


}
