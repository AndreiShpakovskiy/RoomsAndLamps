package com.shpakovskiy.roomsandlamps.service;

import com.shpakovskiy.roomsandlamps.entity.Room;
import com.shpakovskiy.roomsandlamps.repository.RoomsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomsService implements RoomsServiceInterface {

    private final RoomsRepository roomsRepository;

    public RoomsService(RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }


    @Override
    public void addRoom(Room room) {
        roomsRepository.addRoom(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomsRepository.getAllRooms();
    }

    @Override
    public Room getRoomById(int id) {
        return roomsRepository.getRoomById(id);
    }

    @Override
    public void switchLampState(Room room) {
        roomsRepository.switchLampState(room);
    }
}
