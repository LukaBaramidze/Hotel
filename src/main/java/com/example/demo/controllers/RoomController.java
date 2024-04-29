package com.example.demo.controllers;

import com.example.demo.dto.RoomDto;
import com.example.demo.dto.RoomList;
import com.example.demo.models.Room;
import com.example.demo.services.RoomServiceInterace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController()
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private final RoomServiceInterace roomService;
    public RoomController(@Qualifier("roomService") RoomServiceInterace roomService){
        this.roomService = roomService;
    }
    @GetMapping("/{hotelId}")
    public ResponseEntity<RoomList> getRoomsOfHotel(@PathVariable Integer hotelId){
        return ResponseEntity.ok(roomService.getRooms(hotelId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> bookRoom(@PathVariable Integer id) {
        boolean booked = roomService.bookRoom(id);
        if (booked) {
            return ResponseEntity.ok("Room successfully booked for one day!");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Room couldn't be booked: Room not available!");
        }
    }
    @PostMapping
    public ResponseEntity<RoomDto> addRoom(@RequestBody Room room, @RequestParam Integer hotelId) {
        RoomDto newRoom = roomService.addRoom(room, hotelId);
        if (newRoom!= null) {
            return ResponseEntity.ok(newRoom);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<RoomDto> deleteRoom(@PathVariable Integer id){
        RoomDto temp = roomService.deleteRoom(id);
        if (Objects.isNull(temp)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            return ResponseEntity.ok(temp);
        }
    }
}
