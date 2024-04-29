package com.example.demo.controllers;

import com.example.demo.dto.HotelDto;
import com.example.demo.dto.HotelList;
import com.example.demo.models.Hotel;
import com.example.demo.services.HotelServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController()
@RequestMapping("/hotel")
public class HotelController {

    private final HotelServiceInterface hotelService;
    @Autowired
    public HotelController(@Qualifier("hotelService") HotelServiceInterface hotelService){
        this.hotelService = hotelService;
    }
    @GetMapping
    public HotelList getHotels(){
        return hotelService.getHotels();
    }
    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable Integer id){
        HotelDto temp = hotelService.getHotelById(id);
        if (!Objects.isNull(temp)) {
            return ResponseEntity.ok(temp);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<HotelDto> updateHotel(@RequestBody Hotel hotel, @PathVariable Integer id){
        HotelDto temp = hotelService.updateHotel(id, hotel);
        if (Objects.isNull(temp)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            return ResponseEntity.ok(temp);
        }
    }
    @PostMapping
    public ResponseEntity<HotelDto> addHotel(@RequestBody Hotel hotel){
        HotelDto temp = hotelService.addHotel(hotel);
        if (Objects.isNull(temp)) {
            return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body(null);
        } else {
            return ResponseEntity.ok(temp);
        }
    }


}
