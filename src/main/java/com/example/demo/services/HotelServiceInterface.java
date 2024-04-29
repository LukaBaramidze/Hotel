package com.example.demo.services;

import com.example.demo.dto.HotelDto;
import com.example.demo.dto.HotelList;
import com.example.demo.models.Hotel;

public interface HotelServiceInterface {
    HotelList getHotels();
    HotelDto getHotelById(Integer id);

    HotelDto updateHotel(Integer id, Hotel hotel);
    HotelDto deleteHotel(Integer id);
    HotelDto addHotel(Hotel hotel);

}
