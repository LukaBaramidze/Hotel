package com.example.demo.services;

import com.example.demo.dto.HotelDto;
import com.example.demo.dto.HotelList;
import com.example.demo.models.Hotel;
import com.example.demo.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Qualifier("hotelService")
public class HotelService implements HotelServiceInterface {
    private final ModelConverter converter;
    private static final Map<Integer, Hotel> hotelTable = new HashMap<>();
    static {
        hotelTable.put(1, new Hotel(1, "Hotel A"));
        hotelTable.put(2, new Hotel(2, "Hotel B"));
        hotelTable.put(3, new Hotel(3, "Hotel C"));
        hotelTable.put(4, new Hotel(4, "Hotel D"));
    }

    @Autowired
    public HotelService(ModelConverter converter) {
        this.converter = converter;

    }


    @Override
    public HotelList getHotels() {
        List<Hotel> hotels = hotelTable.values().stream().toList();
        HotelList hotelDtoList = new HotelList(hotels);
        return hotelDtoList;
    }

    @Override
    public HotelDto getHotelById(Integer id) {
        Hotel hotel = hotelTable.get(id);
        return converter.convert(hotel);
    }

    @Override
    public HotelDto updateHotel(Integer id, Hotel newHotel) {
        Hotel temp = hotelTable.get(id);
        if (Objects.isNull(temp)) {
            return null;
        } else {
            hotelTable.put(id, newHotel);
            return converter.convert(newHotel);
        }
    }

    @Override
    public HotelDto deleteHotel(Integer id) {
        HotelDto hotel = getHotelById(id);
        if (Objects.isNull(hotel)) {
            return null;
        } else {
            hotelTable.remove(id);
            return hotel;
        }
    }

    @Override
    public HotelDto addHotel(Hotel hotel) {
        if (Objects.isNull(hotel)) {
            return null;
        }
        hotelTable.put(hotel.getId(), hotel);
        return converter.convert(hotel);
    }
}
