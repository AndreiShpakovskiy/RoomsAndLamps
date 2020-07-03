package com.shpakovskiy.roomsandlamps.repository;

import com.shpakovskiy.roomsandlamps.entity.Room;

import java.util.List;

public interface RoomsRepositoryInterface {
    List<Room> getAllRooms();
    void addRoom(Room room);
    Room getRoomById(int id);
    void switchLampState(Room room);
}
