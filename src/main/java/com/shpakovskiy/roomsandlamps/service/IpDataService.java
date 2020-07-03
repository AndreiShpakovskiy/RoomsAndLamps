package com.shpakovskiy.roomsandlamps.service;

import com.shpakovskiy.roomsandlamps.entity.IpDataItem;
import com.shpakovskiy.roomsandlamps.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IpDataService implements IpDataServiceInterface {

    @Override
    public void addItem(IpDataItem ipDataItem) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(ipDataItem);
            session.getTransaction().commit();
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void addItems(List<IpDataItem> ipDataItems) {
        for (IpDataItem ipDataItem : ipDataItems) {
            addItem(ipDataItem);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public int getCountryIdByNumericIp(long numericIp) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            List<IpDataItem> countries = (List<IpDataItem>) session
                    .createQuery("FROM IpDataItem idi WHERE :numericIp BETWEEN idi.ipFrom AND idi.ipTo")
                    .setParameter("numericIp", numericIp)
                    .list();
            if (!countries.isEmpty()) { return countries.get(0).getCountryId(); };
        }
        catch (Exception e) { e.printStackTrace(); }
        return -1;
    }
}
