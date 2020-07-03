package com.shpakovskiy.roomsandlamps.service;

import com.shpakovskiy.roomsandlamps.entity.Room;

import java.util.List;

public interface RoomsServiceInterface {
    void addRoom(Room room);
    List<Room> getAllRooms();
    Room getRoomById(int id);
    void switchLampState(Room room);
}
