package com.shpakovskiy.roomsandlamps.repository;

import com.shpakovskiy.roomsandlamps.entity.Room;
import com.shpakovskiy.roomsandlamps.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomsRepository implements RoomsRepositoryInterface {

    @Override
    @SuppressWarnings("unchecked") //Used to avoid warnings, caused by unchecked cast
    public List<Room> getAllRooms() {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return (List<Room>) session
                    .createQuery("FROM Room")
                    .list();
        }
        catch (Exception e) { e.printStackTrace(); }
        return null;
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
