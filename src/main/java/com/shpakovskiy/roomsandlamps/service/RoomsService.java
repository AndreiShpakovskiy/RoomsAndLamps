package com.shpakovskiy.roomsandlamps.service;

import com.shpakovskiy.roomsandlamps.entity.Country;
import com.shpakovskiy.roomsandlamps.entity.Room;
import com.shpakovskiy.roomsandlamps.repository.RoomsRepository;
import com.shpakovskiy.roomsandlamps.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
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
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(room);
            session.getTransaction().commit();
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public List<Room> getAllRooms() {
        return roomsRepository.getAllRooms();
    }

    @Override
    public Room getRoomById(int id) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Room.class, id);
        }
        catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public void switchLampState(Room room) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            room.setLampOn(!room.isLampOn());
            session.update(room);
            session.getTransaction().commit();
        }
        catch (Exception e) { e.printStackTrace(); }
    }
}
