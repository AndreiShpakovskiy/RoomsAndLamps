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
}
