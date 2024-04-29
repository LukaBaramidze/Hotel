package com.example.demo.services;

import com.example.demo.dto.RoomDto;
import com.example.demo.dto.RoomList;
import com.example.demo.models.Room;
import com.example.demo.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Qualifier("roomService")
public class RoomService implements RoomServiceInterace {

    private final static Map<Integer, Room> roomTable = new HashMap<>();

    static {
        roomTable.put(1, new Room(1, "Room 101", false, 1));
        roomTable.put(2, new Room(2, "Room 102", false, 1));

        roomTable.put(3, new Room(3, "Room 201", false, 2));
        roomTable.put(4, new Room(4, "Room 202", false, 2));

        roomTable.put(5, new Room(5, "Room 301", false, 3));
        roomTable.put(6, new Room(6, "Room 302", false, 3));

        roomTable.put(7, new Room(7, "Room 401", false, 4));
        roomTable.put(8, new Room(8, "Room 402", false, 4));
    }
    private final ModelConverter converter;

    @Autowired
    public RoomService(ModelConverter converter) {
        this.converter = converter;
    }

    @Override
    public RoomList getRooms(Integer hotelId) {
        List<Room> rooms= roomTable.values()
                        .stream()
                        .filter(room -> room.getHotelId().equals(hotelId) && !room.isBooked())
                        .toList();
        return new RoomList(rooms);
    }

    @Override
    public RoomDto addRoom(Room room, Integer hotelId) {
        if (Objects.isNull(room)) {
            return null;
        } else {
            roomTable.put(room.getId(), room);
            return converter.convert(room);
        }
    }

    @Override
    public RoomDto deleteRoom(Integer id) {
        Room temp = roomTable.get(id);
        if (Objects.isNull(temp)) {
            return null;
        } else {
            roomTable.remove(id);
            return converter.convert(temp);
        }
    }

    @Override
    public RoomDto updateRoom(Integer id, Room newRoom) {
        Room temp = roomTable.get(id);
        if (Objects.isNull(temp)) {
            return null;
        } else {
            roomTable.put(id, newRoom);
            return converter.convert(newRoom);
        }
    }

    public boolean bookRoom(Integer id) {
        Room room = roomTable.get(id);
        if (room == null) {
            System.out.println("Room not found.");
            return false;
        }
        if (room.isBooked()) {
            System.out.println("Room already booked.");
            return false;
        }
        room.setBooked(true);
        updateRoom(id, room);
        return true;
    }
}