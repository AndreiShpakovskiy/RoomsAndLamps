package com.shpakovskiy.roomsandlamps.util;

import com.shpakovskiy.roomsandlamps.entity.Country;
import com.shpakovskiy.roomsandlamps.entity.IpDataItem;
import com.shpakovskiy.roomsandlamps.entity.Room;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            synchronized (SessionFactory.class) {
                if (sessionFactory == null) {
                    try {
                        Configuration configuration = new Configuration().configure();

                        configuration.addAnnotatedClass(Room.class);
                        configuration.addAnnotatedClass(Country.class);
                        configuration.addAnnotatedClass(IpDataItem.class);

                        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                                .applySettings(configuration.getProperties());
                        sessionFactory = configuration.buildSessionFactory(builder.build());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return sessionFactory;
    }
}